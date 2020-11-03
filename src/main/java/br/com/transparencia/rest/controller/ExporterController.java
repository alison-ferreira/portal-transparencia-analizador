package br.com.transparencia.rest.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.transparencia.service.TransacaoService;

@RestController
@RequestMapping("/exportar/transacoes")
public class ExporterController {

	@Autowired
	private TransacaoService transacaoService;
	
	private static final String DELIMITER = ";";
	private static final String LINE_SEPARATOR = "\r\n";

	@GetMapping("/porCnae")
	public void getTransactionAmountGroupedByCnae(HttpServletResponse response) throws IOException {
		response.setHeader(HttpHeaders.CONTENT_TYPE, "text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=exportacao.csv");
		
		String csvHeaders = "Soma" + DELIMITER 
				+ "Código Divisão" + DELIMITER 
				+ "Descrição Divisão" + LINE_SEPARATOR;

		String csvLines = transacaoService.getTransacaoSumPerCnae().stream()
				.map(t -> String.valueOf(t.getSoma()).replaceAll("\\.", ",") + DELIMITER
						+ t.getCodigoDivisao() + DELIMITER + t.getDivisao())
				.collect(Collectors.joining(LINE_SEPARATOR));

		String csvContent = csvHeaders + csvLines;
		
		response.setContentLength(csvContent.length());

		response.getWriter().append(csvContent);
	}

}
