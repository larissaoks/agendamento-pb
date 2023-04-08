package br.com.infnet.agendamento.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="agenda")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;
    private Long idCliente;
    private String dataAgendamento;
    private String horarioAgendamento;

    @ManyToMany
    @JoinTable(name = "agendamentoServico",
    joinColumns = @JoinColumn(name = "idAgenda"), inverseJoinColumns = @JoinColumn(name = "idServico"))
    private List<Servico> servicos;

    public Agendamento(Long idCliente, String dataAgendamento, String horarioAgendamento){
        this.idCliente = idCliente;
        this.dataAgendamento = dataAgendamento;
        this.horarioAgendamento = horarioAgendamento;
        this.servicos = new ArrayList<>();
    }

    public void agendarServico(Servico servico){
        this.servicos.add(servico);
    }
}

