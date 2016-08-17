package com.ivanob.puntalradio.model;

import java.util.HashMap;
import java.util.Map;

public class RadioProgram {
	private String nombre;
	private String nombreLargo;
	private String ficheroDesc;
	private int idLogo;
	private String descripcion;
	private String horario;
	private Map <String, String> media = new HashMap<String, String>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFicheroDesc() {
		return ficheroDesc;
	}

	public void setFicheroDesc(String ficheroDesc) {
		this.ficheroDesc = ficheroDesc;
	}
	
	public String getMediaURL(String service){
		return media.get(service);
	}
	
	public void addMediaURL(String service, String url){
		media.put(service,url);
	}
	
	public int getNumMediaURL(){
		return media.size();
	}

	public int getIdLogo() {
		return idLogo;
	}

	public void setIdLogo(int idLogo) {
		this.idLogo = idLogo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	
}
