package com.jm.Gerenciador_de_Tarefas.Controller;


import com.jm.Gerenciador_de_Tarefas.DTO.TarefasDTO;
import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import com.jm.Gerenciador_de_Tarefas.Service.TarefaService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/criar")
    public ResponseEntity<String> criarTarefa(@RequestBody TarefasDTO tarefasDTO){
        TarefasDTO novaTarefa = tarefaService.criarTarefa(tarefasDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Tarefa criada com sucesso: "+ novaTarefa.getNome());


    }


}
