package br.com.agabsistemas.drogaria.dao;

import java.math.BigDecimal;
import java.util.List;


import org.junit.Ignore;
import org.junit.Test;

import br.com.agabsistemas.drogaria.domain.Cidade;
import br.com.agabsistemas.drogaria.domain.Fabricante;
import br.com.agabsistemas.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Test
	@Ignore
	public void Salvar(){
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(2L);
		
		Produto produto = new Produto();
		produto.setDescricao("Miclesine 150 mg com 20 comprimidos");
		produto.setFabricante(fabricante);
		produto.setQuantidade(new Short( "5"));
		produto.setPreco(new BigDecimal("8.55"));
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);
	}
	@Test
	@Ignore
	public void Buscar(){
		Long codigo = 1L;
		ProdutoDAO  produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		
		if (produto != null){
			System.out.println("Código    	   : "+produto.getCodigo());
			System.out.println("Descrição	   : "+produto.getDescricao());
			System.out.println("Quantidade	   : "+produto.getQuantidade());
			System.out.println("Preço Unitário : "+produto.getPreco());
			System.out.println("Cód. Fabricante: "+produto.getFabricante().getCodigo());
			System.out.println("Nome Fabricante: "+produto.getFabricante().getDescricao());
	
			
		}else{
			System.out.println("Produto não encontrado");
		}
			
		
	}

	@Test
	@Ignore
	public void Listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		System.out.println("Cód. Produto"+" Descrição              "+"Qtde "+"  Preço"+"  Cód Fabr."+ " Nome Fabr.");
		
		for (Produto produto : resultado){
			System.out.println(produto.getCodigo()+"   -   "+produto.getDescricao()+"   -   "+
					           produto.getQuantidade()+" - "+produto.getPreco()+"  -  "+produto.getFabricante().getCodigo()+
					           " - "+produto.getFabricante().getDescricao());

		}
			
			
	}
	@Test
	public void Excluir(){
		Long codigo = 3L;
		ProdutoDAO  produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		produtoDAO.excluir(produto);
	}
	@Test
	@Ignore
	public void Editar(){
		Long codigo = 2L;
		ProdutoDAO  produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
	
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(2L);
		
		produto.setDescricao("Miclesine 250 mg com 20 comprimidos");
		produto.setFabricante(fabricante);
		produto.setQuantidade(new Short( "15"));
		produto.setPreco(new BigDecimal("18.55"));
		
		produtoDAO.editar(produto);
	
		
		
	}
	

}
