package br.com.walm.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.util.CollectionUtils;


public class Rotas {

	private Set<Rota> rotas = new TreeSet<>();
	private String pontoOrigem;
	private String pontoDestino; 
	
	public Rotas( String pontoOrigem,String pontoDestino) {
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
	}
	
	public boolean addRota(Rota rota) {
		boolean added = false;
		if(!CollectionUtils.containsInstance(rotas, rota) ){
			added = rotas.add(rota);
		}
		return added;
	}

	public Rota getMelhorRota() {
		Iterator<Rota> iterator = rotas.iterator();
		if (iterator.hasNext() != false) {
			return rotas.iterator().next();
		}
		return null;
	}

	public Set<String> montarPesquisa() {
		Set<String> origens = new HashSet<String>();
		if (CollectionUtils.isEmpty(rotas)) {
			origens.add(pontoDestino);
		} else {
			for (Rota rota : rotas) {
				if(!rota.getLastPontoDestino().equalsIgnoreCase(pontoDestino)){
					origens.add(rota.getLastPontoDestino());
				}
			}
		}
		return origens;
	}
	public Rota atualizarStatusRotas(Rota rota){
			/*//se a rota nao chegou no destino que deveria deve ser excluida
			if(!rota.getLastPontoDestino().equalsIgnoreCase(pontoDestino)){
				rotasParaSeremExcluidas.add(rota);
			}*/
			if(rota.getLastPontoDestino().equalsIgnoreCase(rota.getPontoDestino())){
				rota.concluirRota();
			}
			//se possivelMelhorRota possui distancia maior que atual deve ser cancelada
			if(this.buscarPossivelMelhorRota() != null && !this.buscarPossivelMelhorRota().permiteAdicionarMalhas() && !rota.permiteAdicionarMalhas() && this.buscarPossivelMelhorRota().getDistancia().compareTo(rota.getDistancia()) == 1){
				this.buscarPossivelMelhorRota().cancelarRota();
			}
			//se a distancia jah estiver maior que a possivelMelhorRota a rota atual deve ser excluida
			if(this.buscarPossivelMelhorRota() != null && !this.buscarPossivelMelhorRota().permiteAdicionarMalhas() && rota.getDistancia().compareTo(this.buscarPossivelMelhorRota().getDistancia()) == 1){
				rota.cancelarRota();
			}
			if(rota.isErroRota()){
				rota.cancelarRota();
			}
		
		return rota;
		
	}
	public Rotas adicionarMalhas(Rotas rotas, Map<String, List<MalhaLogistica>> malhasPorDestino) {
		Set<Rota> rotasBaseIgual = new HashSet<>();
		for (Rota rota : rotas.getRotas()) {
			//vai ter q iterar nas rotas e nas malhas
			// adicionar novas malhas na respectiva rota
			
				if(!rota.isConcluida() && !rota.isCancelada()){
				//uma rota q esta com erro ou ja esta concluida nao pode mais adicionar nada
					if(rota.permiteAdicionarMalhas()){
						//para cada mapa
						for (Map.Entry<String,List<MalhaLogistica>> entry : malhasPorDestino.entrySet()) {
							List<MalhaLogistica> malhas = entry.getValue();
							boolean cancelarRotaPorSerIncompleta = true;
							for (MalhaLogistica malhaLogistica : malhas) {
								if(malhaLogistica.getPontoDestino().equals(rota.getPontoDestino())){
									cancelarRotaPorSerIncompleta = false;
								}
							}
							if(cancelarRotaPorSerIncompleta){
								rota.cancelarRota();
							}
							
						    if(rota.getLastPontoDestino().equalsIgnoreCase(entry.getKey())){
						    	for (int i = 0; i < malhas.size(); i++) {
						    		// a primeira malha encontrada eh adicionada
									if(i != 0){
										// se houver mais malhas semelhantes cria rota nova
										Rota rotaBaseIgual = rota.criarCopia();
										rotaBaseIgual.addMalhaLogistica(malhas.get(i));
										rotasBaseIgual.add(rotaBaseIgual);
									}else{
										rota.addMalhaLogistica(malhas.get(i));
									}
						    	}
						    }
						}
					}
					rota = atualizarStatusRotas(rota);
				}
		}
			rotas.getRotas().addAll(rotasBaseIgual);
		
		
		return rotas;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean encontrouMelhorRota() {
		for (Rota rota : rotas) {
			//enquanto permite adicionar malhas nao enontrou a melhor rota ainda
			if(rota.permiteAdicionarMalhas()){
				return false;
			}
		}
		return true;
	}

	public Rota buscarPossivelMelhorRota() {
		Iterator<Rota> iterator = rotas.iterator();
		if (iterator.hasNext() != false) {
			return rotas.iterator().next();
		}
		return null;
		
	}
	public Set<Rota> getRotas() {
		return rotas;
	}

}
