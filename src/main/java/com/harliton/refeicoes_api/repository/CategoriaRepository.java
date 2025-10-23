package com.harliton.refeicoes_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harliton.refeicoes_api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
