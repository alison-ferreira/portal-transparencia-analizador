package br.com.transparencia.service;

import java.util.List;

import br.com.transparencia.jpa.entity.Cnae;

public interface TransacaoService {
	List<Cnae> getTransacaoSumPerCnae();
}
