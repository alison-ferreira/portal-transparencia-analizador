package br.com.transparencia.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UnidadeGestoraVO {
	private String codigo;
	private String nome;
	private OrgaoVinculadoVO orgaoVinculado;
}
