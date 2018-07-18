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
import br.com.agabsistemas.drogaria.dao.EstadoDAO;
import br.com.agabsistemas.drogaria.dao.PessoaDAO;
import br.com.agabsistemas.drogaria.domain.Cidade;
import br.com.agabsistemas.drogaria.domain.Estado;
import br.com.agabsistemas.drogaria.domain.Pessoa;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private List<Estado> estados;
	private Estado estado;
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	private List<Cidade> cidades;
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar(){
		try{
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoas = pessoaDAO.listar("nome");
			
		}
		catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar listar pessoas");
			erro.printStackTrace();
			
		}
	}

	public void novo(){
		try {
			pessoa = new Pessoa();
		    estado = new Estado();
		    
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			
			cidades  = new ArrayList<Cidade>();
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar listar pessoas");
			erro.printStackTrace();
	
		}

	}
	
	public void salvar(){
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.merge(pessoa);
			
			pessoas = pessoaDAO.listar("nome");
						
			Messages.addGlobalInfo("Pessoa salva com sucesso...");
			
			novo();
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar salvar pessoa.");
			erro.printStackTrace();
		}

	}
	
	public void excluir(ActionEvent evento){
		try{
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(pessoa);
			
			Messages.addGlobalInfo("Pessoa removida com sucesso!!");
			pessoas = pessoaDAO.listar();
		}
		catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o pessoa.");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		try {
			pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
			
			estado = pessoa.getCidade().getEstado();
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar("nome");
			
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.buscaPorEstado(estado.getCodigo());
			
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar selecionar uma pessoa.");
			erro.printStackTrace();
	
		}

	}
	
	
	public void popular(){
		try{
			if(estado != null){
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscaPorEstado(estado.getCodigo());
				
			}else{
				cidades = new ArrayList<>();
			}
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar filtrar cidades.");
			erro.printStackTrace();
	
		}
	}
}
