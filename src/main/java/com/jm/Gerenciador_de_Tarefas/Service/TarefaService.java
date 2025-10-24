package com.jm.Gerenciador_de_Tarefas.Service;

import com.jm.Gerenciador_de_Tarefas.DTO.TarefasDTO;
import com.jm.Gerenciador_de_Tarefas.Mapper.TarefasMapper;
import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import com.jm.Gerenciador_de_Tarefas.Repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefasMapper tarefasMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefasMapper tarefasMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefasMapper = tarefasMapper;
    }



    public List<TarefasDTO> listarTarefas(){
           List<TarefaModel> tarefas = tarefaRepository.findAll();
           return tarefas.stream()
                   .map(tarefasMapper::map)
                   .collect(Collectors.toList());
    }
    public TarefasDTO criarTarefa(TarefasDTO tarefasDTO){
                TarefaModel tarefa =tarefasMapper.map(tarefasDTO);
                tarefa = tarefaRepository.save(tarefa);
                return tarefasMapper.map(tarefa);


    }



}
