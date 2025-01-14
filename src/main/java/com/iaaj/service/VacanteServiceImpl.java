package com.iaaj.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.iaaj.model.Vacante;

@Service
public class VacanteServiceImpl implements IVacantesService{
	
	private static final Logger logger = Logger.getLogger("VacanteServiceImpl");
	
	private List<Vacante> lista = null;
	
	public VacanteServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<>();
        try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero de Sistemas");
			vacante1.setDescripcion("Se solicita Ingeniero de Sistemas para trabajar en empresa de tecnologia");
			vacante1.setFecha(sdf.parse("08-02-2021"));
			vacante1.setSalario(50000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			vacante1.setEstatus("Aprobada");
			lista.add(vacante1);

			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador General");
			vacante2.setDescripcion("Se solicita Contador General para trabajar en empresa de tecnologia");
			vacante2.setFecha(sdf.parse("09-02-2021"));
			vacante2.setSalario(9000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			vacante2.setEstatus("Aprobada");
			lista.add(vacante2);

			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Civil");
			vacante3.setDescripcion("Se solicita Ingeniero Civil para trabajar en empresa de tecnologia");
			vacante3.setFecha(sdf.parse("10-02-2021"));
			vacante3.setSalario(12000.0);
			vacante3.setDestacado(0);
			vacante3.setEstatus("Aprobada");
			lista.add(vacante3);

			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Arquitecto");
			vacante4.setDescripcion("Se solicita Arquitecto para trabajar en empresa de tecnologia");
			vacante4.setFecha(sdf.parse("12-02-2021"));
			vacante4.setSalario(11000.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa3.png");
			vacante4.setEstatus("Aprobada");
			lista.add(vacante4);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Vacante> buscarTodas() {
		
		return lista;
	}
	
	@Override
	public Vacante buscarPorId(Integer id) {
		return lista.stream()
				.filter(vacante -> vacante.getId().equals(id))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void guardar(Vacante vacante) {
		int lastId = lista.get(lista.size() - 1).getId();
		vacante.setId(lastId + 1);
		lista.add(vacante);
		logger.log(Level.INFO, "Vacante guardada: {0}", vacante);
	}
}
