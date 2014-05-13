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
	
	
}
