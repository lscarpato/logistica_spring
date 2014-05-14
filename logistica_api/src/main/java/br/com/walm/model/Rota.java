package br.com.walm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class Rota implements Comparable<Rota>{

	private List<MalhaLogistica> malhas = new ArrayList<>();
	private BigDecimal distancia = BigDecimal.ZERO;
	private String pontoOrigem;
	private String pontoDestino; 
	private boolean concluida;
	private boolean cancelada;
	private boolean erroRota;

	public Rota( String pontoOrigem,String pontoDestino) {
		this.pontoOrigem = pontoOrigem;
		this.pontoDestino = pontoDestino;
	}
	
	public Rota criarCopia(){
		Rota rota = new Rota(pontoOrigem, pontoDestino);
		for (MalhaLogistica malha : malhas) {
			rota.addMalhaLogistica(malha);
		}
		return rota;
	}
	
	public boolean addMalhaLogistica(MalhaLogistica malhaLogistica) {
		
		if (CollectionUtils.isEmpty(malhas) && !pontoOrigem.equals(malhaLogistica.getPontoOrigem())){
			throw new IllegalArgumentException("N\u00E3o \u00E9 poss\u00EDvel inicializar a rota com esta malha, verificar ponto de origem");
		}
		if (!CollectionUtils.isEmpty(malhas) && pontoOrigem.equals(malhaLogistica.getPontoDestino())){
			concluida = true;
			// entrou em loop
			erroRota = true;
		}
		boolean added = false;
		if(!CollectionUtils.containsInstance(malhas, malhaLogistica) && !erroRota){
			malhas.add(malhaLogistica);
			distancia = distancia.add(malhaLogistica.getDistancia());
			added = true;
		}
		if (pontoDestino.equals(malhaLogistica.getPontoDestino())){
			concluida = true;
		}
		return added;
		
	}

	public String getLastPontoDestino() {
		if (!CollectionUtils.isEmpty(malhas)) {
			return malhas.get(malhas.size() - 1).getPontoDestino();
		} else {
			return "";
		}
	}
	public void cancelarRota() {
		cancelada = true;
	}
	public void concluirRota() {
		concluida = true;
	}

	public boolean permiteAdicionarMalhas() {
		if(this.getLastPontoDestino() != this.pontoDestino && !this.isCancelada() && !this.isConcluida() && !this.isErroRota()){
			return true;
		}
		return false;
	}
	public boolean isConcluida() {
		return this.concluida;
	}
	
	@Override
	public int compareTo(Rota rota) {
		return this.distancia.compareTo(rota.distancia);
	}
	
	public BigDecimal getDistancia() {
		return distancia;
	}

	public boolean isErroRota() {
		return erroRota;
	}

	public List<MalhaLogistica> getMalhas() {
		return malhas;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public String getPontoDestino() {
		return pontoDestino;
	}

}
