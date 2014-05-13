package br.com.walm.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.walm.dto.Trajeto;
import br.com.walm.model.MalhaLogistica;
import br.com.walm.model.Rota;
import br.com.walm.model.Rotas;
import br.com.walm.service.MalhaLogisticaService;
import br.com.walm.service.TrajetoService;

@Service
public class TrajetoServiceImpl implements TrajetoService{
	
	private MalhaLogisticaService malhaLogisticaService;

	@Autowired
	public TrajetoServiceImpl(MalhaLogisticaService malhaLogisticaService) {
		this.malhaLogisticaService = malhaLogisticaService;
	}
	
	@Override
	public Trajeto buscarMelhor(Trajeto pesquisa) {
		
		// busca pontosOrigem  igual ao da pesquisa
		List<MalhaLogistica> pontosOrigem = malhaLogisticaService.findByPontoOrigem(pesquisa.getPontoOrigem());
		
		Rotas rotas = new Rotas(pesquisa.getPontoOrigem(), pesquisa.getPontoDestino());
		
		//Varre cada pontoOrigem encontrado
		for (MalhaLogistica malhaLogistica : pontosOrigem) {
			Rota rota = new Rota(pesquisa.getPontoOrigem(), pesquisa.getPontoDestino());
			
			//Se rota nao concluida adiona a primeira MalhaLogistica
			rota.addMalhaLogistica(malhaLogistica);
			rotas.addRota(rota);
		}
		
		while(!rotas.encontrouMelhorRota()){
			//busca malhas pelo ultimo destino da malha anterior
			Map<String, List<MalhaLogistica>> malhas  = malhaLogisticaService.findMalhasByPontosOrigem(rotas.montarPesquisa());
			rotas.adicionarMalhas(malhas);
		}
		
		return  preencherTrajeto(rotas.getMelhorRota(), pesquisa.getAutonomia(), pesquisa.getValorLitro());
	}
	
	@Override
	public String retornarNomesRota(List<MalhaLogistica> malhas){
		StringBuilder nomes = new StringBuilder();
		for (int i = 0; i < malhas.size(); i++) {
			if(i == 0 ){
				nomes.append(malhas.get(i).getPontoOrigem());
			}
			else{
				nomes.append("_");
				nomes.append(malhas.get(i).getPontoOrigem());
			}
			if(i == (malhas.size() -1)){
				nomes.append("_");
				nomes.append(malhas.get(i).getPontoDestino());
			}
		}
		 return nomes.toString();
	}
	
	@Override
	public Trajeto preencherTrajeto(Rota rota, BigDecimal autonomia, BigDecimal valorLitro){
		if(rota != null && rota.getMalhas() != null){
		Trajeto melhorTrajeto = new Trajeto();
		melhorTrajeto.setRotaFinal(retornarNomesRota(rota.getMalhas()));
		melhorTrajeto.setCustoTrajeto((rota.getDistancia().divide(autonomia,MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_EVEN)).multiply(valorLitro));
		return melhorTrajeto;
		}
		return null;
	}
	
	
}
