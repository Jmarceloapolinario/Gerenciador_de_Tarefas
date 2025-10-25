package com.jm.Gerenciador_de_Tarefas.Controller;


import com.jm.Gerenciador_de_Tarefas.DTO.TarefasDTO;
import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import com.jm.Gerenciador_de_Tarefas.Service.TarefaService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<TarefasDTO>> listartarefas(){
        List<TarefasDTO> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);

}
    @GetMapping("/listar/{data}")
    public ResponseEntity<?> listaTarefasData(@PathVariable LocalDate data) {
        List<TarefasDTO> tarefa = tarefaService.listarTarefasDate(data);
        if (tarefa != null) {
            return ResponseEntity.ok(tarefa);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa não encontrada para a data: " + data);
        }
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarTarefaID(@PathVariable Long id){
        TarefasDTO tarefa = tarefaService.listarTarefaPorId(id);
        if(tarefa != null){
            return ResponseEntity.ok(tarefa);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa nao encontrada");
        }
    }
    @GetMapping("/listar/status/{status}")
    public ResponseEntity<?> listarPorStatus(@PathVariable String status){
        List<TarefasDTO> tarefa  = tarefaService.listarPorStatus(status);
        if (tarefa != null){
            return ResponseEntity.ok(tarefa);
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Tarefa nao encotrada");
        }
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarTarefa(@PathVariable Long id , @RequestBody TarefasDTO tarefasDTO){

        if (tarefaService.listarTarefaPorId(id) != null){
            TarefasDTO tarefaAtualizada = tarefaService.alteraTarefas(id ,tarefasDTO);
            return ResponseEntity.ok("A tarefa de id: "+id+" foi atualizada com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)

                    .body("Tarefa de id: "+id+ " nao encotrada");
        }
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarPorId(@PathVariable Long id){
        if(tarefaService.listarTarefaPorId(id) != null){
            tarefaService.deletarTarefa(id);
            return ResponseEntity.ok("A Tarefa de id: "+id+ " deletada com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A Tarefa de id: "+id+" nao encotrado");
        }
    }


    @PostMapping("/criar")
    public ResponseEntity<String> criarTarefa(@RequestBody TarefasDTO tarefasDTO){
        TarefasDTO novaTarefa = tarefaService.criarTarefa(tarefasDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa criada com sucesso: "+ novaTarefa.getNome());


    }


}
