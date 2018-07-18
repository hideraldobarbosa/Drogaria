package br.com.agabsistemas.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTest {
	@Test
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	
	}

}
