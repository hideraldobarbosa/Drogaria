package br.com.agabsistemas.drogaria.dao;

import java.util.Date;

import org.junit.Test;

import br.com.agabsistemas.drogaria.domain.Cliente;
import br.com.agabsistemas.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	@Test
	public void salvar(){
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.buscar(2L);
		
		Cliente cliente = new Cliente();
		cliente.setDataCadastro(new Date());
		cliente.setLiberado(true);
		cliente.setPessoa(pessoa);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		
		System.out.println("cliente salvo");
		
	}
}
