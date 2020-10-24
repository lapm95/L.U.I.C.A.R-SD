package py.una.pol.personas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;


@SuppressWarnings("serial")
@XmlRootElement
public class Vehiculo implements Serializable{
	int id_vehiculo;
	String tipo;
	
	
	public Vehiculo(){
	}

	public Vehiculo(int id_vehiculo, String tipo) {
		this.id_vehiculo = id_vehiculo;
		this.tipo = tipo;
	}
	
	public int getidVehiculo() {
		return id_vehiculo;
	}
	
	public void setidVehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}

