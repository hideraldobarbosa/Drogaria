package br.com.agabsistemas.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.agabsistemas.drogaria.domain.Cidade;
import br.com.agabsistemas.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
    @SuppressWarnings("unchecked")
	public List<Cidade> buscaPorEstado(long estadoCodigo){
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(Cidade.class);
				consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
				consulta.addOrder(Order.asc("nome"));

				List<Cidade> resultado = consulta.list();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}

	 }
	 
}
