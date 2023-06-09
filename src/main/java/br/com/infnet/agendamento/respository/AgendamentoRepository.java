package br.com.infnet.agendamento.respository;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByIdCliente(Long idCliente);

    Boolean existsAgendamentoByDataAgendamentoAndHorarioAgendamentoAndAndServicos(String dataAgendamento, String horarioAgendamento, Servico servicos);


}
