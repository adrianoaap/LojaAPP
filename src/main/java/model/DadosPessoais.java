package model;

import lombok.*;

import javax.persistence.Embeddable;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class DadosPessoais {

    private String nome;
    private String cpf;
    private String cidade;
    private String pais;

}
