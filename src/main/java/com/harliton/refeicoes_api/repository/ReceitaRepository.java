package com.harliton.refeicoes_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harliton.refeicoes_api.enumeration.Dificuldade;
import com.harliton.refeicoes_api.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
     // Spring Data JPA cria a query automaticamente pelo nome do método
    List<Receita> findByDificuldade(Dificuldade dificuldade);
    
    // Busca todas as receitas que pertencem a um ID de categoria
    List<Receita> findByCategoriaId(Long categoriaId);
}
