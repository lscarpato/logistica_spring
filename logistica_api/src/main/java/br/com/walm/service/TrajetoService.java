package br.com.walm.service;

import java.util.List;

import br.com.walm.dto.Trajeto;
import br.com.walm.model.MalhaLogistica;
import br.com.walm.model.Rota;

public interface TrajetoService {

	Trajeto buscarMelhor(Trajeto trajeto);
	
	String retornarNomesRota(List<MalhaLogistica> malhas);
	
	Trajeto preencherTrajeto(Rota rota, Trajeto pesquisa);
	

}
