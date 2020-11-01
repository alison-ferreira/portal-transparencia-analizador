package br.com.transparencia.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrgaoVinculadoVO {
	private String nome;
	private String cnpj;
	private String sigla;
	private String descricaoPoder;
	private OrgaoMaximoVO orgaoMaximo;
}
