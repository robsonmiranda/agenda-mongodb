package br.com.mongodbAgenda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.mongodbAgenda.model.Contato;

public interface ContatoRepository extends MongoRepository<Contato, Long> {
    
}
