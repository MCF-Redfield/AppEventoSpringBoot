package com.redfield.evento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.redfield.evento.models.Evento;
import com.redfield.evento.repository.EventoRepository;

@Controller
public class EventoController
{
	
	@Autowired
	private EventoRepository eventR;
	
	@RequestMapping("/addEvento")
	@GetMapping
	public String form()
	{
		return "eventoView/formEvento";
	}
	
	@RequestMapping(value="/addEvento", method = RequestMethod.POST)
	public String form(Evento evento)
	{
		eventR.save(evento);
		return "redirect:/addEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView getEventos()
	{
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = eventR.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	
}
