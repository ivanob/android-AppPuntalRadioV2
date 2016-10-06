package com.ivanob.puntalradio.model;

import java.util.HashMap;
import java.util.Map;

public class RadioProgram {
	private String nombre;
	private String nombreLargo;
	private String ficheroDesc;
	private int idLogo;
	private String descripcion;
	private String horarioEmision;
	private String horarioRedifusion;
	private String presentador;
	private String colaborador;
	private Map <String, String> media = new HashMap<String, String>();

	public String getHorarioEmision() {
		return horarioEmision;
	}

	public void setHorarioEmision(String horarioEmision) {
		this.horarioEmision = horarioEmision;
	}
	public String getHorarioRedifusion() {
		return horarioRedifusion;
	}

	public void setHorarioRedifusion(String horarioRedifusion) {
		this.horarioRedifusion = horarioRedifusion;
	}

	public String getPresentador() {
		return presentador;
	}

	public void setPresentador(String presentador) {
		this.presentador = presentador;
	}

	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}


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

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}
	
}
