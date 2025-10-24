package com.harliton.refeicoes_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// DTO simples para representar a Categoria em listas
@Data
@AllArgsConstructor
public class CategoriaDTO {
     private Long id;
     private String titulo;
     private String descricao;
     private String imagemUrl;
}