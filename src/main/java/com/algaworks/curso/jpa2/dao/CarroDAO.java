package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.model.Carro;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class CarroDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Carro fabricante) {
		manager.merge(fabricante);
	}

	@SuppressWarnings("unchecked")
	public List<Carro> selectAll() {
		//return manager.createQuery("from Carro").getResultList();
		return manager.createNamedQuery("Carro.buscarTodos").getResultList();
	}
	
	public Carro selectById(Long codigo) {
		//return manager.find(Carro.class, codigo);
		return manager.createNamedQuery("Carro.buscarCarroComAcessorios", Carro.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}
	
	public Carro selectCarroComAcessorios(Long codigo) {
		return (Carro) manager.createQuery("select c from Carro c JOIN c.acessorios a where c.codigo = ?1")
				.setParameter(1, codigo)
				.getSingleResult();
	}
	
	@Transactional
	public void excluir(Carro carro) throws NegocioException {
		carro = selectById(carro.getCodigo());
		try {
			manager.remove(carro);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Carro não pode ser excluído.");
		}
	}
}
