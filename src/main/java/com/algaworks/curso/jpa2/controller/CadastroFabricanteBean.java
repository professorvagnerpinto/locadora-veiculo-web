package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

import com.algaworks.curso.jpa2.model.Fabricante;
import com.algaworks.curso.jpa2.service.FabricanteService;
import com.algaworks.curso.jpa2.service.NegocioException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{
	@Inject
	private FabricanteService fabricanteService;
	private Fabricante fabricante;
	
	public void salvar() { //mesmo nome que está na View
		try {
			this.fabricanteService.insert(fabricante);
			FacesUtil.addSuccessMessage("Fabricante salvo com sucesso.");
			this.limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	@PostConstruct
	public void init() { //chamado ao inicializar a View
		this.limpar();
	}
	
	public void limpar() { //limpa o objeto de modelo
		this.fabricante = new Fabricante();
	}
	
	//getters e setters
	public Fabricante getFabricante() {
		return fabricante;
	}
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	

}
