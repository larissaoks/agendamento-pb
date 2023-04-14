package br.com.infnet.agendamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="servico")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Servico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServico;

    private String nomeServico;

    private String nomeProfissional;

    private double valor;

    @JsonIgnore
    @ManyToMany(mappedBy = "servicos")
    private List<Agendamento> agenda;

    public Servico (String nomeServico, String nomeProfissional, double valor){
        this.nomeServico = nomeServico;
        this.nomeProfissional = nomeProfissional;
        this.valor = valor;
    }

}
