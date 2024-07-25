package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.exception.EmailNotValidException;
import com.aurionpro.exception.PasswordNotValidException;
import com.aurionpro.model.User;

public class UserTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = new User(email, password);
            System.out.println("User created successfully.");
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());
        } catch (EmailNotValidException | PasswordNotValidException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
	}

}
