package com.harliton.refeicoes_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harliton.refeicoes_api.dto.CategoriaCreateDTO;
import com.harliton.refeicoes_api.dto.CategoriaDTO;
import com.harliton.refeicoes_api.exception.ResourceNotFoundException;
import com.harliton.refeicoes_api.model.Categoria;
import com.harliton.refeicoes_api.model.Receita;
import com.harliton.refeicoes_api.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

     private final CategoriaRepository categoriaRepository;

     // CORREÇÃO: Retorna DTOs, não Entidades
     public List<CategoriaDTO> getAllCategorias() {
          return categoriaRepository.findAll()
                    .stream()
                    .map(this::convertToCategoriaDTO)
                    .collect(Collectors.toList());
     }

     // CORREÇÃO: Retorna DTO e usa a Exceção customizada
     public CategoriaDTO getCategoriaById(Long id) {
          Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
          return convertToCategoriaDTO(categoria);
     }

     // CORREÇÃO: Recebe o DTO de criação
     public CategoriaDTO createCategoria(CategoriaCreateDTO dto) {
          Categoria categoria = new Categoria();
          categoria.setTitulo(dto.getTitulo());
          categoria.setDescricao(dto.getDescricao());
          categoria.setImagemUrl(dto.getImagemUrl());

          // CORREÇÃO: Esta é a lógica que faltava para o seu erro MismatchedInput
          // Se o DTO de Categoria trouxe Receitas, nós as criamos também.
          if (dto.getReceitas() != null && !dto.getReceitas().isEmpty()) {
               for (var receitaDTO : dto.getReceitas()) {
                    Receita receita = new Receita();
                    receita.setTitulo(receitaDTO.getTitulo());
                    receita.setDescricao(receitaDTO.getDescricao());
                    receita.setImagemUrl(receitaDTO.getImagemUrl());
                    receita.setTempoPreparo(receitaDTO.getTempoPreparo());
                    receita.setDificuldade(receitaDTO.getDificuldade());
                    receita.setIngredientes(receitaDTO.getIngredientes());
                    receita.setPassos(receitaDTO.getPassos());

                    // O link bidirecional (a "mágica" do JPA)
                    receita.setCategoria(categoria); // Linka a receita à categoria
                    categoria.getReceitas().add(receita); // Linka a categoria à receita
               }
          }

          Categoria categoriaSalva = categoriaRepository.save(categoria);
          return convertToCategoriaDTO(categoriaSalva);
     }

     public void deleteCategoria(Long id) {
          Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + id));
          categoriaRepository.delete(categoria);
     }

     // --- Métodos de Mapeamento ---

     private CategoriaDTO convertToCategoriaDTO(Categoria categoria) {
          CategoriaDTO dto = new CategoriaDTO();
          dto.setId(categoria.getId());
          dto.setTitulo(categoria.getTitulo());
          dto.setDescricao(categoria.getDescricao());
          dto.setImagemUrl(categoria.getImagemUrl());
          return dto;
     }
}