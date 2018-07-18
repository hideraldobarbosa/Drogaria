package br.com.agabsistemas.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.agabsistemas.drogaria.dao.CidadeDAO;
import br.com.agabsistemas.drogaria.dao.ClienteDAO;
import br.com.agabsistemas.drogaria.dao.EstadoDAO;
import br.com.agabsistemas.drogaria.dao.PessoaDAO;
import br.com.agabsistemas.drogaria.domain.Cidade;
import br.com.agabsistemas.drogaria.domain.Cliente;
import br.com.agabsistemas.drogaria.domain.Estado;
import br.com.agabsistemas.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{

		private Cliente cliente;
		private List<Cliente> clientes;
		private List<Pessoa> pessoas;
		
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		public List<Cliente> getClientes() {
			return clientes;
		}
		public void setClientes(List<Cliente> clientes) {
			this.clientes = clientes;
		}
		public List<Pessoa> getPessoas() {
			return pessoas;
		}
		public void setPessoas(List<Pessoa> pessoas) {
			this.pessoas = pessoas;
		}

		
		public void novo(){
			try {
				cliente = new Cliente();
			    
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoas = pessoaDAO.listar("nome");
				
			}catch(RuntimeException erro){
				Messages.addFlashGlobalError("Ocorreu erro ao tentar criar um novo cliente.");
				erro.printStackTrace();
		
			}
			
		}
		public void salvar(){
			try {
				ClienteDAO clienteDAO = new ClienteDAO();
				clienteDAO.merge(cliente);
									
				Messages.addGlobalInfo("Pessoa salva com sucesso...");
				
				novo();
				
				clientes = clienteDAO.listar("dataCadastro");
				
			}catch(RuntimeException erro){
				Messages.addFlashGlobalError("Ocorreu erro ao tentar salvar o cliente.");
				erro.printStackTrace();
			}			
		}
		
		public void editar(ActionEvent evento){
			try {
				cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoas = pessoaDAO.listar();
				
			}catch(RuntimeException erro){
				Messages.addFlashGlobalError("Ocorreu erro ao tentar selecionar um cliente.");
				erro.printStackTrace();
		
			}
			
		}
		
		@PostConstruct
		public void listar(){
			try{
				ClienteDAO clienteDAO = new ClienteDAO();
				clientes = clienteDAO.listar("dataCadastro");
				
			}
			catch(RuntimeException erro){
				Messages.addFlashGlobalError("Ocorreu erro ao tentar listar Clientes");
				erro.printStackTrace();
				
			}
		}
		
		public void buscar(){
			
		}
		
}
