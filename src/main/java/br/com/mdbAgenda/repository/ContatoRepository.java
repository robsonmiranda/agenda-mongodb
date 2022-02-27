package br.com.mdbAgenda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mdbAgenda.model.Contato;

public interface ContatoRepository extends MongoRepository<Contato, Long> {
    
}
