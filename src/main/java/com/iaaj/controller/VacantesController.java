package com.iaaj.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iaaj.model.Vacante;
import com.iaaj.service.IListasBusquedaGeneral;
import com.iaaj.service.IVacantesService;
import com.iaaj.utilerias.ProcesoGeneral;

@Controller
@RequestMapping("/vacantes")
public class VacantesController extends ProcesoGeneral {
	
	@Value("${empleosapp.ruta.carga.imagenes}")
	private String ruta;
	
	@Autowired
	private IVacantesService serviceVacantes;
	@Autowired
	private IListasBusquedaGeneral listasGeneral;
	
	private static final Logger logger = Logger.getLogger("VacantesController");
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> vacantes = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", vacantes);
		
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/crear")
	public String crear(Vacante vacante, Model model) {
		//Recuperar la lista de categorias al modelo
		listasGeneral.getCategorias(model);
		return "vacantes/formVacante";
	}

	//Este metodo utiliza DataBinding de SpringBoot
	//Utilizar el objeto Vacante en lugar de los parametros individuales permite tener mas control sobre los datos
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, Model model,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		
		if(result.hasErrors()) {
			getMensajeError(result, VacantesController.class.getName());
			listasGeneral.getCategorias(model);
			return "vacantes/formVacante";
		}
		
		if (!multiPart.isEmpty()) {
			String nombreImagen = guardarArchivo(multiPart, ruta);
			
			if (!nombreImagen.equals("empty")) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}
		
		logger.log(Level.INFO, "Objeto {0}",vacante);
		serviceVacantes.guardar(vacante);
		attributes.addFlashAttribute("msg","Registro Guardado");
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		logger.log(Level.INFO, "Borrando vacante con id: {0}", idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") Integer idVacante, Model model) {
		logger.log(Level.INFO, "Id vacante: {0}", idVacante);
		//Buscar los detalles de la vacante en la BD...
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "detalle";
	}
	

}
