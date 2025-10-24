package com.jm.Gerenciador_de_Tarefas.Mapper;

import com.jm.Gerenciador_de_Tarefas.DTO.TarefasDTO;
import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import org.springframework.stereotype.Component;

@Component
public class TarefasMapper {
    public TarefaModel map(TarefasDTO  tarefasDTO){
        TarefaModel tarefaModel = new TarefaModel();
        tarefaModel.setId(tarefasDTO.getId());
        tarefaModel.setNome(tarefasDTO.getNome());
        tarefaModel.setStatus(tarefasDTO.getStatus());
        tarefaModel.setData(tarefasDTO.getData());

        return tarefaModel;

    }

    public TarefasDTO map(TarefaModel tarefaModel){
        TarefasDTO tarefasDTO = new TarefasDTO();
        tarefasDTO.setId(tarefaModel.getId());
        tarefasDTO.setNome(tarefaModel.getNome());
        tarefasDTO.setStatus(tarefaModel.getStatus());
        tarefasDTO.setData(tarefaModel.getData());

        return tarefasDTO;
    }
}
