package py.una.pol.personas.rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import py.una.pol.personas.model.Coordenada;
import py.una.pol.personas.model.Persona;
import py.una.pol.personas.service.CoordenadaService;

/**
 * JAX-RS Example
 * <p/>
 * Esta clase produce un servicio RESTful para leer y escribir contenido de coordenadas
 */
@Path("/coordenadas")
@RequestScoped
public class CoordenadaRESTService {
	
	@Inject
    private Logger log;

    @Inject
    CoordenadaService coordenadaService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Coordenada> listar() {
        return coordenadaService.seleccionar();
    }
    
    @GET
    @Path("/id_vehiculo")
    @Produces(MediaType.APPLICATION_JSON)
    public Coordenada obtenerPorIdQuery(@QueryParam("id_vehiculo") Integer id_vehiculo) {
    	Coordenada c = coordenadaService.seleccionarPorVehiculo(id_vehiculo);
        if (c == null) {
        	log.info("obtenerPorId_vehiculo " + id_vehiculo + " no encontrado.");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        log.info("obtenerPorId_vehiculo " + id_vehiculo + " encontrada: " + c.getLatitud() + "," + c.getLongitud());
        return c;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Coordenada c) {

        Response.ResponseBuilder builder = null;

        try {
            coordenadaService.crear(c);
            // Create an "ok" response
            
            //builder = Response.ok();
            builder = Response.status(201).entity("Coordenada creada exitosamente");
            
        } catch (SQLException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("bd-error", e.getLocalizedMessage());
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

}
