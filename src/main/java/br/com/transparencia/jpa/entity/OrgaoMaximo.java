package br.com.transparencia.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrgaoMaximo {
	private String codigo;
	private String sigla;
	private String nome;
}
