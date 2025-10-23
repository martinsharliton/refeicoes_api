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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harliton.refeicoes_api.enumeration.Dificuldade;
import com.harliton.refeicoes_api.model.Receita;
import com.harliton.refeicoes_api.service.ReceitaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReceitaController {

     private final ReceitaService receitaService;

     // Cria uma receita DENTRO de uma categoria
     @PostMapping("/categorias/{categoriaId}/receitas")
     public ResponseEntity<Receita> criarReceita(
               @PathVariable Long categoriaId,
               @RequestBody Receita receita) {
          Receita novaReceita = receitaService.createReceita(categoriaId, receita);
          return new ResponseEntity<>(novaReceita, HttpStatus.CREATED);
     }

     // Lista todas as receitas de UMA categoria
     @GetMapping("/categorias/{categoriaId}/receitas")
     public List<Receita> listarReceitasDaCategoria(@PathVariable Long categoriaId) {
          return receitaService.getReceitasByCategoria(categoriaId);
     }

     // Lista TODAS as receitas, independentemente da categoria
     @GetMapping("/receitas")
     public List<Receita> listarTodasReceitas() {
          return receitaService.getAllReceitas();
     }

     // Busca uma receita específica pelo ID dela
     @GetMapping("/receitas/{id}")
     public Receita buscarReceita(@PathVariable Long id) {
          return receitaService.getReceitaById(id);
     }

     // Busca receitas por dificuldade (ex: /api/receitas/search?dificuldade=FACIL)
     @GetMapping("/receitas/search")
     public List<Receita> buscarPorDificuldade(@RequestParam Dificuldade dificuldade) {
          return receitaService.getReceitasByDificuldade(dificuldade);
     }

     @DeleteMapping("/receitas/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deletarReceita(@PathVariable Long id) {
          receitaService.deleteReceita(id);
     }
}