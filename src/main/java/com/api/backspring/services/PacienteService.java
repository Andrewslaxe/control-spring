package com.api.backspring.services;

import com.api.backspring.models.Paciente;
import com.api.backspring.repositories.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.api.backspring.models.constants.Constants.*;
import static com.api.backspring.utils.Utils.FormatResponse;

@Service
public class PacienteService {
	private final IPacienteRepository pacienteRepository;

	@Autowired
	public PacienteService(IPacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	public ResponseEntity<Object> obtainPacientes() {
		List<Paciente> pacientes = this.pacienteRepository.findAll();
		return new ResponseEntity<>(pacientes, HttpStatus.OK);
	}

	public ResponseEntity<Object> obtainPacienteByDocumento(Long documento) {
		Optional<Paciente> paciente = this.pacienteRepository.findById(documento);
		if (paciente.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(paciente, HttpStatus.OK);
	}

	public ResponseEntity<Object> newPaciente(Paciente paciente) {
		if (this.pacienteRepository.existsById(paciente.getId())) {
			return new ResponseEntity<>(FormatResponse(PACIENTE + paciente.getNombre() + BLANK + paciente.getApellido() + REGISTRADO), HttpStatus.CONFLICT);
		}
		this.pacienteRepository.save(paciente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
