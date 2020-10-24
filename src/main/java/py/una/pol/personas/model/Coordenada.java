package py.una.pol.personas.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Coordenada implements Serializable {
	
	private Integer id_coordenada;
	private Integer id_vehiculo;
	private Date fecha;
	private Time hora;
	private Double latitud;
	private Double longitud;
	
	public Coordenada() {
		this.id_vehiculo = 1;
		this.fecha = this.getFechaEnDate(LocalDate.now());
		this.hora = this.getHoraEnTime(LocalTime.now());
	}
	
	public Coordenada(Integer id_viaje, Date fecha, Time hora, Double latitud, Double longitud){
		this.id_coordenada = id_viaje;
		this.id_vehiculo = 1;
		this.fecha = fecha;
		this.hora = hora;
		this.latitud = latitud;
		this.longitud = longitud;		
	}
	
	public Integer getId_vehiculo() {
		return id_vehiculo;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Time getHora() {
		return hora;
	}
	
	public Integer getId_coordenada() {
		return id_coordenada;
	}
	
	public Double getLatitud() {
		return latitud;
	}
	
	public Double getLongitud() {
		return longitud;
	}
	
	public void setHora(Time hora) {
		this.hora = hora;
	}
	
	public void setId_vehiculo(Integer id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	
	public void setId_coordenada(Integer id_viaje) {
		this.id_coordenada = id_viaje;
	}
	
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Date getFechaEnDate(LocalDate fecha) {
		Date date = Date.valueOf(fecha);
		return date;
	}
	
	public Time getHoraEnTime(LocalTime hora) {
		Time time = Time.valueOf(hora);
		return time;
	}

	
	
	
	
}
