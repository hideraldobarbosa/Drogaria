package br.com.agabsistemas.drogaria.main;

import org.hibernate.Session;

import br.com.agabsistemas.drogaria.util.HibernateUtil;

public class HibernateUtilTest {

	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
	}

}
