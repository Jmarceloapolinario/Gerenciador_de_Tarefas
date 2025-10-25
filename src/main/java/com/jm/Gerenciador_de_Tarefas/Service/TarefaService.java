package com.jm.Gerenciador_de_Tarefas.Service;

import com.jm.Gerenciador_de_Tarefas.DTO.TarefasDTO;
import com.jm.Gerenciador_de_Tarefas.Mapper.TarefasMapper;
import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import com.jm.Gerenciador_de_Tarefas.Repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    public List<TarefasDTO> listarTarefasDate(LocalDate data) {
        List<TarefaModel> tarefasData = tarefaRepository.findByData(data);
        return tarefasData.stream()
                .map(tarefasMapper::map)
                .collect(Collectors.toList());
    }

    public TarefasDTO criarTarefa(TarefasDTO tarefasDTO){
                TarefaModel tarefa =tarefasMapper.map(tarefasDTO);
                tarefa = tarefaRepository.save(tarefa);
                return tarefasMapper.map(tarefa);


    }
    public TarefasDTO listarTarefaPorId(Long id){
        Optional<TarefaModel> tarefaId = tarefaRepository.findById(id);
        return tarefaId.map(tarefasMapper::map).orElse(null);

    }
    public List<TarefasDTO> listarPorStatus(String status) {
        List<TarefaModel> tarefasStatus = tarefaRepository.findByStatus(status);
        return tarefasStatus.stream()
                .map(tarefasMapper::map)
                .collect(Collectors.toList());
    }

    public TarefasDTO alteraTarefas(Long id, TarefasDTO tarefasDTO ){
        Optional<TarefaModel> tarefaExistente = tarefaRepository.findById(id);
        if (tarefaExistente.isPresent()){
            TarefaModel tarefaAtualizada = tarefasMapper.map(tarefasDTO);
            tarefaAtualizada.setId(id);
            TarefaModel tarefaSalva = tarefaRepository.save(tarefaAtualizada);
            return tarefasMapper.map(tarefaSalva);
        }
        return null;
    }
    public void deletarTarefa(Long id){tarefaRepository.deleteById(id);}



}
