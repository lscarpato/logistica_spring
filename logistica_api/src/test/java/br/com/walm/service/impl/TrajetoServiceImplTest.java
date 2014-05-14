package br.com.walm.service.impl;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.walm.dto.Trajeto;
import br.com.walm.service.TrajetoService;

@ContextConfiguration("/trajeto-service-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TrajetoServiceImplTest {
	
	@Autowired
	TrajetoService trajetoService;
	
	@Test
	public void recuperarMelhorMalhaLogisticaComMelhorTrajetoTeste() {
		
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("A");
		trajetoPesquisa.setPontoDestino("D");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(BigDecimal.TEN);
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		if(trajeto != null && trajeto.getRotaFinal() != null){
			assertEquals("A_B_D", trajeto.getRotaFinal());
		}
	}
	
	
	@Test
	public void recuperarMalhaLogisticaComMelhorCustoTeste() {
		
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("A");
		trajetoPesquisa.setPontoDestino("D");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(new BigDecimal(2.5));
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		if(trajeto != null && trajeto.getRotaFinal() != null){
			assertEquals(new BigDecimal(6.25), trajeto.getCustoTrajeto().setScale(2, RoundingMode.HALF_EVEN));
		}
	}

	@Test
	public void malhaLogisticaComPontoDestinoInexistente() {
		
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("A");
		trajetoPesquisa.setPontoDestino("z");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(BigDecimal.TEN);
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		assertEquals(null, trajeto);
	}
	@Test
	public void malhaLogisticaComPontoOrigemInexistente() {
		
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("Z");
		trajetoPesquisa.setPontoDestino("D");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(BigDecimal.TEN);
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		assertEquals(null, trajeto);
	}
	@Test
	public void rotaQueNaoPodeSerConcluida() {
		
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("A");
		trajetoPesquisa.setPontoDestino("G");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(BigDecimal.TEN);
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		assertEquals(null, trajeto);
	}
}
