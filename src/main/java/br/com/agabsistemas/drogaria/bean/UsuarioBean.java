package br.com.agabsistemas.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.agabsistemas.drogaria.dao.PessoaDAO;
import br.com.agabsistemas.drogaria.dao.UsuarioDAO;
import br.com.agabsistemas.drogaria.domain.Pessoa;
import br.com.agabsistemas.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Pessoa> pessoas;
	

	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	public void novo(){
		try {
			usuario = new Usuario();
		    
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar criar um novo cliente.");
			erro.printStackTrace();
	
		}
		
	}
	public void salvar(){
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);
								
			Messages.addGlobalInfo("Usuario salvo com sucesso...");
			
			novo();
			
			usuarios = usuarioDAO.listar("tipo");
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar salvar o cliente.");
			erro.printStackTrace();
		}			
	}
	
	public void editar(ActionEvent evento){
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar();
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar selecionar um usu�rio.");
			erro.printStackTrace();
	
		}
		
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("tipo");
			
		}
		catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar listar Usu�rios");
			erro.printStackTrace();
			
		}
	}
	
	public void buscar(){
		
	}
	
}
