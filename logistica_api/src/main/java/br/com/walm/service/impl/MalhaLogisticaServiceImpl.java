package br.com.walm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.walm.model.MalhaLogistica;
import br.com.walm.repository.MalhaLogisticaRepository;
import br.com.walm.service.MalhaLogisticaService;

@Service
public class MalhaLogisticaServiceImpl implements MalhaLogisticaService {
	
	private MalhaLogisticaRepository repository;
	
	@Autowired
	public MalhaLogisticaServiceImpl(MalhaLogisticaRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	public MalhaLogistica insertMalhaLogistica(MalhaLogistica malhaLogistica) {
		return repository.save(malhaLogistica);
		
	}

	@Override
	public void deleteMalhaLogistica(long id) {
		repository.delete(id);
		
	}

	@Override
	public List<MalhaLogistica> getAllMalhaLogistica() {
		repository.findAll();
		return new ArrayList<MalhaLogistica> ();
	}

	@Override
	public MalhaLogistica getMalhaLogistica(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<MalhaLogistica> findByPontoOrigem(String pontoOrigem) {
		return repository.findByPontoOrigem(pontoOrigem);
	}

	@Override
	public List<MalhaLogistica> findByPontoDestino(String pontoDestino) {
		return repository.findByPontoDestino(pontoDestino);
	}
	
	@Override
    public Map<String, List<MalhaLogistica>> findMalhasByPontosOrigem(Set<String> pontosOrigem) {
		 List<MalhaLogistica> findByPontoOrigemIn = repository.findByPontoOrigemIn(pontosOrigem);
         Map<String, List<MalhaLogistica>> malhasPorOrigem = new HashMap<>();
         // Para cada malha encontrada, adiciona chave 
         for (MalhaLogistica malhaLogistica : findByPontoOrigemIn) {
        	 //primeiramente adiciona a chave
        	 if (!malhasPorOrigem.containsKey(malhaLogistica.getPontoOrigem())) {
        		 malhasPorOrigem.put(malhaLogistica.getPontoOrigem(), new ArrayList<MalhaLogistica>());
             }
        	 // para depois setar a malha
        	 malhasPorOrigem.get(malhaLogistica.getPontoOrigem()).add(malhaLogistica);
         }
         return malhasPorOrigem;
 }

}
