package br.com.transparencia.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.transparencia.jpa.entity.Cnae;
import br.com.transparencia.service.TransacaoService;

@Service
public class TransacaoServiceImpl implements TransacaoService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Cnae> getTransacaoSumPerCnae() {
		return entityManager
				.createNamedQuery("Transacao.getSumPerCnae", Cnae.class)
				.getResultList();
	}
	
}
