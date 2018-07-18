package br.com.agabsistemas.drogaria.bean;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.Path;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.agabsistemas.drogaria.dao.FabricanteDAO;
import br.com.agabsistemas.drogaria.dao.ProdutoDAO;

import br.com.agabsistemas.drogaria.domain.Fabricante;
import br.com.agabsistemas.drogaria.domain.Produto;
import br.com.agabsistemas.drogaria.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;


@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}
	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
	@PostConstruct
	public void listar(){
		try{
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		}
		catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar listar produtos");
			erro.printStackTrace();
			
		}
	}
	
	public void novo(){
		try {
			produto = new Produto();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar listar fabricantes");
			erro.printStackTrace();
	
		}

	}	
	public void salvar(){
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.merge(produto);
			produto = new Produto();
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
			
			produtos = produtoDAO.listar(); 
			
			Messages.addGlobalInfo("Produto salvo com sucesso...");
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar listar produtos");
			erro.printStackTrace();
		}

	}
	
	public void excluir(ActionEvent evento){
		try{
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto);
			
			Messages.addGlobalInfo("Produto removido com sucesso!!");
			produtos = produtoDAO.listar();
		}
		catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto.");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento){
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
			
			FabricanteDAO fabricanteDAO = new FabricanteDAO();
			fabricantes = fabricanteDAO.listar();
		}catch(RuntimeException erro){
			Messages.addFlashGlobalError("Ocorreu erro ao tentar selecionar um produto.");
			erro.printStackTrace();
	
		}

	}	
	
	public void uploadFoto(FileUploadEvent evento){
		try{
			UploadedFile arquivoUpload = evento.getFile();
			java.nio.file.Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp,StandardCopyOption.REPLACE_EXISTING);
		    produto.setCaminho(arquivoTemp.toString());
		    System.out.println("Caminho :"+produto.getCaminho());
		    // primeiro parametro nome, segundo extensão
		}catch(IOException erro){
			Messages.addGlobalInfo("Ocorreu um erro ao tentar realizar o upload");
		}

	}
	
	@SuppressWarnings("unused")
	public void imprimir(){
		try{
			
			DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("formListagem:tabela");
			Map<String,Object> filtros = tabela.getFilters();
			String proDescricao = (String) filtros.get("descricao");
			String fabDescricao = (String) filtros.get("fabricante");
			String caminho = Faces.getRealPath("/reports/produto_fabricante.jasper");
			Map<String, Object> parametros = new HashMap<>();
			
			if (proDescricao == null){
				parametros.put("produto_descricao","%%");
					
			}else{
				parametros.put("produto_descricao","%"+proDescricao+"%");
				
			}

			if (fabDescricao == null){
				parametros.put("fabricante_descricao","%%");
				
			}else{
				parametros.put("fabricante_descricao","%"+fabDescricao+"%");
				
			}

			Connection conexao = HibernateUtil.getConexao();
			
			JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
			JasperPrintManager.printReport(relatorio,true);
		}catch(JRException erro){
			Messages.addGlobalError("Ocorreu um erro ao gerar o relatorio");
 			erro.printStackTrace();
		}
	}
}