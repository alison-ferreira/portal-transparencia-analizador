package br.com.transparencia.event.listners;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.transparencia.jpa.entity.Cnae;
import br.com.transparencia.jpa.entity.OrgaoMaximo;
import br.com.transparencia.jpa.entity.Transacao;
import br.com.transparencia.jpa.respository.CnaeRepository;
import br.com.transparencia.jpa.respository.OrgaoMaximoRepository;
import br.com.transparencia.jpa.respository.TransacaoRepository;
import br.com.transparencia.vo.TransacaoVO;
import reactor.core.publisher.Flux;

@Component
public class UnloadCardsTransactions implements ApplicationListener<ApplicationReadyEvent> {
	
	final static Logger logger = LoggerFactory.getLogger(UnloadCardsTransactions.class);
	
	@Autowired
	TransacaoRepository transacaoRepository;
	
	@Autowired
	CnaeRepository cnaeRepository;
	
	@Autowired
	OrgaoMaximoRepository orgaoMaximoRepository;

	private WebClient client = WebClient.builder()
				.baseUrl("http://www.portaltransparencia.gov.br")
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader("chave-api-dados", "fb5454d37fd3d49d2a5859c3390b8f0d")
				.build();

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("Unload Cards Transactions has started the process.");
		
		logger.info("Retrieving from Government API.");
		Flux.interval(this.getDuration()).filter(i -> i > 0)
			.flatMap(this::getTransactionsByPage).log()
			.takeWhile(t -> t.getId() != null)
			.doOnError(e -> logger.error(e.getMessage()))
			.doOnComplete(() -> logger.info("All data has been retrived from Government API."))
			.subscribe(t -> this.saveTransaction(t), 
					e -> logger.error(e.getMessage()));
	}
	
	private Flux<TransacaoVO> getTransactionsByPage(Long page) {
		logger.info("GET page: " + page.toString());
		
		return client.get()
			.uri(uriBuilder -> uriBuilder.path("/api-de-dados/cartoes")
					.queryParam("dataTransacaoInicio", "01/01/2020")
					.queryParam("dataTransacaoFim", "31/01/2020")
					.queryParam("pagina", page.toString())
					.build())
			.retrieve()
			.bodyToFlux(TransacaoVO.class)
			.defaultIfEmpty(new TransacaoVO());
	}
	
	private void saveTransaction(TransacaoVO transacaoVO) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			String cnaeJson = mapper.writeValueAsString(transacaoVO.getEstabelecimento().getCnae());
			Cnae cnae = mapper.readValue(cnaeJson, Cnae.class);
			cnaeRepository.save(cnae);
			
			String orgaoMaximoJson = mapper.writeValueAsString(transacaoVO.getUnidadeGestora().getOrgaoVinculado().getOrgaoMaximo());
			OrgaoMaximo orgaoMaximo = mapper.readValue(orgaoMaximoJson, OrgaoMaximo.class);
			orgaoMaximoRepository.save(orgaoMaximo);
			
			String transacaoJson = mapper.writeValueAsString(transacaoVO);
			Transacao transacao;
			transacao = mapper.readValue(transacaoJson, Transacao.class);
			transacao.setCnae(cnae);
			transacao.setOrgaoMaximo(orgaoMaximo);
			transacaoRepository.save(transacao);
		} catch (JsonMappingException e) {
			logger.error("saveTransaction => {}", e.getMessage());
		} catch (JsonProcessingException e) {
			logger.error("saveTransaction => {}", e.getMessage());
		}
	}
	
	private Duration getDuration() {
		LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		
		if (dateTime.getHour() >= 0 && dateTime.getHour() < 6) {
			return Duration.ofMillis(200L);
		} else {
			return Duration.ofSeconds(2L);
		}
	}

}
