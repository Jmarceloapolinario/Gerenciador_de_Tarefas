package com.jm.Gerenciador_de_Tarefas.Controller;


import com.jm.Gerenciador_de_Tarefas.DTO.TarefasDTO;
import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import com.jm.Gerenciador_de_Tarefas.Service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lista todas as tarefas")
    public ResponseEntity<List<TarefasDTO>> listartarefas(){
        List<TarefasDTO> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);

}
    @GetMapping("/listar/{data}")
    @Operation(summary = "Lista todas as tarefas por data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarefa nao encotarada")
    })
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
    @Operation(summary = "Lista todas as tarefas por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarefa nao encotarada")
    })
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
    @Operation(summary = "Lista todas as tarefas por status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "400", description = "Tarefa nao encontrada")
    })
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
    @Operation(summary = "Altera o tarefa por id" , description = "Rota altera o tarefa por id e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alterado com sucesso"),
            @ApiResponse(responseCode = "404" , description = "Tarefa nao encontrado")
    })
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
    @Operation(summary = "Deleta o tarefa por id" , description = "Faz a deleçao de um tarefa por id passado na requisiçao")
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
    @Operation(summary = "Criar um novo tarefa" , description = "Rota cria um novo tarefa e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criado com sucesso"),
            @ApiResponse(responseCode = "400" , description = "Erro na criaçao do tarefa")
    })
    public ResponseEntity<String> criarTarefa(@RequestBody TarefasDTO tarefasDTO){
        TarefasDTO novaTarefa = tarefaService.criarTarefa(tarefasDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa criada com sucesso: "+ novaTarefa.getNome());


    }


}
