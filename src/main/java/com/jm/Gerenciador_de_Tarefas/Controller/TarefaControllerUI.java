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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/tarefas/ui")
public class TarefaControllerUI {

    private final TarefaService tarefaService;

    public TarefaControllerUI(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    @GetMapping("/listar")
    public String listartarefas(Model model){
        List<TarefasDTO> tarefas =tarefaService.listarTarefas();
        model.addAttribute("tarefas" , tarefas);
        return "index";


    }


    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("tarefa" , tarefaService.listarTarefaPorId(id));
        return "editar";
    }
    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, TarefasDTO tarefasDTO, RedirectAttributes ra){
        tarefaService.alteraTarefas(id, tarefasDTO);
        ra.addFlashAttribute("mensagem", "Tarefa atualizada com sucesso!");
        return "redirect:/tarefas/ui/listar";
    }
    @GetMapping("/deletar/{id}")
    public String deletarPorId(@PathVariable Long id){
        tarefaService.deletarTarefa(id);
        return "redirect:/tarefas/ui/listar";
    }



    @GetMapping("/criar")
    public String criarTarefa(Model model){
        model.addAttribute("tarefa" , new TarefasDTO());
        return "criar";


    }
    @PostMapping("/salvar")
    public String salvarTarefa(TarefasDTO tarefasDTO , RedirectAttributes redirectAttributes){
        tarefaService.criarTarefa(tarefasDTO);
        redirectAttributes.addFlashAttribute("mensagem" , "Tarefa adicionada com sucesso");
        return "redirect:/tarefas/ui/listar";
    }


}
