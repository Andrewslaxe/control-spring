package com.api.backspring.models;

import jakarta.persistence.*;
import lombok.Data;

@Table
@Data
@Entity(name = "pacientes")
public class Paciente {
	@Id
	@Column(unique = true)
	private Long id;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String apellido;
	@Column(nullable = false)
	private Integer edad;
	@Column(nullable = false)
	private String telefono;
}
