package br.com.transparencia.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class UnidadeGestora {
	private String codigo;
	private String nome;
	private OrgaoVinculado orgaoVinculado;
}
