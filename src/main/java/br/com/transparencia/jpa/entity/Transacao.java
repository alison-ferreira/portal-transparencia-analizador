package br.com.transparencia.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Transacao {
	private Long id;
	private String mesExtrato;
	private String dataTransacao;
	private String valorTransacao;
	private Estabelecimento estabelecimento;
	private UnidadeGestora unidadeGestora;
}
