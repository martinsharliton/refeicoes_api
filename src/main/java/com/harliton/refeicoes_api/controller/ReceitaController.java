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

     // CORREÇÃO: Recebe ReceitaCreateDTO
     @PostMapping("/categorias/{categoriaId}/receitas")
     public ResponseEntity<ReceitaDTO> criarReceita(
               @PathVariable Long categoriaId,
               @RequestBody ReceitaCreateDTO receitaDTO) {
          ReceitaDTO novaReceita = receitaService.createReceita(categoriaId, receitaDTO);
          return new ResponseEntity<>(novaReceita, HttpStatus.CREATED);
     }

     // CORREÇÃO: Retorna List<ReceitaDTO>
     @GetMapping("/categorias/{categoriaId}/receitas")
     public List<ReceitaDTO> listarReceitasDaCategoria(@PathVariable Long categoriaId) {
          return receitaService.getReceitasByCategoria(categoriaId);
     }

     // CORREÇÃO: Retorna ReceitaDTO
     @GetMapping("/receitas/{id}")
     public ReceitaDTO buscarReceita(@PathVariable Long id) {
          return receitaService.getReceitaById(id);
     }

     @DeleteMapping("/receitas/{id}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void deletarReceita(@PathVariable Long id) {
          receitaService.deleteReceita(id);
     }

     // Nota: O endpoint de busca por dificuldade não foi incluído nos DTOs/Serviços
     // mas pode ser adicionado seguindo o mesmo padrão.
}