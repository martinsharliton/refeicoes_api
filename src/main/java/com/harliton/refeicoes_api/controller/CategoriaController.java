package com.harliton.refeicoes_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harliton.refeicoes_api.dto.CategoriaCreateDTO;
import com.harliton.refeicoes_api.dto.CategoriaDTO;
import com.harliton.refeicoes_api.service.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias", description = "Operações relacionadas às categorias de receitas")
public class CategoriaController {

     private final CategoriaService categoriaService;

     // CORREÇÃO: Retorna List<CategoriaDTO>
     @GetMapping
     public List<CategoriaDTO> listarCategorias() {
          return categoriaService.getAllCategorias();
     }

     // CORREÇÃO: Retorna CategoriaDTO
     @GetMapping("/{id}")
     public CategoriaDTO buscarCategoria(@PathVariable Long id) {
          return categoriaService.getCategoriaById(id);
     }

     // CORREÇÃO: Recebe CategoriaCreateDTO
     @PostMapping
     public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaCreateDTO categoriaDTO) {
          CategoriaDTO novaCategoria = categoriaService.createCategoria(categoriaDTO);
          return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deletarCategoria(@PathVariable Long id) {
          categoriaService.deleteCategoria(id);
     }
}