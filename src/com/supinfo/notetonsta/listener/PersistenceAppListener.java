package com.supinfo.notetonsta.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.notetonsta.util.PersistenceManager;



@WebListener
public class PersistenceAppListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// Do nothing
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		PersistenceManager.closeEntityManagerFactory();
	}

}