package com.harliton.refeicoes_api.dto;

import java.util.List;

import com.harliton.refeicoes_api.enumeration.Dificuldade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String imagemUrl;
    private int tempoPreparo;
    private Dificuldade dificuldade;
    private List<String> ingredientes;
    private List<String> passos;
    private CategoriaDTO categoria;
}