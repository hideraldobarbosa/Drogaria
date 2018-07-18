package br.com.agabsistemas.drogaria.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity

public class historico extends GenericDomains {
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date Horario;
	@Column(nullable = false, length=500)
	private String observacoes;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;
	
	
}
