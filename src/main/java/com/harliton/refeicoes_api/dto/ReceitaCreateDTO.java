package com.harliton.refeicoes_api.dto;

import java.util.List;

import com.harliton.refeicoes_api.enumeration.Dificuldade;

import lombok.Data;

@Data
public class ReceitaCreateDTO {
     private String titulo;
     private String descricao;
     private String imagemUrl;
     private int tempoPreparo;
     private Dificuldade dificuldade;
     private List<String> ingredientes;
     private List<String> passos;
}