package com.iaaj.utilerias;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

public class ProcesoGeneral {
	
	private static final Logger logger = Logger.getLogger("ProcesoGeneral");
	private String stringVacio = "empty";
	private Random random = new Random();
	
	//Metodo para formatear fecha
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	//Metodo para registrar un error en el log
	public void getMensajeError(BindingResult result, String origen) {	
		result.getAllErrors().forEach(error -> 
			logger.log(Level.SEVERE, "Ocurrio un error en: {0}, detalle: {1}",
					new Object[] { origen, error.getDefaultMessage() })
		);
	}
	
	//Metodo para guardar un archivo
	public String guardarArchivo(MultipartFile multiPart, String ruta) {
		//Obtener el nombre original del archivo
		String nombreOriginal = formatearNombreArchivo(multiPart.getOriginalFilename());
		//Intentar guardar el archivo
		try {
            //Formar el nombre del archivo
            File imageFile = new File(ruta + nombreOriginal);
            //Guardar el archivo en el servidor
            multiPart.transferTo(imageFile);
            logger.log(Level.INFO, "El archivo {0} se guardo correctamente", nombreOriginal);
            return nombreOriginal;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al guardar archivo: {0}", e.getMessage());
            return stringVacio;
        }
	}
	
	//Metodo para formatear el nombre del archivo
	public String formatearNombreArchivo(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return stringVacio;
		}
		return randomAlphaNumeric(8) + cadena.replace(" ", "-");
	}
	
	/* Metodo para generar un nombre aleatorio de longitud N
	 * @param count: longitud del nombre
	 * @return: nombre
	 */
	public String randomAlphaNumeric(int count) {
		String stringCaracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (random.nextInt(stringCaracteres.length()));
			builder.append(stringCaracteres.charAt(character));
		}
		return builder.toString();
	}
	
}
