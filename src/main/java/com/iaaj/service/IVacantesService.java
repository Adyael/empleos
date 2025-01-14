package com.iaaj.service;

import java.util.List;
import com.iaaj.model.Vacante;

public interface IVacantesService {
	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(Integer id);
	
	void guardar(Vacante vacante);
}
