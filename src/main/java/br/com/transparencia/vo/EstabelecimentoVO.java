package br.com.transparencia.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class EstabelecimentoVO {
	private String numeroInscricaoSocial;
	private String nome;
	private String razaoSocialReceita;
	private String nomeFantasiaReceita;
	private CnaeVO cnae;
}
