package com.api.backspring.models;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CitaRequest {
	private Long pacienteId;
	private Long medicoId;
	private LocalDate fecha;
	private LocalTime hora;
}
