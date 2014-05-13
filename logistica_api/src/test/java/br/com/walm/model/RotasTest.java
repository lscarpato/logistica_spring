package br.com.walm.model;

import static junit.framework.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class RotasTest {

	@Test
	public void testGetPesquisa() {
		Rotas rotas = new Rotas("A","D");
		Rota rota = new Rota("A","D");
		rota.addMalhaLogistica(createMalhaLogisticaTeste());
		rotas.addRota(rota);
		rotas.montarPesquisa();
		Set<String> set = new HashSet<>();
		set.add("D");
		assertEquals(set, rotas.montarPesquisa());
	}
	
	public MalhaLogistica createMalhaLogisticaTeste() {
		MalhaLogistica malhaLogistica = new MalhaLogistica();
		malhaLogistica.setPontoOrigem("A");
		malhaLogistica.setPontoDestino("D");
		malhaLogistica.setDistancia(BigDecimal.TEN);
		return malhaLogistica;
		
	}
}
