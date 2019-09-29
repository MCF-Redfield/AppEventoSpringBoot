package com.redfield.evento.repository;

import org.springframework.data.repository.CrudRepository;
import com.redfield.evento.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String>{

	Evento findById(long id);

}
