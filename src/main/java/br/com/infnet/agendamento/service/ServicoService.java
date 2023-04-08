package br.com.infnet.agendamento.service;

import br.com.infnet.agendamento.model.Servico;
import br.com.infnet.agendamento.respository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    ServicoRepository servicoRepository;

    public void salvar(Servico servico){
        servicoRepository.save(servico);
    }

    public void deletar(Long id){
        servicoRepository.deleteById(id);
    }

    public List<Servico> listarTodos(){
        return servicoRepository.findAll();
    }

    public Servico getServicoById(Long id) { return servicoRepository.findById(id).orElse(null);}
}
