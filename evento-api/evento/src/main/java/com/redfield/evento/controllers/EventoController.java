package com.redfield.evento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.templateparser.raw.IRawHandler;

import com.redfield.evento.models.Convidado;
import com.redfield.evento.models.Evento;
import com.redfield.evento.repository.ConvidadoRepository;
import com.redfield.evento.repository.EventoRepository;

@Controller
public class EventoController
{
	
	@Autowired
	private EventoRepository eventR;
	@Autowired
	private ConvidadoRepository convidadoR;
	
	@RequestMapping("/addEvento")
	@GetMapping
	public String form()
	{
		return "eventoView/formEvento";
	}
	
	@RequestMapping(value="/addEvento", method = RequestMethod.POST)
	public String form(Evento evento)
	{
		if(evento.getNome().equals("") 
				|| evento.getData().equals("") 
				|| evento.getHorario().equals("") 
				|| evento.getLocal().equals(""))
		{}else
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
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("id") long id)
	{
		Evento evento = eventR.findById(id);
		ModelAndView mv = new ModelAndView("eventoView/detalhesEvento");
		mv.addObject("evento", evento);
		
		Iterable<Convidado> convidados = convidadoR.findByEvento(evento);
		mv.addObject("convidados", convidados);
		
		return mv;
	}
	
	@RequestMapping("/deleteEvento")
	public String deleteEvento(long id)
	{
		eventR.delete(eventR.findById(id));
		
		return "redirect:/eventos";
	}
	
	@RequestMapping("/deleteConvidado")
	public String deleteConvidado(String rg)
	{
		Convidado convidado = convidadoR.findByRg(rg);
		convidadoR.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codEvento = evento.getId();
		
		return "redirect:/" + codEvento;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("id") long id, Convidado convidado) {
		convidado.setEvento(eventR.findById(id));
				
		if (!convidado.getNomeConvidado().equals("") && !convidado.getRg().equals("")) {
			convidadoR.save(convidado);
		}
		return "redirect:/{id}";
	}
	
}
