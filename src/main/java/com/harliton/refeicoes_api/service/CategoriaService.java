package com.harliton.refeicoes_api.service;

import com.harliton.refeicoes_api.model.Categoria;
import com.harliton.refeicoes_api.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        // Retorna a categoria ou lança uma exceção se não for encontrada
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
    }

    public Categoria createCategoria(Categoria categoria) {
        // Garante que não estamos atualizando uma categoria com ID
        categoria.setId(null); 
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        // Verifica se existe antes de deletar
        Categoria categoria = getCategoriaById(id);
        // Graças ao "cascade = CascadeType.ALL" na entidade Categoria,
        // deletar a categoria também deletará todas as receitas associadas.
        categoriaRepository.delete(categoria);
    }
}