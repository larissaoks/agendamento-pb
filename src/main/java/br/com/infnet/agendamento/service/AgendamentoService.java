package br.com.infnet.agendamento.service;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.model.Servico;
import br.com.infnet.agendamento.respository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepository agendamentoRepository;

    @Autowired
    ServicoService servicoService;

    public Optional<Agendamento> getById(Long id){
        return agendamentoRepository.findById(id);
    }

    public void salvar(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public void deletar(Long id){
        agendamentoRepository.deleteById(id);
    }

    public List<Agendamento> buscarAgendaPorCliente(Long idCliente){
        return agendamentoRepository.findAllByIdCliente(idCliente) ;
    }

    public List<Agendamento> buscarAgenda(){
        return agendamentoRepository.findAll();
    }

    public boolean existeAgendamentoExistente(String dataAgendamento, String horarioAgendamento, Long idServico) {
        Servico servico = servicoService.getServicoById(idServico);
        return agendamentoRepository.existsAgendamentoByDataAgendamentoAndHorarioAgendamentoAndAndServicos(dataAgendamento, horarioAgendamento, servico);
    }


}
