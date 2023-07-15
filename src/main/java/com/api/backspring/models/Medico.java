package com.api.backspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Data
@Entity(name = "medicos")
public class Medico {
	@Id
	@Column(unique = true)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@Column(nullable = false)
	private String correo;
	@Column(nullable = false)
	private String consultorio;
	@Column(nullable = false)
	private String especialidad;

	public Medico() {
	}
}
