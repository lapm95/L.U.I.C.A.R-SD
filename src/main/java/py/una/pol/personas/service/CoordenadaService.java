package py.una.pol.personas.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.personas.dao.CoordenadasDAO;
import py.una.pol.personas.model.Coordenada;
import py.una.pol.personas.model.Persona;

import java.util.List;
import java.util.logging.Logger;

//The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class CoordenadaService {
	
	@Inject
    private Logger log;

    @Inject
    private CoordenadasDAO dao;
    
    public void crear(Coordenada c) throws Exception {
        log.info("Creando Coordenada: " + c.getId_coordenada() + " " + c.getId_vehiculo() + " " 
        		+ c.getFecha() + " " + c.getHora() + " " + c.getLatitud() + " " + c.getLongitud());
        try {
        	dao.insertar(c);
        }catch(Exception e) {
        	log.severe("ERROR al crear coordenada: " + e.getLocalizedMessage() );
        	throw e;
        }
        log.info("Coordenada creada con éxito: " + c.getId_coordenada() + " " + c.getId_vehiculo() + " " 
        		+ c.getFecha() + " " + c.getHora() + " " + c.getLatitud() + " " + c.getLongitud() );
    }
    
    public List<Coordenada> seleccionar() {
    	return dao.seleccionar();
    }
    
    public Coordenada seleccionarPorVehiculo(Integer id_vehiculo) {
    	return dao.seleccionarPorVehiculo(id_vehiculo);
    }

	public List<Coordenada> obtenerCercanos(Double latitud, Double longitud) {
		List<Coordenada> cercanos = dao.obtenerCercanos(latitud, longitud);			
		return cercanos;
	}

}
