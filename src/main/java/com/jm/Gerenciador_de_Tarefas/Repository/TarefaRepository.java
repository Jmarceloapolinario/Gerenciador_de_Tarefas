package com.jm.Gerenciador_de_Tarefas.Repository;

import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
    List<TarefaModel> findByData(LocalDate data);


    List<TarefaModel> findByStatus(String status);

}
