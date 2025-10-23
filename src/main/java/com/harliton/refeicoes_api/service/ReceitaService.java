package com.harliton.refeicoes_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.harliton.refeicoes_api.enumeration.Dificuldade;
import com.harliton.refeicoes_api.model.Categoria;
import com.harliton.refeicoes_api.model.Receita;
import com.harliton.refeicoes_api.repository.CategoriaRepository;
import com.harliton.refeicoes_api.repository.ReceitaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaService {

     private final ReceitaRepository receitaRepository;
     private final CategoriaRepository categoriaRepository; // Precisamos para associar

     public List<Receita> getAllReceitas() {
          return receitaRepository.findAll();
     }

     public Receita getReceitaById(Long id) {
          return receitaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Receita não encontrada com id: " + id));
     }

     public List<Receita> getReceitasByCategoria(Long categoriaId) {
          return receitaRepository.findByCategoriaId(categoriaId);
     }

     public List<Receita> getReceitasByDificuldade(Dificuldade dificuldade) {
          return receitaRepository.findByDificuldade(dificuldade);
     }

     public Receita createReceita(Long categoriaId, Receita receita) {
          // 1. Busca a Categoria à qual esta receita pertencerá
          Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + categoriaId));

          // 2. Associa a categoria à receita
          receita.setCategoria(categoria);
          receita.setId(null);

          // 3. Salva a receita
          return receitaRepository.save(receita);
     }

     public void deleteReceita(Long id) {
          Receita receita = getReceitaById(id);
          receitaRepository.delete(receita);
     }
}