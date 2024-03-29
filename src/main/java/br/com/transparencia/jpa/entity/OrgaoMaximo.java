package br.com.transparencia.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "orgao_maximo")
public class OrgaoMaximo {
	@Id
	private String codigo;
	private String sigla;
	private String nome;
}
