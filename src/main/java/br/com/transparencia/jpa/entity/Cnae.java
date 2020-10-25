package br.com.transparencia.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Cnae {
	private String codigoDivisao;
	private String divisao;
}
