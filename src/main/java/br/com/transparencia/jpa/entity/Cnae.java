package br.com.transparencia.jpa.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "cnaes")
public class Cnae {
	@Id
	private String codigoDivisao;
	private String divisao;
	private BigDecimal soma;
}
