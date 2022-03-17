package br.com.mongodbAgenda.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("contato")
public class Contato {
    
    @Transient
    public static final String SEQUENCE_NAME = "contato_sequence";

    @Id
    private Long id;
    private String nome;
    private String fone;

    public Contato(String nome, String fone) {
        this.nome = nome;
        this.fone = fone;
    }

    public String toString() {
        return "Contato{" + "id=" + id + ", nome='" + nome + '\'' +
            ", fone='" + fone + '\'' + '}';
    }
}
