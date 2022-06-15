package com.algaworks.curso.jpa2.modelolazy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.algaworks.curso.jpa2.dao.CarroDAO;
import com.algaworks.curso.jpa2.model.Carro;

@SuppressWarnings("serial")
public class LazyCarroDataModel extends LazyDataModel<Carro> implements Serializable {

	private CarroDAO carroDAO;
	
	public LazyCarroDataModel(CarroDAO carroDAO) {
		this.carroDAO = carroDAO;
	}
	
	@Override
	public List<Carro> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		
		//busca a página
		List<Carro> carros = this.carroDAO.buscarComPaginacao(first, pageSize);
		
		//informa ao PrimeFace a quantidade total de registros (pra ele calcular a quantidade de páginas)
		this.setRowCount(this.carroDAO.encontrarQuantidadeDeCarros().intValue());
		
		return carros;
	}
	
}
