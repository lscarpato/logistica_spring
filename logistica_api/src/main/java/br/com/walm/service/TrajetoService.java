package br.com.walm.service;

import java.util.List;
import java.util.Map;

import br.com.walm.dto.Trajeto;
import br.com.walm.model.MalhaLogistica;
import br.com.walm.model.Rota;
import br.com.walm.model.Rotas;

public interface TrajetoService {

	Trajeto buscarMelhor(Trajeto trajeto);
	
	String retornarNomesRota(List<MalhaLogistica> malhas);
	
	Trajeto preencherTrajeto(Rota rota, Trajeto pesquisa);
	
	Rotas criarNovasRotas(Trajeto pesquisa );
	
	Rotas addMalhas(Trajeto pesquisa, Rotas rotas);
	
	Map<String, List<MalhaLogistica>>  buscarNovasMalhas( Rota rota);
	

}
