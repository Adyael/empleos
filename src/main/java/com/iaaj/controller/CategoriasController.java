package com.iaaj.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iaaj.model.Categoria;
import com.iaaj.service.ICategoriasService;
import com.iaaj.utilerias.ProcesoGeneral;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/categorias")
public class CategoriasController extends ProcesoGeneral {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	private static final Logger logger = Logger.getLogger(CategoriasController.class.getName());
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Categoria> categorias = serviceCategorias.buscarTodas();
		model.addAttribute("categorias", categorias);
		return "categorias/listCategorias";
	}

	@GetMapping("/create")
	public String crear(Categoria categoria) {
		return "categorias/formCategoria";
	}

	@PostMapping("/save")
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			getMensajeError(result, VacantesController.class.getName());
			return "categorias/formCategoria";
		}
		logger.info("Procesando categoria...");
		serviceCategorias.guardar(categoria);
		attributes.addFlashAttribute("msg","Registro Guardado");
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		logger.log(Level.INFO, "Borrando categoria con id: {0}", idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") Integer idVacante, Model model) {
		logger.log(Level.INFO, "Id categoria: {0}", idVacante);
		//Buscar los detalles de la categoria en la BD...
		Categoria categoria = serviceCategorias.buscarPorId(idVacante);
		model.addAttribute("categoria", categoria);
		return "detalle";
	}
	
}
