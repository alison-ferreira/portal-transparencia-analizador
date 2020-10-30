package br.com.transparencia.jpa.respository;

import org.springframework.data.repository.CrudRepository;

import br.com.transparencia.jpa.entity.Transacao;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

}
