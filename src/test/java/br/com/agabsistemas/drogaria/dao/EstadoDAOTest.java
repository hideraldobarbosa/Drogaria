package br.com.agabsistemas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.agabsistemas.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar(){
		Estado estado = new Estado();
        
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		
		estadoDAO.salvar(estado);
	}
	@Test
	@Ignore
	public void listar(){
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
	
		for(Estado estado : resultado){
			System.out.println(estado.getSigla()+" - "+estado.getNome());		}
	}

	@Test
	@Ignore
	public void buscar(){
		Long codigo = 2L;
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(codigo);

		System.out.println("buscou "+codigo + "---> Trouxe Sigla "+estado.getSigla()+ " nome :"+estado.getNome());

		
	}
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 6L;
		EstadoDAO estadoDAO = new EstadoDAO();
		
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado != null) {
			estadoDAO.excluir(estado);
			System.out.println("registro excluido com sucesso!");
			
			
		}else{
			System.out.println("nenhum registro encontrado!");
		}
			
		
	}
	@Test
	public void editar(){
		Long codigo = 2L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		estado.setSigla("MG");
		estado.setNome("MINAS GERAIS");
		
		if (estado != null) {
			estadoDAO.editar(estado);
			System.out.println("registro atualizado com sucesso!");
			
			
		}else{
			System.out.println("nenhum registro encontrado!");
		}


		
	}
	
	
	
	
	
}
