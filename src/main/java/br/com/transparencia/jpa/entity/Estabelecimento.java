package br.com.transparencia.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "estabelecimentos")
public class Estabelecimento {
	private String numeroInscricaoSocial;
	@Id
	private String nome;
	private String razaoSocialReceita;
	private String nomeFantasiaReceita;
	@OneToOne
	@JoinColumn(name = "id_cnae", referencedColumnName = "codigoDivisao")
	private Cnae cnae;
}
