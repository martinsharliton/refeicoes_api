package com.harliton.refeicoes_api.service;

import java.util.List;
import java.util.stream.Collectors; // <-- IMPORTE STREAMS

import org.springframework.stereotype.Service;

import com.harliton.refeicoes_api.dto.CategoriaDTO; // <-- IMPORTE O DTO
import com.harliton.refeicoes_api.model.Categoria;
import com.harliton.refeicoes_api.model.Receita;
import com.harliton.refeicoes_api.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

     private final CategoriaRepository categoriaRepository;

     // Altere o tipo de retorno deste método
     public List<CategoriaDTO> getAllCategorias() {
          return categoriaRepository.findAll()
                    .stream()
                    .map(this::converterParaDTO) // Converte cada Categoria
                    .collect(Collectors.toList());
     }

     // Método auxiliar privado para a conversão
     private CategoriaDTO converterParaDTO(Categoria categoria) {
          return new CategoriaDTO(
                    categoria.getId(),
                    categoria.getTitulo(),
                    categoria.getDescricao(),
                    categoria.getImagemUrl());
     }

     // Os outros métodos (getById, create, delete) podem continuar
     // retornando a entidade 'Categoria' completa, se você quiser.

     public Categoria getCategoriaById(Long id) {
          return categoriaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
     }

     public Categoria createCategoria(Categoria categoria) {
          categoria.setId(null);

          // ADICIONE ISSO para a Solução B funcionar:
          if (categoria.getReceitas() != null) {
               for (Receita receita : categoria.getReceitas()) {
                    receita.setCategoria(categoria); // Linka a receita de volta à categoria
               }
          }

          return categoriaRepository.save(categoria);
     }

     public void deleteCategoria(Long id) {
          Categoria categoria = getCategoriaById(id);
          categoriaRepository.delete(categoria);
     }
}