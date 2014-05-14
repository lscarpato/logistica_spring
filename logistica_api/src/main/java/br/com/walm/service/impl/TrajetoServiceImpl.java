package br.com.walm.service.impl;

import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
		Rotas rotas = criarNovasRotas(pesquisa);
		rotas = addMalhas(pesquisa, rotas);
		if(rotas.getMelhorRota() != null && rotas.getMelhorRota().getLastPontoDestino() != null &&rotas.getMelhorRota().getLastPontoDestino().equalsIgnoreCase(pesquisa.getPontoDestino())){
			return  preencherTrajeto(rotas.getMelhorRota(), pesquisa);
		}
		return null;
		
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
	public Trajeto preencherTrajeto(Rota rota, Trajeto pesquisa){
		if(rota != null && rota.getMalhas() != null){
		Trajeto melhorTrajeto = new Trajeto();
		melhorTrajeto.setPontoDestino(pesquisa.getPontoDestino());
		melhorTrajeto.setPontoOrigem(pesquisa.getPontoOrigem());
		melhorTrajeto.setAutonomia(pesquisa.getAutonomia());
		melhorTrajeto.setValorLitro(pesquisa.getValorLitro());
		melhorTrajeto.setDistancia(rota.getDistancia());;
		melhorTrajeto.setRotaFinal(retornarNomesRota(rota.getMalhas()));
		melhorTrajeto.setCustoTrajeto((rota.getDistancia().divide(pesquisa.getAutonomia(),MathContext.DECIMAL128).setScale(2, RoundingMode.HALF_EVEN)).multiply(pesquisa.getValorLitro()));
		return melhorTrajeto;
		}
		return null;
	}
	
	@Override
	public Rotas addMalhas(Trajeto pesquisa, Rotas rotas ){
		while(!rotas.encontrouMelhorRota()){
			Map<String, List<MalhaLogistica>> malhas  = malhaLogisticaService.findMalhasByPontosOrigem(rotas.montarPesquisa());
			
			/*if(CollectionUtils.isEmpty(malhasEncontradas) && !rota.getLastPontoDestino().equalsIgnoreCase(rota.getPontoDestino())){
				rota.cancelarRota();
			}*/
			rotas = rotas.adicionarMalhas(rotas, malhas);
		}
		
		return rotas;
	}
		
	@Override
	public Rotas criarNovasRotas(Trajeto pesquisa ){
		// busca pontosOrigem  igual ao da pesquisa
				List<MalhaLogistica> pontosOrigem = malhaLogisticaService.findByPontoOrigem(pesquisa.getPontoOrigem());
		Rotas rotas = new Rotas(pesquisa.getPontoOrigem(), pesquisa.getPontoDestino());
		//Varre cada pontoOrigem encontrado
				for (MalhaLogistica malhaLogistica : pontosOrigem) {
					Rota rota = new Rota(pesquisa.getPontoOrigem(), pesquisa.getPontoDestino());
					//Adiciona a primeira MalhaLogistica
					rota.addMalhaLogistica(malhaLogistica);
					rotas.addRota(rota);
				}
		return rotas;
	}
	@Override
	public Map<String, List<MalhaLogistica>>  buscarNovasMalhas(Rota rota ){
		Map<String, List<MalhaLogistica>> malhasPorOrigem = new HashMap<>();
		List<MalhaLogistica> malhas = new ArrayList<MalhaLogistica>();
			malhas = malhaLogisticaService.findByPontoOrigem(rota.getLastPontoDestino());
		if(!CollectionUtils.isEmpty(malhas)){
			malhasPorOrigem.put(rota.getLastPontoDestino(), malhas);
		}
		return malhasPorOrigem;
	}
}
