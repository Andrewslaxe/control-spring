package com.api.backspring.controllers;

import com.api.backspring.models.CitaRequest;
import com.api.backspring.services.CitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.backspring.models.constants.Constants.*;

@RestController
@RequestMapping(path = "/citas")
public class CitaController {
	private final CitaService citaService;
	public final Logger logger = LoggerFactory.getLogger(CitaController.class);

	public CitaController(CitaService citaService) {
		this.citaService = citaService;
	}

	@GetMapping
	ResponseEntity<Object> obtainCitas(@RequestParam(required = false) String fechas, @RequestParam(required = false) Long medico) {
		if (fechas != null && medico != null) {
			logger.info(GETTING + CITAS + BY + FECHAS + fechas + BLANK + AND + MEDICO + medico);
			return this.citaService.obtainCitasByDatesAndMedico(fechas, medico);
		}
		if (fechas != null) {
			logger.info(GETTING + CITAS + BY + FECHAS + fechas);
			return this.citaService.obtainCitasByDates(fechas);
		}
		logger.info(GETTING + CITAS);
		return this.citaService.obtainCitas();
	}

	@PostMapping
	ResponseEntity<Object> newCita(@RequestBody CitaRequest citaRequest) {
		logger.info(POSTING + CITAS + citaRequest.toString());
		return this.citaService.newCita(citaRequest);
	}
}
