package com.algaworks.curso.jpa2.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import com.algaworks.curso.jpa2.model.Fabricante;



@SuppressWarnings("serial")
public class FabricanteService implements Serializable{
	
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Transactional
	public void insert(Fabricante fabricante) throws NegocioException{
		if(fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new NegocioException("O nome do fabricante é obrigatório.");
		}
		
		this.fabricanteDAO.insert(fabricante);
		
	}

}
