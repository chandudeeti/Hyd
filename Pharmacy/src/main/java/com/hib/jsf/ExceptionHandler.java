package com.hib.jsf;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ExceptionHandler extends Exception{
	
	 private static final Logger LOGGER = (Logger) LogManager.getLogger(ExceptionHandler.class);

	    public static void handleException(Exception e) throws IOException {
	        LOGGER.error("An error occurred", e);

	        FacesContext context = FacesContext.getCurrentInstance();
	        ExternalContext externalContext = context.getExternalContext();

	        context.addMessage(null, new FacesMessage(
	            FacesMessage.SEVERITY_ERROR, "Something went wrong", null));

	        externalContext.redirect(externalContext.getRequestContextPath() + "/error.xhtml");
	    }
	}


