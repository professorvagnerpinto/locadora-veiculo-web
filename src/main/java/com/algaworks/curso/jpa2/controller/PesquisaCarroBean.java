package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.model.Carro;
import com.algaworks.curso.jpa2.modelolazy.LazyCarroDataModel;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	CarroDAO carroDAO;
	
	private List<Carro> carros = new ArrayList<>();
	
	private LazyCarroDataModel lazyCarros;
	
	private Carro carroSelecionado;
	
	@PostConstruct
	public void inicializar() {
		//carros = carroDAO.selectAll();
		lazyCarros = new LazyCarroDataModel(carroDAO); //paginação
	}
	
	public void buscarCarroComAcessorios() {
		carroSelecionado = carroDAO.selectCarroComAcessorios(carroSelecionado.getCodigo());
	}
	
	public void excluir() {
		try {
			carroDAO.excluir(carroSelecionado);
			this.carros.remove(carroSelecionado);
			FacesUtil.addSuccessMessage("Carro placa " + carroSelecionado.getPlaca() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public List<Carro> getCarros() {
		return carros;
	}
	
	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}
	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}
	
	public LazyCarroDataModel getLazyCarros() {
		return lazyCarros;
	}
	
}
