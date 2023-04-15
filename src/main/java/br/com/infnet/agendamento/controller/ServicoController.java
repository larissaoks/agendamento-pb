package br.com.infnet.agendamento.controller;

import br.com.infnet.agendamento.model.Agendamento;
import br.com.infnet.agendamento.model.Servico;
import br.com.infnet.agendamento.service.DecoderService;
import br.com.infnet.agendamento.service.ServicoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("servico/")
@AllArgsConstructor
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @Autowired
    DecoderService decoderService;

    @GetMapping("listarServicos")
    public ResponseEntity<Map<String, Object>> listarServicos(){
        List<Servico> servicos = servicoService.listarTodos();
        if(servicos.isEmpty()){
            return ResponseEntity.ok(Map.of("info", "Não há serviços registrados"));
        }
        return ResponseEntity.ok(Map.of("servicos", servicos));
    }

    @PostMapping("criarServico")
    public ResponseEntity<String> criarServico(@RequestBody Servico servico){
        servicoService.salvar(servico);
        return ResponseEntity.ok("Serviço criado");
    }

    @GetMapping("pegarServico/{id}")
    public ResponseEntity<Map<String, Object>> listarServicoPorId(@PathVariable Long id){
        Servico servico = servicoService.getServicoById(id);
        System.out.println(servico);
        System.out.println(servico.getIdServico() + servico.getValor() + servico.getNomeServico());

        if(servico == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Não há serviço com o id: " + id));
        }

        return ResponseEntity.ok(Map.of("servico", servico));
    }

    @DeleteMapping("cancelarServico/{idServico}")
    public ResponseEntity<String> cancelarServico(@PathVariable String idServico){
        Servico servico = servicoService.getServicoById(Long.valueOf(idServico));
        if (servico == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum serviço encontrado com o id " + idServico);
        }
        else {
            servicoService.deletar(Long.valueOf(idServico));
            return ResponseEntity.status(HttpStatus.OK).body("Servico apagado!");
        }

    }
}
