package br.com.transparencia.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class OrgaoMaximoVO {
	private String codigo;
	private String sigla;
	private String nome;
}
