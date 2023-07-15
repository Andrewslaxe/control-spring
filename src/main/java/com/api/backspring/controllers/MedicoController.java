package com.api.backspring.controllers;

import com.api.backspring.models.Medico;
import com.api.backspring.services.MedicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.backspring.models.constants.Constants.*;

@RestController
@RequestMapping(path = "/medicos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class MedicoController {
	private final MedicoService medicoService;
	public final Logger logger = LoggerFactory.getLogger(PacienteController.class);

	@Autowired
	public MedicoController(MedicoService medicoService) {
		this.medicoService = medicoService;
	}

	@GetMapping
	public ResponseEntity<Object> obtainMedicos(@RequestParam(required = false) String especialidad) {
		if (especialidad != null) {
			logger.info(GETTING + MEDICO + BY + ESPECIALIDAD + especialidad);
			return this.medicoService.obtainMedicosByEspecialidad(especialidad);
		}
		logger.info(GETTING + MEDICO);
		return this.medicoService.obtainMedicos();
	}

	@GetMapping(path = "/{idMedico}")
	public ResponseEntity<Object> obtainMedicoByDocumento(@PathVariable Long idMedico) {
		logger.info(GETTING + MEDICO + BY + ID + idMedico);
		return this.medicoService.obtainMedicoByDocumento(idMedico);
	}

	@GetMapping(path = "/especialidades")
	public ResponseEntity<Object> obtainEspecialidades() {
		logger.info(GETTING + ESPECIALIDAD);
		return this.medicoService.obtainEspecialidades();
	}

	@PostMapping
	public ResponseEntity<Object> newMedico(@RequestBody Medico medico) {
		logger.info(POSTING + MEDICO + medico.toString());
		return this.medicoService.newMedico(medico);
	}
}

