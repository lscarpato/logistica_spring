package br.com.walm.service.impl;

import static br.com.walm.model.MalhasDataBuilder.createMalhasPontoOrigemA;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.walm.dto.Trajeto;
import br.com.walm.model.MalhaLogistica;
import br.com.walm.model.MalhasDataBuilder;
import br.com.walm.service.MalhaLogisticaService;
@RunWith(MockitoJUnitRunner.class)
public class MalhaLogisticaImplTest {

	@Mock
	MalhaLogisticaService service;

	@InjectMocks
	TrajetoServiceImpl trajetoService = new TrajetoServiceImpl(service);

	@Test
	public void test() {
		when(service.findByPontoOrigem("A")).thenReturn(createMalhasPontoOrigemA());
		when(service.findMalhasByPontosOrigem(new HashSet<>(Arrays.asList("B","C")))).thenAnswer(new Answer<Map<String, List<MalhaLogistica>>>() {
			  public Map<String, List<MalhaLogistica>> answer(InvocationOnMock invocation) throws Throwable {
				    return MalhasDataBuilder.createMalhasPontoOrigemB_C();
				  }
				});
				
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("A");
		trajetoPesquisa.setPontoDestino("D");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(BigDecimal.TEN);
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		
		assertEquals("A_B_D", trajeto.getRotaFinal());
	}
}
