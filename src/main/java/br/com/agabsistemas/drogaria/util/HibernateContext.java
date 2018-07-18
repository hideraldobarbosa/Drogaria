package br.com.agabsistemas.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateContext implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getFabricaDeSessoes().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getFabricaDeSessoes();
		
	}

}
