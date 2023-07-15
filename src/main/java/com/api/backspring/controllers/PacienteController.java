package com.api.backspring.controllers;

import com.api.backspring.models.Paciente;
import com.api.backspring.services.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.backspring.models.constants.Constants.*;

@RestController
@RequestMapping(path = "/pacientes", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PacienteController {
	private final PacienteService pacienteService;
	public final Logger logger = LoggerFactory.getLogger(PacienteController.class);

	@Autowired
	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	@GetMapping
	public ResponseEntity<Object> obtainPacientes() {
		logger.info(GETTING + PACIENTE);
		return this.pacienteService.obtainPacientes();
	}

	@GetMapping(path = "/{idPaciente}")
	public ResponseEntity<Object> obtainPacientesByDocumento(@PathVariable Long idPaciente) {
		logger.info(GETTING + PACIENTE + BY + ID + idPaciente);
		return this.pacienteService.obtainPacienteByDocumento(idPaciente);
	}

	@PostMapping
	public ResponseEntity<Object> postPaciente(@RequestBody Paciente paciente) {
		logger.info("POSTING PACIENTE " + paciente.toString());
		return this.pacienteService.newPaciente(paciente);
	}
}
