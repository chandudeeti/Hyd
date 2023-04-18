package com.hib.jsf;

import java.io.IOException;

public interface PharmacyDAO {
	
	String addUser(User user) throws IOException;
	public String validateUser(User user);

}
