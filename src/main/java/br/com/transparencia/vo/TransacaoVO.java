package br.com.transparencia.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TransacaoVO {
	private Long id;
	private String mesExtrato;
	private String dataTransacao;
	private String valorTransacao;
	private EstabelecimentoVO estabelecimento;
	private UnidadeGestoraVO unidadeGestora;
}
