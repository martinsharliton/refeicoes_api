package com.harliton.refeicoes_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harliton.refeicoes_api.dto.CategoriaDTO;
import com.harliton.refeicoes_api.dto.ReceitaCreateDTO;
import com.harliton.refeicoes_api.dto.ReceitaDTO;
import com.harliton.refeicoes_api.exception.ResourceNotFoundException;
import com.harliton.refeicoes_api.model.Categoria;
import com.harliton.refeicoes_api.model.Receita;
import com.harliton.refeicoes_api.repository.CategoriaRepository;
import com.harliton.refeicoes_api.repository.ReceitaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReceitaService {

     private final ReceitaRepository receitaRepository;
     private final CategoriaRepository categoriaRepository;

     public ReceitaDTO getReceitaById(Long id) {
          Receita receita = receitaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com id: " + id));
          return convertToReceitaDTO(receita);
     }

     public List<ReceitaDTO> getReceitasByCategoria(Long categoriaId) {
          return receitaRepository.findByCategoriaId(categoriaId)
                    .stream()
                    .map(this::convertToReceitaDTO)
                    .collect(Collectors.toList());
     }

     // CORREÇÃO: Recebe o DTO de criação
     public ReceitaDTO createReceita(Long categoriaId, ReceitaCreateDTO dto) {
          // 1. Busca a Entidade Categoria
          Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(
                              () -> new ResourceNotFoundException("Categoria não encontrada com id: " + categoriaId));

          // 2. Converte o DTO para Entidade Receita
          Receita receita = new Receita();
          receita.setTitulo(dto.getTitulo());
          receita.setDescricao(dto.getDescricao());
          receita.setImagemUrl(dto.getImagemUrl());
          receita.setTempoPreparo(dto.getTempoPreparo());
          receita.setDificuldade(dto.getDificuldade());
          receita.setIngredientes(dto.getIngredientes());
          receita.setPassos(dto.getPassos());

          // 3. Linka a Categoria à Receita
          receita.setCategoria(categoria);

          // 4. Salva a nova Receita
          Receita receitaSalva = receitaRepository.save(receita);

          // 5. Retorna o DTO da receita criada
          return convertToReceitaDTO(receitaSalva);
     }

     public void deleteReceita(Long id) {
          if (!receitaRepository.existsById(id)) {
               throw new ResourceNotFoundException("Receita não encontrada com id: " + id);
          }
          receitaRepository.deleteById(id);
     }

     // --- Métodos de Mapeamento ---

     private ReceitaDTO convertToReceitaDTO(Receita receita) {
          ReceitaDTO dto = new ReceitaDTO();
          dto.setId(receita.getId());
          dto.setTitulo(receita.getTitulo());
          dto.setDescricao(receita.getDescricao());
          dto.setImagemUrl(receita.getImagemUrl());
          dto.setTempoPreparo(receita.getTempoPreparo());
          dto.setDificuldade(receita.getDificuldade());
          dto.setIngredientes(receita.getIngredientes());
          dto.setPassos(receita.getPassos());

          // Mapeia a Categoria aninhada para seu DTO
          if (receita.getCategoria() != null) {
               CategoriaDTO catDto = new CategoriaDTO();
               catDto.setId(receita.getCategoria().getId());
               catDto.setTitulo(receita.getCategoria().getTitulo());
               catDto.setDescricao(receita.getCategoria().getDescricao());
               catDto.setImagemUrl(receita.getCategoria().getImagemUrl());
               dto.setCategoria(catDto);
          }
          return dto;
     }
}