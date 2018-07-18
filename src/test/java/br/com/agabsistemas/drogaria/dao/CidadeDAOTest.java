package br.com.agabsistemas.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.agabsistemas.drogaria.domain.Cidade;
import br.com.agabsistemas.drogaria.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar(){
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(2L);
	
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de Janeiro");
		cidade.setEstado(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
		
	}
	@Ignore
	@Test
	public void listar(){
		CidadeDAO cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		System.out.println("Cód. Cid"+"Nome Cidade"+"Cód UF"+"Nome Estado"+"Sigla UF");
		
		for (Cidade cidade : resultado){
			System.out.println(cidade.getCodigo()+"   -   "+cidade.getNome()+"   -   "+
					           cidade.getEstado().getCodigo()+" - "+cidade.getEstado().getNome()+
					           " - "+cidade.getEstado().getSigla());

		}
			
		
	}
	
	@Test
	@Ignore
	
	public void buscar(){
		Long codigo = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		System.out.println("Cód. Cid"+"Nome Cidade"+"C0ód UF"+"Nome Estado"+"Sigla UF");
		System.out.println(cidade.getCodigo()+"   -   "+cidade.getNome()+"   -   "+
		           cidade.getEstado().getCodigo()+" - "+cidade.getEstado().getNome()+
		           " - "+cidade.getEstado().getSigla());
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 7L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
	
		cidadeDAO.excluir(cidade);
		
	}
	@Test
	public void editar(){
		Long codigo = 1L;
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		
		cidade.setNome("Rio de Favelas");
		
		cidadeDAO.editar(cidade);
		
	}
	
	
	

}
