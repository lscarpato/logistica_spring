package br.com.walm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MalhasDataBuilder {
	
	
	public static List<MalhaLogistica> createMalhasPontoOrigemA(){
		List<MalhaLogistica> list = new ArrayList<MalhaLogistica>();
		
		MalhaLogistica malhaLogistica3 = new MalhaLogistica();
		malhaLogistica3.setPontoOrigem("A");
		malhaLogistica3.setPontoDestino("C");
		malhaLogistica3.setDistancia(new BigDecimal(20));
		list.add(malhaLogistica3);
		
		MalhaLogistica malhaLogistica1 = new MalhaLogistica();
		malhaLogistica1.setPontoOrigem("A");
		malhaLogistica1.setPontoDestino("B");
		malhaLogistica1.setDistancia(BigDecimal.TEN);
		list.add(malhaLogistica1);
		return list;
	}

	public static Map<String,List<MalhaLogistica>> createMalhasPontoOrigemB_C(){
		
		Map<String,List<MalhaLogistica>> malhasPorOrigem = new HashMap<>();
		List<MalhaLogistica> malhasB = new ArrayList<>();
		
		MalhaLogistica malhaLogistica2 = new MalhaLogistica();
		malhaLogistica2.setPontoOrigem("B");
		malhaLogistica2.setPontoDestino("D");
		malhaLogistica2.setDistancia( new BigDecimal(15));
		malhasB.add(malhaLogistica2);
		
		MalhaLogistica malhaLogistica5 = new MalhaLogistica();
		malhaLogistica5.setPontoOrigem("B");
		malhaLogistica5.setPontoDestino("E");
		malhaLogistica5.setDistancia(new BigDecimal(50));
		malhasB.add(malhaLogistica5);
		
		malhasPorOrigem.put("B", malhasB);
		
		List<MalhaLogistica> malhasC = new ArrayList<>();
		MalhaLogistica malhaLogistica4 = new MalhaLogistica();
		malhaLogistica4.setPontoOrigem("C");
		malhaLogistica4.setPontoDestino("D");
		malhaLogistica4.setDistancia(new BigDecimal(30));
		malhasC.add(malhaLogistica4);
		
		malhasPorOrigem.put("C", malhasC);
		return malhasPorOrigem;
	}
	
	public static List<MalhaLogistica> createMalhasPontoOrigemSorocaba(){
		List<MalhaLogistica> list = new ArrayList<MalhaLogistica>();
		

		MalhaLogistica malhaLogistica1 = new MalhaLogistica();
		malhaLogistica1.setPontoOrigem("SOROCABA");
		malhaLogistica1.setPontoDestino("RIBEIRÃO PRETO");
		malhaLogistica1.setDistancia( new BigDecimal(324));
		list.add(malhaLogistica1);
		
		MalhaLogistica malhaLogistica2 = new MalhaLogistica();
		malhaLogistica2.setPontoOrigem("SOROCABA");
		malhaLogistica2.setPontoDestino("SÃO PAULO");
		malhaLogistica2.setDistancia(new BigDecimal(87));
		list.add(malhaLogistica2);
		
		
		return list;
	}
	
	public static Map<String,List<MalhaLogistica>> createMalhasPontoOrigemSaoPaulo_RibeiraoPreto(){
		
		Map<String,List<MalhaLogistica>> malhasPorOrigem = new HashMap<>();
		List<MalhaLogistica> malhasSaoPaulo = new ArrayList<>();
		
		MalhaLogistica malhaLogistica1 = new MalhaLogistica();
		malhaLogistica1.setPontoOrigem("SÃO PAULO");
		malhaLogistica1.setPontoDestino("SOROCABA");
		malhaLogistica1.setDistancia(new BigDecimal(88));
		malhasSaoPaulo.add(malhaLogistica1);
		
		MalhaLogistica malhaLogistica2 = new MalhaLogistica();
		malhaLogistica2.setPontoOrigem("SÃO PAULO");
		malhaLogistica2.setPontoDestino("S. JOSÉ DOS CAMPOS");
		malhaLogistica2.setDistancia(new BigDecimal(72));
		malhasSaoPaulo.add(malhaLogistica2);
		
		malhasPorOrigem.put("SÃO PAULO", malhasSaoPaulo);
		
		List<MalhaLogistica> malhasRibeiraoPreto = new ArrayList<>();
		MalhaLogistica malhaLogistica3 = new MalhaLogistica();
		malhaLogistica3.setPontoOrigem("RIBEIRÃO PRETO");
		malhaLogistica3.setPontoDestino("SÃO PAULO");
		malhaLogistica3.setDistancia(new BigDecimal(483));
		malhasRibeiraoPreto.add(malhaLogistica3);
		
		MalhaLogistica malhaLogistica4 = new MalhaLogistica();
		malhaLogistica4.setPontoOrigem("RIBEIRÃO PRETO");
		malhaLogistica4.setPontoDestino("S. JOSÉ DOS CAMPOS");
		malhaLogistica4.setDistancia(new BigDecimal(2659));
		malhasRibeiraoPreto.add(malhaLogistica4);
		
		malhasPorOrigem.put("RIBEIRÃO PRETO", malhasRibeiraoPreto);
		return malhasPorOrigem;
	}
	
	public static List<MalhaLogistica> createMalhasPontoOrigemSaoPaulo(){
		List<MalhaLogistica> list = new ArrayList<MalhaLogistica>();
		MalhaLogistica malhaLogistica1 = new MalhaLogistica();
		malhaLogistica1.setPontoOrigem("SÃO PAULO");
		malhaLogistica1.setPontoDestino("ARUJÁ");
		malhaLogistica1.setDistancia( new BigDecimal(42.7));
		list.add(malhaLogistica1);
		
		MalhaLogistica malhaLogistica2 = new MalhaLogistica();
		malhaLogistica2.setPontoOrigem("SÃO PAULO");
		malhaLogistica2.setPontoDestino("SANTA ISABEL");
		malhaLogistica2.setDistancia(new BigDecimal(59.6));
		list.add(malhaLogistica2);
		
		MalhaLogistica malhaLogistica3 = new MalhaLogistica();
		malhaLogistica3.setPontoOrigem("SÃO PAULO");
		malhaLogistica3.setPontoDestino("MOGI DAS CRUZES");
		malhaLogistica3.setDistancia( new BigDecimal(62.3));
		list.add(malhaLogistica3);
		
		MalhaLogistica malhaLogistica4 = new MalhaLogistica();
		malhaLogistica4.setPontoOrigem("SÃO PAULO");
		malhaLogistica4.setPontoDestino("JACAREÍ");
		malhaLogistica4.setDistancia(new BigDecimal(82.7));
		
		list.add(malhaLogistica4);
		MalhaLogistica malhaLogistica5 = new MalhaLogistica();
		malhaLogistica5.setPontoOrigem("SÃO PAULO");
		malhaLogistica5.setPontoDestino("S. JOSÉ DOS CAMPOS");
		malhaLogistica5.setDistancia( new BigDecimal(89.7));
		list.add(malhaLogistica5);
		return list;
	}
	
	public static Map<String,List<MalhaLogistica>> createMalhasPontoOrigemSantaIsabel_MogiDasCruzes(){
		
		Map<String,List<MalhaLogistica>> malhasPorOrigem2 = new HashMap<>();
		
		List<MalhaLogistica> malhasAruja = new ArrayList<>();
		MalhaLogistica malhaLogistica1 = new MalhaLogistica();
		malhaLogistica1.setPontoOrigem("ARUJÁ");
		malhaLogistica1.setPontoDestino("JACAREÍ");
		malhaLogistica1.setDistancia(new BigDecimal(43.4));
		malhasAruja.add(malhaLogistica1);
		malhasPorOrigem2.put("ARUJÁ", malhasAruja);
		
		List<MalhaLogistica> malhasJacarei = new ArrayList<>();
		MalhaLogistica malhaLogistica2 = new MalhaLogistica();
		malhaLogistica2.setPontoOrigem("JACAREÍ");
		malhaLogistica2.setPontoDestino("S. JOSÉ DOS CAMPOS");
		malhaLogistica2.setDistancia(new BigDecimal(13.3));
		malhasJacarei.add(malhaLogistica2);
		malhasPorOrigem2.put("JACAREÍ", malhasJacarei);
		
		List<MalhaLogistica> malhasSantaIsabel = new ArrayList<>();
		MalhaLogistica malhaLogistica3 = new MalhaLogistica();
		malhaLogistica3.setPontoOrigem("SANTA ISABEL");
		malhaLogistica3.setPontoDestino("S. JOSÉ DOS CAMPOS");
		malhaLogistica3.setDistancia(new BigDecimal(40.7));
		malhasSantaIsabel.add(malhaLogistica3);
		malhasPorOrigem2.put("SANTA ISABEL", malhasSantaIsabel);
		
		List<MalhaLogistica> malhasMogi = new ArrayList<>();
		
		MalhaLogistica malhaLogistica4 = new MalhaLogistica();
		malhaLogistica4.setPontoOrigem("MOGI DAS CRUZES");
		malhaLogistica4.setPontoDestino("JACAREÍ");
		malhaLogistica4.setDistancia(new BigDecimal(43.0));
		malhasMogi.add(malhaLogistica4);
		malhasPorOrigem2.put("MOGI DAS CRUZES", malhasMogi);
		return malhasPorOrigem2;
	}
}
