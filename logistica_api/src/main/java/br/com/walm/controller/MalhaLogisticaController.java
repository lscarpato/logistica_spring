package br.com.walm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseErrorHandler;

import br.com.walm.model.MalhaLogistica;
import br.com.walm.service.MalhaLogisticaService;

@RestController
@RequestMapping(value="/malhas")
public class MalhaLogisticaController {
	
	private MalhaLogisticaService service;
	
	@Autowired
	public MalhaLogisticaController(MalhaLogisticaService seriesService) {
		this.service = seriesService;
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertMalhaLogistica(@RequestBody MalhaLogistica malha, HttpServletRequest request, HttpServletResponse response) {
		service.insertMalhaLogistica(malha);
		response.setHeader("Location", request.getRequestURL().append("/").append(malha.getId()).toString());
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.DELETE)
	public void deleteMalhaLogistica(@PathVariable("Id") long id) {
		 service.deleteMalhaLogistica(id);
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.GET)
	public ResponseEntity<MalhaLogistica> getMalhaLogistica(@PathVariable("Id") long id) {
		MalhaLogistica malhaLogistica = service.getMalhaLogistica(id);
		if (malhaLogistica == null) {
			return new ResponseEntity<MalhaLogistica>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<MalhaLogistica>(malhaLogistica, HttpStatus.OK);
	}
}
