package br.com.agabsistemas.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;

import br.com.agabsistemas.drogaria.dao.EstadoDAO;
import br.com.agabsistemas.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	
	private Estado estado;
	private List<Estado> estados;  // sempre ao criar um modelo sempre criar gets and sets
	
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
	
	public void novo() {
		estado = new Estado();
	}

	@PostConstruct   //chamado logo após o construtor da classe 
	public void listar(){
		try {
				EstadoDAO estadoDAO = new EstadoDAO();
				estados = estadoDAO.listar();
			}catch (RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados.");
				erro.printStackTrace();
			}		
		
	}
	
	public void excluir(ActionEvent evento){
		try{
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			Messages.addGlobalInfo("Estado removido com sucesso!!");
			estados = estadoDAO.listar();
		}
		catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado.");
			erro.printStackTrace();
		}
		
	}
	
	public void salvar(){
		try {
			
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);
			
			// MENSAGEM EM PRIMEFACES
			//String texto = "Programação web com java";
			//FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,texto,texto);
			// passar para o contexto do JSF
			//FacesContext contexto = FacesContext.getCurrentInstance();
			//contexto.addMessage(null, mensagem);
			
			// MENSAGEM EM OMNIFACES
			novo();
			estados = estadoDAO.listar();
			
			Messages.addGlobalInfo("Estado salvo com sucesso");
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado.");
			erro.printStackTrace();
	}
  }
	
   public void editar(ActionEvent evento){
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
	    
   }
	
	
}
 