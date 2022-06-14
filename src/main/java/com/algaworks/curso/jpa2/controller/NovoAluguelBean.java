package com.algaworks.curso.jpa2.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.dao.MotoristaDAO;
import com.algaworks.curso.jpa2.model.Aluguel;
import com.algaworks.curso.jpa2.model.ApoliceSeguro;
import com.algaworks.curso.jpa2.model.Carro;
import com.algaworks.curso.jpa2.model.Motorista;
import com.algaworks.curso.jpa2.service.CadastroAluguelService;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jsf.FacesUtil;

@Named
@ViewScoped
public class NovoAluguelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private CadastroAluguelService cadastroAluguelService;
	@Inject
	private CarroDAO carroDAO;
	@Inject
	private MotoristaDAO motoristaDAO;
	private Aluguel aluguel;	
	private List<Carro> carros;
	private List<Motorista> motoristas;
	
	@PostConstruct
	public void inicializar() {
		this.limpar();
		
		this.carros = this.carroDAO.selectAll();
		this.motoristas = this.motoristaDAO.selectAll();
	}
	
	public void salvar() {
		try {
			this.cadastroAluguelService.salvar(aluguel);
			FacesUtil.addSuccessMessage("Aluguel salvo com sucesso!");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
		
		this.limpar();
	}
	
	public void limpar() {
		this.aluguel = new Aluguel();
		this.aluguel.setApoliceSeguro(new ApoliceSeguro());
	}

	public Aluguel getAluguel() {
		return aluguel;
	}
	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public List<Carro> getCarros() {
		return carros;
	}
	
	public List<Motorista> getMotoristas() {
	    return motoristas;
	}

}
