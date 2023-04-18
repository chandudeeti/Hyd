package com.hib.jsf;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.hib.jsf.PhoneValidator")
public class PhoneValidator implements Validator {

	private static final String Phone_pattern = "\\d{10}";
	private Pattern pattern;
	private Matcher matcher;
	
	public PhoneValidator(){
		pattern = Pattern.compile(Phone_pattern);
	}

	@Override

	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
			FacesMessage msg=
			new FacesMessage("Phone No Validation...","Mobile No Contains only 10 characters...");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}

}