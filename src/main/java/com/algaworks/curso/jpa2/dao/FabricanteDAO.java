package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.curso.jpa2.model.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;

@SuppressWarnings("serial")
public class FabricanteDAO implements Serializable{
	@Inject
	private EntityManager em;
	
	public void insert(Fabricante fabricante) {
		em.merge(fabricante); //o merge resolve do preblema de atachar o objeto no PU
	}

	public List<Fabricante> selectAll() {	
		return em.createQuery("from Fabricante", Fabricante.class).getResultList();
	}
	
	public Fabricante selectById(Long codigo) {
		return em.find(Fabricante.class, codigo);
	}

	public void delete(Fabricante fabricante) throws NegocioException{
		Fabricante fabricanteTemp = em.find(Fabricante.class, fabricante.getCodigo());
		em.remove(fabricanteTemp);
		em.flush();	
	}
	
}
