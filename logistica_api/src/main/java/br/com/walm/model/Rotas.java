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
				origens.add(rota.getLastPontoDestino());
			}
		}
		return origens;
	}
	
	public void adicionarMalhas(Map<String, List<MalhaLogistica>> malhasPorDestino) {
        
		Set<Rota> rotasBaseIgual = new HashSet<>();
		//vai ter q iterar nas rotas e nas malhas
		/*Iterator<Rota> iterator = rotas.iterator();*/
		
		Set<Rota> rotasParaSeremExcluidas = new TreeSet<>();
		
			
		for (Rota rota : rotas) {
			/*Rota rota = iterator.next();*/
			if(rota.isCancelada() || rota.isErroRota()){
				rotasParaSeremExcluidas.add(rota);
			}
			if(rota.isConcluida()){
				continue;
			}
			//uma rota q esta com erro ou ja esta concluida nao pode mais adicionar nada
			if(rota.permiteAdicionarMalhas()){
				//para cada mapa
				for (Map.Entry<String,List<MalhaLogistica>> entry : malhasPorDestino.entrySet()) {
					List<MalhaLogistica> malhas = entry.getValue();
				    if(rota.getLastPontoDestino().equalsIgnoreCase(entry.getKey())){
				    	if  (!CollectionUtils.isEmpty(malhas)){
				    		for (int i = 0; i < malhas.size(); i++) {
				    			// a primeira malha da lista adiciona a rota
								if(i == 0){
									rota.addMalhaLogistica(malhas.get(i));
								}else{
									// se houver mais malhas semelhantes cria rota nova
									Rota rotaBaseIgual = rota.criarCopia();
									rotaBaseIgual.addMalhaLogistica(malhas.get(i));
									rotasBaseIgual.add(rotaBaseIgual);
								}
								
							}
				    		
				    	}
				    	//se a distancia jah estiver maior que a possivelMelhorRota a rota atual deve ser excluida
						if(buscarPossivelMelhorRota() != null && rota.getDistancia().compareTo(buscarPossivelMelhorRota().getDistancia()) == 1){
							rotasParaSeremExcluidas.add(rota);
						}
				    }
				}
			}
			//se a rota nao chegou no destino que deveria deve ser excluida
			if(!rota.getLastPontoDestino().equalsIgnoreCase(pontoDestino)){
				rotasParaSeremExcluidas.add(rota);
			}
			//se possivelMelhorRota possui distancia maior que atual deve ser cancelada
			if(buscarPossivelMelhorRota() != null && buscarPossivelMelhorRota().getDistancia().compareTo(rota.getDistancia()) == 1){
				buscarPossivelMelhorRota().cancelarRota();
			}
		}
		for (Rota rotaExcluida : rotasParaSeremExcluidas) {
			rotas.remove(rotaExcluida);
		}
		rotas.addAll(rotasBaseIgual);
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

}
