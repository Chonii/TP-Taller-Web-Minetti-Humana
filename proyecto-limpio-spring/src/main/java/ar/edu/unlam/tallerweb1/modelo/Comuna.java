package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import antlr.collections.List;

@Entity
public class Comuna {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@ManyToOne
	private ArrayList<Barrio> listaBarrios;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Barrio> getListaBarrios() {
		return listaBarrios;
	}
	public void setListaBarrios(ArrayList<Barrio> listaBarrios) {
		this.listaBarrios = listaBarrios;
	}
	
	
}
