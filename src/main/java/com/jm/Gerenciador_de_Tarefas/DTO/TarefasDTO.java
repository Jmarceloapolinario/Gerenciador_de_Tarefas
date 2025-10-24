package com.jm.Gerenciador_de_Tarefas.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefasDTO {

    private Long id;
    private String nome;
    private String status;
    private Date data;

}
