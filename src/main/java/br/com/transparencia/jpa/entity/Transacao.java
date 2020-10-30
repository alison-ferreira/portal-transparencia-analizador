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
@Entity(name = "transacoes")
public class Transacao {
	@Id
	private Long id;
	private String mesExtrato;
	private String dataTransacao;
	private String valorTransacao;
	@OneToOne
	@JoinColumn(name = "id_estabelecimento", referencedColumnName = "nome")
	private Estabelecimento estabelecimento;
//	private UnidadeGestora unidadeGestora;
}
