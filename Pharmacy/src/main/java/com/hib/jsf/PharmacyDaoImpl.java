package com.hib.jsf;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


@ManagedBean(name = "pDao")
@SessionScoped
public class PharmacyDaoImpl implements PharmacyDAO{
	
	private List<SelectItem> gender;
	
	private static final Logger LOGGER = LogManager.getLogger(PharmacyDaoImpl.class);
	
	
	
	public List<SelectItem> getGender() {
		return gender;
	}



	public void setGender(List<SelectItem> gender) {
		this.gender = gender;
	}



	public PharmacyDaoImpl()
	{
		gender = Arrays.stream(Gender.values()).map(g -> new SelectItem(g, g.name()))
				.collect(Collectors.toList());
	}
	

	@Override
	public String addUser(User user) throws IOException {
		try {
	    String pwd = EntryptPassword.getCode(user.getPassword());
	    user.setPassword(pwd);
	    SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Criteria cr = session.createCriteria(User.class);
	    cr.add(Restrictions.eq("userName", user.getUserName()));
	    cr.setProjection(Projections.rowCount());
	    long countUserName = (long) cr.uniqueResult();
	    cr = session.createCriteria(User.class);
	    cr.add(Restrictions.eq("email", user.getEmail()));
	    cr.setProjection(Projections.rowCount());
	    long countEmail = (long) cr.uniqueResult();
	    cr = session.createCriteria(User.class);
	    cr.add(Restrictions.eq("phone", user.getPhone()));
	    cr.setProjection(Projections.rowCount());
	    long countPhone = (long) cr.uniqueResult();
	    if(countUserName == 1)
	    {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username is taken", null));
	        return null;
	    } else if (countEmail == 1) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "A user with this email already exists", null));
	        return null;
	    } else if (countPhone == 1) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "A user with this Phone number already exists", null));
	        return null;
	    } else {
	        Transaction transaction = session.beginTransaction();
	        session.save(user);
	        transaction.commit();
	       
	    }
		}catch (Exception e) {
	    	
	    	ExceptionHandler.handleException(e);
	    }
		
		 return "UserLogin.xhtml?faces-redirect=true";
	}




	@Override
	public String validateUser(User user) {
	    Map<String,Object> sessionMap = 
	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	    String pwd = EntryptPassword.getCode(user.getPassword());
	    SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Criteria cr = session.createCriteria(User.class);
	    Criteria cr2 = session.createCriteria(Info.class);
	    cr.add(Restrictions.eq("userName", user.getUserName()));
	    cr.add(Restrictions.eq("password", pwd.trim()));
	    cr.setProjection(Projections.rowCount());
	    long count = (long) cr.uniqueResult();
	    if(count == 1)
	    {
	    	User user2 = searchByUserName(user.getUserName());
	    	sessionMap.put("sUser", user2);
	    	LOGGER.info(user2);
	        return "PharmacyDashboard.xhtml?faces-redirect=true";
	    }else {
	        sessionMap.put("error", "Invalid Credentials / Click on Singn Up to create an account");
	        return "UserLogin.xhtml?faces-redirect=true";
	    }
	}
	
	
	public String logout() {
		Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Info info = new Info();
	    info.setDateTime(new Date()); 
	    User user = (User) sessionMap.get("sUser");
	    info.setName(user.getName());
	    SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Transaction transaction = session.beginTransaction();
	    session.save(info); 
	    transaction.commit();
	    session.close();
	    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "UserLogin?faces-redirect=true";
	}
	
	
	public User searchByUserName(String userName)
	{
		SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Criteria cr = session.createCriteria(User.class);
	    cr.add(Restrictions.eq("userName", userName ));
	    User user = (User) cr.uniqueResult();
	    return user;
	}
	
	public String getName()
	{
		SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Criteria cr = session.createCriteria(Info.class);
	    List<Info> infos = cr.list();
	    Info info = infos.get(infos.size()-1);
	    return info.getName();
	}
	
	public java.sql.Timestamp getDate() {
	    SessionFactory sf = SessionHelper.getConnection();
	    Session session = sf.openSession();
	    Criteria cr = session.createCriteria(Info.class);
	    List<Info> infos = cr.list();
	    Info info = infos.get(infos.size() - 1);
	    java.util.Date utilDate = info.getDateTime();
	    return new java.sql.Timestamp(utilDate.getTime());
	}

	
	
	
	
	

}