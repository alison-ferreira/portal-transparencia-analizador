package br.com.transparencia.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Estabelecimento {
	private String numeroInscricaoSocial;
	private String nome;
	private String razaoSocialReceita;
	private String nomeFantasiaReceita;
	private Cnae cnae;
}
