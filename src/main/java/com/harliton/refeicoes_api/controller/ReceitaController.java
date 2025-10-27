package com.harliton.refeicoes_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harliton.refeicoes_api.dto.ItemDTO;
import com.harliton.refeicoes_api.dto.ItemUpdateDTO;
import com.harliton.refeicoes_api.dto.ReceitaCreateDTO;
import com.harliton.refeicoes_api.dto.ReceitaDTO;
import com.harliton.refeicoes_api.service.ReceitaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Receitas", description = "Operações relacionadas às receitas")
public class ReceitaController {

     private final ReceitaService receitaService;

     // Recebe ReceitaCreateDTO
     @PostMapping("/categorias/{categoriaId}/receitas")
     public ResponseEntity<ReceitaDTO> criarReceita(
               @PathVariable Long categoriaId,
               @RequestBody ReceitaCreateDTO receitaDTO) {
          ReceitaDTO novaReceita = receitaService.createReceita(categoriaId, receitaDTO);
          return new ResponseEntity<>(novaReceita, HttpStatus.CREATED);
     }

     // Retorna List<ReceitaDTO>
     @GetMapping("/categorias/{categoriaId}/receitas")
     public List<ReceitaDTO> listarReceitasDaCategoria(@PathVariable Long categoriaId) {
          return receitaService.getReceitasByCategoria(categoriaId);
     }

     // Retorna ReceitaDTO
     @GetMapping("/receitas/{id}")
     public ReceitaDTO buscarReceita(@PathVariable Long id) {
          return receitaService.getReceitaById(id);
     }

     @DeleteMapping("/receitas/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deletarReceita(@PathVariable Long id) {
          receitaService.deleteReceita(id);
     }

     // Dentro da classe ReceitaController...

     // --- ENDPOINTS PARA GERENCIAR ITENS DA RECEITA ---

     // ANTES: public ReceitaDTO adicionarIngrediente(...)
     @PostMapping("/receitas/{id}/ingredientes")
     public List<String> adicionarIngrediente(
               @PathVariable Long id,
               @RequestBody ItemDTO itemDTO) {

          return receitaService.addIngrediente(id, itemDTO);
     }

     // ANTES: public ReceitaDTO adicionarPasso(...)
     @PostMapping("/receitas/{id}/passos")
     public List<String> adicionarPasso(
               @PathVariable Long id,
               @RequestBody ItemDTO itemDTO) {

          return receitaService.addPasso(id, itemDTO);
     }

     // ANTES: public ReceitaDTO removerIngrediente(...)
     @DeleteMapping("/receitas/{id}/ingredientes")
     public List<String> removerIngrediente(
               @PathVariable Long id,
               @RequestBody ItemDTO itemDTO) {

          return receitaService.removeIngrediente(id, itemDTO);
     }

     // ANTES: public ReceitaDTO removerPasso(...)
     @DeleteMapping("/receitas/{id}/passos")
     public List<String> removerPasso(
               @PathVariable Long id,
               @RequestBody ItemDTO itemDTO) {

          return receitaService.removePasso(id, itemDTO);
     }

     // ANTES: public ReceitaDTO atualizarIngrediente(...)
     @PutMapping("/receitas/{id}/ingredientes")
     public List<String> atualizarIngrediente(
               @PathVariable Long id,
               @RequestBody ItemUpdateDTO itemUpdateDTO) {

          return receitaService.updateIngrediente(id, itemUpdateDTO);
     }

     // ANTES: public ReceitaDTO atualizarPasso(...)
     @PutMapping("/receitas/{id}/passos")
     public List<String> atualizarPasso(
               @PathVariable Long id,
               @RequestBody ItemUpdateDTO itemUpdateDTO) {

          return receitaService.updatePasso(id, itemUpdateDTO);
     }

     @GetMapping("/receitas/{id}/ingredientes")
     public List<String> listarIngredientes(@PathVariable Long id) {
          return receitaService.getIngredientes(id);
     }

     // Endpoint para LISTAR todos os passos de UMA receita
     @GetMapping("/receitas/{id}/passos")
     public List<String> listarPassos(@PathVariable Long id) {
          return receitaService.getPassos(id);
     }
}