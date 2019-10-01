package com.redfield.evento.repository;

import org.springframework.data.repository.CrudRepository;

import com.redfield.evento.models.Convidado;
import com.redfield.evento.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String>
{

	Iterable<Convidado> findByEvento(Evento evento);

	Convidado findByRg(String rg);

}
