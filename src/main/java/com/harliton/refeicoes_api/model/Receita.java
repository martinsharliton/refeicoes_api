package com.harliton.refeicoes_api.model;

import java.util.ArrayList;
import java.util.List;

import com.harliton.refeicoes_api.enumeration.Dificuldade;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    // (Opcional, mas recomendado) Mude para TEXT
    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String imagemUrl;

    private int tempoPreparo;

    @Enumerated(EnumType.STRING)
    private Dificuldade dificuldade;

    // CORREÇÃO: Inicialize as listas
    @ElementCollection
    private List<String> ingredientes = new ArrayList<>();

    // CORREÇÃO: Inicialize as listas
    @ElementCollection
    private List<String> passos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}