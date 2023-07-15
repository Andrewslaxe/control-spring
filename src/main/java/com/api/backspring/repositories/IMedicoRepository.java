package com.api.backspring.repositories;

import com.api.backspring.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Long> {
	@Query(value = "SELECT DISTINCT especialidad FROM medicos")
	List<String> findEspecialidades();

	List<Medico> findMedicosByEspecialidad(String especialidad);
}
