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

import com.harliton.refeicoes_api.dto.CategoriaDTO;
import com.harliton.refeicoes_api.model.Categoria;
import com.harliton.refeicoes_api.service.CategoriaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias") // Define o prefixo da URL para este controller
@Tag(name = "Categorias", description = "Categorias API. Contém operações relacionadas a categorias.")
public class CategoriaController {

     private final CategoriaService categoriaService;

     @GetMapping
     public List<CategoriaDTO> listarCategorias() {
          return categoriaService.getAllCategorias();
     }

     @GetMapping("/{id}")
     public Categoria buscarCategoria(@PathVariable Long id) {
          return categoriaService.getCategoriaById(id);
     }

     @PostMapping
     public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
          Categoria novaCategoria = categoriaService.createCategoria(categoria);
          return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deletarCategoria(@PathVariable Long id) {
          categoriaService.deleteCategoria(id);
     }
}