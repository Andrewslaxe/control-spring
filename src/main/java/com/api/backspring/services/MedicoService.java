package com.api.backspring.services;

import com.api.backspring.models.Medico;
import com.api.backspring.repositories.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.api.backspring.models.constants.Constants.*;
import static com.api.backspring.utils.Utils.FormatResponse;

@Service
public class MedicoService {
	private final IMedicoRepository medicoRepository;

	@Autowired
	public MedicoService(IMedicoRepository medicoRepositorio) {
		this.medicoRepository = medicoRepositorio;
	}

	public ResponseEntity<Object> obtainMedicos() {
		List<Medico> medicos = this.medicoRepository.findAll();
		return new ResponseEntity<>(medicos, HttpStatus.OK);
	}

	public ResponseEntity<Object> obtainMedicoByDocumento(Long documento) {
		Optional<Medico> medico = this.medicoRepository.findById(documento);
		if (medico.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(medico, HttpStatus.OK);
	}

	public ResponseEntity<Object> obtainEspecialidades() {
		List<String> especialidades = this.medicoRepository.findEspecialidades();
		if (especialidades.isEmpty())
			return new ResponseEntity<>(FormatResponse("No se encuentran especialidades"), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(especialidades, HttpStatus.OK);
	}

	public ResponseEntity<Object> obtainMedicosByEspecialidad(String especialidad) {
		List<Medico> medicos = this.medicoRepository.findMedicosByEspecialidad(especialidad);
		if (medicos.isEmpty())
			return new ResponseEntity<>(FormatResponse("No se encuentran medicos por la especialidad " + especialidad), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(medicos, HttpStatus.OK);
	}

	public ResponseEntity<Object> newMedico(Medico medico) {
		if (this.medicoRepository.existsById(medico.getId())) {
			return new ResponseEntity<>(FormatResponse(PACIENTE + medico.getNombre() + BLANK + medico.getApellido() + REGISTRADO), HttpStatus.CONFLICT);
		}
		this.medicoRepository.save(medico);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
