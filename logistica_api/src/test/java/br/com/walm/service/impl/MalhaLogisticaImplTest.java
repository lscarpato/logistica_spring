package br.com.walm.service.impl;

import static br.com.walm.model.MalhasDataBuilder.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
				    return createMalhasPontoOrigemB_C();
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
	
	@Test
	public void testRotaDiretaSorocaba_SaoPaulo() {
		when(service.findByPontoOrigem("SOROCABA")).thenReturn(createMalhasPontoOrigemSorocaba());
		when(service.findMalhasByPontosOrigem(new HashSet<>(Arrays.asList("SÃO PAULO","RIBEIRÃO PRETO")))).thenAnswer(new Answer<Map<String, List<MalhaLogistica>>>() {
			  public Map<String, List<MalhaLogistica>> answer(InvocationOnMock invocation) throws Throwable {
				    return createMalhasPontoOrigemSaoPaulo_RibeiraoPreto();
				  }
				});
				
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("SOROCABA");
		trajetoPesquisa.setPontoDestino("SÃO PAULO");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(new BigDecimal(2.5));
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		
		assertEquals("SOROCABA_SÃO PAULO", trajeto.getRotaFinal());
	}
	
	@Test
	public void testCustoSorocaba_SaoPaulo() {
		when(service.findByPontoOrigem("SOROCABA")).thenReturn(createMalhasPontoOrigemSorocaba());
		when(service.findMalhasByPontosOrigem(new HashSet<>(Arrays.asList("SÃO PAULO","RIBEIRÃO PRETO")))).thenAnswer(new Answer<Map<String, List<MalhaLogistica>>>() {
			  public Map<String, List<MalhaLogistica>> answer(InvocationOnMock invocation) throws Throwable {
				    return createMalhasPontoOrigemSaoPaulo_RibeiraoPreto();
				  }
				});
				
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("SOROCABA");
		trajetoPesquisa.setPontoDestino("SÃO PAULO");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(new BigDecimal(2.5));
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		
		if(trajeto != null && trajeto.getRotaFinal() != null){
			assertEquals(new BigDecimal(21.75), trajeto.getCustoTrajeto().setScale(2, RoundingMode.HALF_EVEN));
		}
	}
	
	@Test
	public void testRotaSaoPaulo_SaoJoseDosCampos() {
		when(service.findByPontoOrigem("SÃO PAULO")).thenReturn(createMalhasPontoOrigemSaoPaulo());
		when(service.findMalhasByPontosOrigem(new HashSet<>(Arrays.asList("ARUJÁ","JACAREÍ","SANTA ISABEL","MOGI DAS CRUZES")))).thenAnswer(new Answer<Map<String, List<MalhaLogistica>>>() {
			  public Map<String, List<MalhaLogistica>> answer(InvocationOnMock invocation) throws Throwable {
				    return createMalhasPontoOrigemSantaIsabel_MogiDasCruzes();
				  }
				});
				
		Trajeto trajetoPesquisa = new Trajeto();
		trajetoPesquisa.setPontoOrigem("SÃO PAULO");
		trajetoPesquisa.setPontoDestino("S. JOSÉ DOS CAMPOS");
		trajetoPesquisa.setAutonomia(BigDecimal.TEN);
		trajetoPesquisa.setValorLitro(new BigDecimal(2.5));
		Trajeto trajeto = trajetoService.buscarMelhor(trajetoPesquisa);
		
		assertEquals("SÃO PAULO_ARUJÁ_JACAREÍ_S. JOSÉ DOS CAMPOS", trajeto.getRotaFinal());
	}
}
