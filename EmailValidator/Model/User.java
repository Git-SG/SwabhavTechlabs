package com.aurionpro.model;

import com.aurionpro.exception.EmailNotValidException;
import com.aurionpro.exception.PasswordNotValidException;

public class User {
	private String email;
    private String password;
    private Validator validator;

    public User(String email, String password) throws EmailNotValidException, PasswordNotValidException {
        this.validator = new Validator();
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailNotValidException {
        if (!validator.isValidEmail(email)) {
            throw new EmailNotValidException("Email is not valid");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordNotValidException {
        if (!validator.isValidPassword(password)) {
            throw new PasswordNotValidException("Password is not valid");
        }
        this.password = password;
    }
}
