package br.com.transparencia.event.listners;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.transparencia.jpa.entity.Transacao;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Component
public class UnloadCardsTransactions implements ApplicationListener<ApplicationReadyEvent> {
	
	final static Logger logger = LoggerFactory.getLogger(UnloadCardsTransactions.class);

	private WebClient client = WebClient.builder()
				.baseUrl("http://www.portaltransparencia.gov.br")
				.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.defaultHeader("chave-api-dados", "fb5454d37fd3d49d2a5859c3390b8f0d")
				.build();

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("Unload Cards Transactions has started the process.");
		
		logger.info("Retrieving from Government API.");
		List<Integer> pages = Arrays.asList(1, 2, 3);
		Flux.fromIterable(pages).parallel().runOn(Schedulers.elastic())
			.flatMap(this::getTransactionsByPage)
			.subscribe(t -> logger.info(t.toString()));
//		this.getTransactionsByPage(1).subscribe(t -> logger.info(t.toString()));
	}
	
	private Flux<Transacao> getTransactionsByPage(Integer page) {
		return client.get()
			.uri(uriBuilder -> uriBuilder.path("/api-de-dados/cartoes")
					.queryParam("mesExtratoInicio", "01/2020")
					.queryParam("mesExtratoFim", "01/2020")
					.queryParam("pagina", page.toString())
					.build())
			.retrieve()
			.bodyToFlux(Transacao.class);
	}

}
