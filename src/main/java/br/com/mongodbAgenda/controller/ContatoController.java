package br.com.mongodbAgenda.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mongodbAgenda.exception.ResourceNotFoundException;
import br.com.mongodbAgenda.model.Contato;
import br.com.mongodbAgenda.repository.ContatoRepository;
import br.com.mongodbAgenda.service.SequenceGeneratorService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/")
    public List<Contato> getAllContatos() {
        return contatoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contato getContatoById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if (id != null) {
            Optional<Contato> contato = this.contatoRepository.findById(id);
            if (contato.isPresent()) {
                return contato.get();
            }
        }
        return null;
    }

    @PostMapping("/")
    public Contato createContato(@RequestBody Contato contato) {
        contato.setId(sequenceGeneratorService.generateSequence(Contato.SEQUENCE_NAME));
        return this.contatoRepository.save(contato);
    }

    @PutMapping("/{id}")
    public Contato updateContato(@PathVariable("id") Long id, @RequestBody Contato contatoDetails) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado para este id :: " + id));
        contato.setNome(contatoDetails.getNome());
        contato.setFone(contatoDetails.getFone());
        final Contato updatedContato = contatoRepository.save(contato);
        return updatedContato;
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteContato(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado para este id :: " + id));
        contatoRepository.delete(contato);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
