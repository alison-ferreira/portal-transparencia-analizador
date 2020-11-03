package br.com.transparencia.jpa.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "transacoes")
@NamedNativeQueries({
	@NamedNativeQuery(
			name = "Transacao.getSumPerCnae",
			query = "SELECT SUM(valor_transacao) soma, codigo_divisao, divisao "
					+ "FROM transacoes INNER JOIN cnaes ON id_cnae = codigo_divisao "
					+ "GROUP BY codigo_divisao, divisao "
					+ "ORDER BY 1 DESC", 
			resultClass = Cnae.class)
})
public class Transacao {
	@Id
	private Long id;
	private String mesExtrato;
	private String dataTransacao;
	@JsonIgnore
	private BigDecimal valorTransacao;
	@Transient
	@JsonProperty("valorTransacao")
	private String valorTransacaoStr;
	@OneToOne
	@JoinColumn(name = "id_cnae", referencedColumnName = "codigoDivisao")
	private Cnae cnae;
	@OneToOne
	@JoinColumn(name = "id_orgao_maximo", referencedColumnName = "codigo")
	private OrgaoMaximo orgaoMaximo;
	
	public void setValorTransacaoStr(String valorStr) {
		String valorModificado = valorStr.replaceAll("- ", "-").replaceAll("\\.", "").replaceAll(",", ".");
		this.setValorTransacao(new BigDecimal(valorModificado));
	}
}
