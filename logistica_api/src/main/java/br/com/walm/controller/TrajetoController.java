package br.com.walm.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.walm.dto.Trajeto;
import br.com.walm.service.TrajetoService;

@RestController
@RequestMapping(value="/trajetos")
public class TrajetoController {
	
	private TrajetoService service;
	
	@Autowired
	public TrajetoController(TrajetoService trajetoService) {
		this.service = trajetoService;
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Trajeto> getTrajeto(
			@RequestParam(value = "origem") String origem,
			@RequestParam(value = "destino") String destino,
			@RequestParam(value = "autonomia") BigDecimal autonomia,
			@RequestParam(value = "valorLitro") BigDecimal valorLitro
			) {
		Trajeto trajeto = new Trajeto();
		trajeto.setPontoDestino(destino);
		trajeto.setPontoOrigem(origem);
		trajeto.setAutonomia(autonomia);
		trajeto.setValorLitro(valorLitro);
		trajeto = service.buscarMelhor(trajeto);
		if(trajeto == null){	
			return new ResponseEntity<Trajeto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Trajeto>(trajeto, HttpStatus.OK);
	}
}
