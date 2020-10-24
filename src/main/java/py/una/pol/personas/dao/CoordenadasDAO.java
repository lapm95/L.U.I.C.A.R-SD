package py.una.pol.personas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import py.una.pol.personas.model.Persona;
import py.una.pol.personas.model.Coordenada;

public class CoordenadasDAO {
	
	 @Inject
	    private Logger log;
	    
		/**
		 * 
		 * @param condiciones 
		 * @return
		 */
	 public List<Coordenada> seleccionar() {
			String query = "SELECT id_viaje, id_vehiculo, fecha, hora, latitud, longitud FROM coordenada ORDER BY id_viaje";
			
			List<Coordenada> lista = new ArrayList<Coordenada>();
			
			Connection conn = null; 
	        try 
	        {
	        	conn = Bd.connect();
	        	ResultSet rs = conn.createStatement().executeQuery(query);

	        	while(rs.next()) {
	        		Coordenada v = new Coordenada();
	        		v.setId_coordenada(rs.getInt(1));
	        		v.setId_vehiculo(rs.getInt(2));
	        		v.setFecha(rs.getDate(3));
	        		v.setHora(rs.getTime(4));
	        		v.setLatitud(rs.getDouble(5));
	        		v.setLongitud(rs.getDouble(6));
	        		
	        		lista.add(v);
	        	}
	        	
	        } catch (SQLException ex) {
	            log.severe("Error en la seleccion: " + ex.getMessage());
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }
			return lista;

		}
	 
	 public Coordenada seleccionarPorVehiculo(Integer id_vehiculo) {
			String SQL = "SELECT id_viaje, id_vehiculo, fecha, hora, latitud, longitud FROM coordenada WHERE id_viaje = ?";
			
			Coordenada v = null;
			
			Connection conn = null; 
	        try 
	        {
	        	conn = Bd.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(SQL);
	        	pstmt.setInt(1, id_vehiculo);
	        	
	        	ResultSet rs = pstmt.executeQuery();

	        	while(rs.next()) {
	        		v = new Coordenada();
	        		v.setId_coordenada(rs.getInt(1));
	        		v.setId_vehiculo(rs.getInt(2));
	        		v.setFecha(rs.getDate(3));
	        		v.setHora(rs.getTime(4));
	        		v.setLatitud(rs.getDouble(5));
	        		v.setLongitud(rs.getDouble(6));
	        	}
	        	
	        } catch (SQLException ex) {
	        	log.severe("Error en la seleccion: " + ex.getMessage());
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }
			return v;

		}
	 
	 public long insertar(Coordenada v) throws SQLException {

	        String SQL = "INSERT INTO coordenada(id_viaje, id_vehiculo, fecha, hora, latitud, longitud) "
	                + "VALUES(?,?,?,?,?,?)";
	 
	        long id = 0;
	        Connection conn = null;
	        
	        try 
	        {
	        	conn = Bd.connect();
	        	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
	            pstmt.setInt(1, v.getId_coordenada());
	            pstmt.setInt(2, v.getId_vehiculo());
	            pstmt.setDate(3, v.getFecha());
	            pstmt.setTime(3, v.getHora());
	            pstmt.setDouble(3, v.getLatitud());
	            pstmt.setDouble(3, v.getLongitud());
	            
	 
	            int affectedRows = pstmt.executeUpdate();
	            // check the affected rows 
	            if (affectedRows > 0) {
	                // get the ID back
	                try (ResultSet rs = pstmt.getGeneratedKeys()) {
	                    if (rs.next()) {
	                        id = rs.getLong(1);
	                    }
	                } catch (SQLException ex) {
	                	throw ex;
	                }
	            }
	        } catch (SQLException ex) {
	        	throw ex;
	        }
	        finally  {
	        	try{
	        		conn.close();
	        	}catch(Exception ef){
	        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
	        	}
	        }
	        	
	        return id;
	    	
	    	
	    }

	public List<Coordenada> obtenerCercanos(Double latitud, Double longitud) {
		Double limite = 20.00;			//la distancia maxima considerada
		
		String SQL = "SELECT id_viaje, id_vehiculo, fecha, hora, latitud, longitud FROM coordenada WHERE sqrt(power(latitud-?,2)+power(longitud-?,2)) > ?";
		
		List<Coordenada> cercanos = new ArrayList<Coordenada>();
		
		Connection conn = null;
		
		try 
        {
        	conn = Bd.connect();
        	PreparedStatement pstmt = conn.prepareStatement(SQL);
        	pstmt.setDouble(1, latitud);
        	pstmt.setDouble(2, longitud);
        	pstmt.setDouble(3, limite);
        	
        	ResultSet rs = pstmt.executeQuery();

        	while(rs.next()) {
        		Coordenada c = new Coordenada();
        		c.setId_coordenada(rs.getInt(1));
        		c.setId_vehiculo(rs.getInt(2));
        		c.setFecha(rs.getDate(3));
        		c.setHora(rs.getTime(4));
        		c.setLatitud(rs.getDouble(5));
        		c.setLongitud(rs.getDouble(6));
        		
        		cercanos.add(c);
        	}
        	
        } catch (SQLException ex) {
        	log.severe("Error en la seleccion: " + ex.getMessage());
        }
        finally  {
        	try{
        		conn.close();
        	}catch(Exception ef){
        		log.severe("No se pudo cerrar la conexion a BD: "+ ef.getMessage());
        	}
        }
		return cercanos;
	}	 

}
