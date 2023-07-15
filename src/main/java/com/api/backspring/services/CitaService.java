package com.api.backspring.services;

import com.api.backspring.models.Cita;
import com.api.backspring.models.CitaRequest;
import com.api.backspring.models.Medico;
import com.api.backspring.models.Paciente;
import com.api.backspring.repositories.ICitaRepository;
import com.api.backspring.repositories.IMedicoRepository;
import com.api.backspring.repositories.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CitaService {
	private final ICitaRepository citaRepository;
	private final IMedicoRepository medicoRepository;
	private final IPacienteRepository pacienteRepository;

	@Autowired
	public CitaService(ICitaRepository citaRepository, IMedicoRepository medicoRepository, IPacienteRepository pacienteRepository) {
		this.citaRepository = citaRepository;
		this.medicoRepository = medicoRepository;
		this.pacienteRepository = pacienteRepository;
	}

	public ResponseEntity<Object> obtainCitas() {
		List<Cita> citas = this.citaRepository.findAll();
		return new ResponseEntity<>(citas, HttpStatus.OK);
	}

	public ResponseEntity<Object> obtainCitasByDates(String dates) {
		List<Integer> dateList = Arrays.stream(dates.split(","))
				.map(Integer::parseInt)
				.toList();
		LocalDate fechaActual = LocalDate.now();
		List<Cita> citas = new ArrayList<>();
		for (Integer date : dateList) {
			LocalDate fecha = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), date);
			List<Cita> citasPorFecha = this.citaRepository.findAllByFecha(fecha);
			citas.addAll(citasPorFecha);
		}
		return new ResponseEntity<>(citas, HttpStatus.OK);
	}

	public ResponseEntity<Object> obtainCitasByDatesAndMedico(String dates, Long medicoId) {
		List<Integer> dateList = Arrays.stream(dates.split(","))
				.map(Integer::parseInt)
				.toList();
		LocalDate fechaActual = LocalDate.now();
		List<Cita> citas = new ArrayList<>();
		for (Integer date : dateList) {
			LocalDate fecha = LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), date);
			List<Cita> citasPorFecha = this.citaRepository.findAllByFechaAndMedicoId(fecha, medicoId);
			citas.addAll(citasPorFecha);
		}
		return new ResponseEntity<>(citas, HttpStatus.OK);
	}

	public ResponseEntity<Object> newCita(CitaRequest citaRequest) {
		Medico medico = medicoRepository.findById(citaRequest.getMedicoId())
				.orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + citaRequest.getMedicoId()));
		Paciente paciente = pacienteRepository.findById(citaRequest.getPacienteId())
				.orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + citaRequest.getPacienteId()));
		Cita cita = new Cita();
		cita.setMedico(medico);
		cita.setPaciente(paciente);
		cita.setFecha(citaRequest.getFecha());
		cita.setHora(citaRequest.getHora());
		citaRepository.save(cita);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
