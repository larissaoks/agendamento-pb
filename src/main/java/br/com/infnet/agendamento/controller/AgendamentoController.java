package br.com.infnet.agendamento.controller;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.model.AgendamentoDTO;
import br.com.infnet.agendamento.model.Servico;
import br.com.infnet.agendamento.service.AgendamentoService;
import br.com.infnet.agendamento.service.DecoderService;
import br.com.infnet.agendamento.service.ServicoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("agenda/")
@AllArgsConstructor
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @Autowired
    ServicoService servicoService;

    @Autowired
    DecoderService decoderService;

    @GetMapping("buscarAgendaPorCliente")
    public ResponseEntity<Map<String,Object>> buscarAgendaPorCliente(@RequestHeader("Authorization") String token){
        Long idCliente = decoderService.getIdCliente(token);
        List<Agendamento> agendaDoCliente = agendamentoService.buscarAgendaPorCliente(idCliente);
        if (agendaDoCliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("error","Não há nenhum serviço agendado para o id: " + idCliente));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("agenda", agendaDoCliente));
    }

    @GetMapping("buscarAgenda")
    public ResponseEntity<Map<String,Object>> buscarAgenda(){
        List<Agendamento> agendaCompleta = agendamentoService.buscarAgenda();
        if(agendaCompleta.isEmpty()){
            return ResponseEntity.ok(Map.of("Aviso: ", "Não há serviço agendado"));
        }
        return ResponseEntity.ok(Map.of("Agenda: ", agendaCompleta));
    }

    @PostMapping("marcarServico")
    public ResponseEntity marcarServico(@RequestHeader("Authorization") String token, @RequestBody AgendamentoDTO agenda){//@RequestParam String dataAgendamento, @RequestParam String horarioAgendamento, @RequestParam String idServico){
        try {
            Long idCliente = decoderService.getIdCliente(token);

            Servico servico = servicoService.getServicoById(Long.valueOf(agenda.getIdServico()));
            System.out.println(servico.getIdServico());

            Agendamento newAgenda = new Agendamento(idCliente, agenda.getDataAgendamento(), agenda.getHorarioAgendamento());
            newAgenda.agendarServico(servico);

            agendamentoService.salvar(newAgenda);

            return ResponseEntity.status(HttpStatus.CREATED).body("Marcado para dia: " + agenda.getDataAgendamento() + " às " + agenda.getHorarioAgendamento());
        }
        catch(Error error){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(error.getMessage());
        }
    }


    @DeleteMapping("desmarcarServico/{idAgenda}")
    public ResponseEntity<String> desmarcarServico(@PathVariable String idAgenda){
        Optional<Agendamento> agenda = agendamentoService.getById(Long.valueOf(idAgenda));
        if (agenda.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum serviço encontrado com o id " + idAgenda);
        }
        else {
            agendamentoService.deletar(Long.valueOf(idAgenda));
            return ResponseEntity.status(HttpStatus.OK).body("Servico desmarcado!");
        }

    }
}
