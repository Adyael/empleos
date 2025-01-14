package com.iaaj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class ListasBusquedaGeneralImpl implements IListasBusquedaGeneral{
	
	@Autowired
	private ICategoriasService serviceCategorias;
	@Autowired
	private IVacantesService serviceVacantes;

	@Override
	public void getCategorias(Model model) {
		model.addAttribute("listCategorias", serviceCategorias.buscarTodas());
	}

	@Override
	public void getvacantes(Model model) {
		model.addAttribute("listVacantes", serviceVacantes.buscarTodas());
	}
	
}
