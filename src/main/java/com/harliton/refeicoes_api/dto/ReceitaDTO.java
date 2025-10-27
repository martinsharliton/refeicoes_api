package com.harliton.refeicoes_api.dto;

import java.util.ArrayList; // Importe
import java.util.List;

import com.harliton.refeicoes_api.enumeration.Dificuldade;

import lombok.Data;

@Data
public class ReceitaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String imagemUrl;
    private int tempoPreparo;
    private Dificuldade dificuldade;
    private List<String> ingredientes = new ArrayList<>();
    private List<String> passos = new ArrayList<>();
    private CategoriaDTO categoria;
}