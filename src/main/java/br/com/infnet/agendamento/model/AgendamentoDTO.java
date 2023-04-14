package br.com.infnet.agendamento.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AgendamentoDTO {
    private String dataAgendamento;
    private String horarioAgendamento;
    private String idServico;

}
