package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.FabricanteDAO;
import com.algaworks.curso.jpa2.model.Fabricante;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaFabricanteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	FabricanteDAO fabricanteDAO;
	
	private List<Fabricante> fabricantes = new ArrayList<>();
	
	private Fabricante fabricante;
	
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	
	@Transactional
	public void excluir() {
		try {
			fabricanteDAO.delete(fabricante);
			this.fabricantes.remove(fabricante);
			FacesUtil.addSuccessMessage("Fabricante " + fabricante.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Fabricante getFabricanteSelecionado() {
		return fabricante;
	}
	public void setFabricanteSelecionado(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	@PostConstruct
	public void inicializar() {
		fabricantes = fabricanteDAO.selectAll();
	}
}
