package br.com.transparencia.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrgaoVinculado {
	private String nome;
	private String cnpj;
	private String sigla;
	private String descricaoPoder;
	private OrgaoMaximo orgaoMaximo;
}
