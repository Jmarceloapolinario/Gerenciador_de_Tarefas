package com.jm.Gerenciador_de_Tarefas.Repository;

import com.jm.Gerenciador_de_Tarefas.Model.TarefaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaModel, Long> {
}
