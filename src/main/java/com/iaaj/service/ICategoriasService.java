package com.iaaj.service;

import java.util.List;

import com.iaaj.model.Categoria;

public interface ICategoriasService {
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer id);
	void guardar(Categoria categoria);
}
