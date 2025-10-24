package com.harliton.refeicoes_api.dto;

import com.harliton.refeicoes_api.enumeration.Dificuldade;
import lombok.Data;
import java.util.List;

@Data
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