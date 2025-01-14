package com.iaaj.service;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.iaaj.model.Categoria;

@Service
public class CategoriasServiceImpl implements ICategoriasService{
	
	private static final Logger logger = Logger.getLogger("CategoriasServiceImpl");
	
	private List<Categoria> lista = null;
	
	public CategoriasServiceImpl() {
		lista = new LinkedList<>();
		
		Categoria cat1 = new Categoria();
		cat1.setId(1);
		cat1.setNombre("Programacion");
		cat1.setDescripcion("Categoria para programadores");
		lista.add(cat1);
		
		Categoria cat2 = new Categoria();
		cat2.setId(2);
		cat2.setNombre("Diseño");
		cat2.setDescripcion("Categoria para diseñadores");
		lista.add(cat2);
		
		Categoria cat3 = new Categoria();
		cat3.setId(3);
		cat3.setNombre("Redes");
		cat3.setDescripcion("Categoria para administradores de redes");
		lista.add(cat3);
		
		Categoria cat4 = new Categoria();
		cat4.setId(4);
		cat4.setNombre("Soporte");
		cat4.setDescripcion("Categoria para personal de soporte tecnico");
		lista.add(cat4);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return lista;
	}

	@Override
	public Categoria buscarPorId(Integer id) {
		return lista.stream()
				.filter(categoria -> categoria.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void guardar(Categoria categoria) {
		int lastId = lista.get(lista.size() - 1).getId();
		categoria.setId(lastId + 1);
		lista.add(categoria);
		logger.log(Level.INFO, "Categoria guardada: {0}", categoria);
	}

}
