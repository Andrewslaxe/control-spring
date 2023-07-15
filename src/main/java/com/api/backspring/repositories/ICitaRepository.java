package com.api.backspring.repositories;

import com.api.backspring.models.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ICitaRepository extends JpaRepository<Cita, Long> {
	List<Cita> findAllByFecha(LocalDate fecha);

	List<Cita> findAllByFechaAndMedicoId(LocalDate fecha, Long medicoId);
}
