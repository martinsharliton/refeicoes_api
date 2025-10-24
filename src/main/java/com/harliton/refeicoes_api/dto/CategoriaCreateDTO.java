package com.harliton.refeicoes_api.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoriaCreateDTO {
     private String titulo;
     private String descricao;
     private String imagemUrl;
     // Permite criar receitas ao mesmo tempo, se a lista for enviada
     private List<ReceitaCreateDTO> receitas;
}