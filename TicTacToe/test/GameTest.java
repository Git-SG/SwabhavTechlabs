package com.aurionpro.test;

import java.util.Scanner;

import com.aurionpro.model.GameController;

public class GameTest {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); 
		GameController controller = new GameController();
		
		controller.startGame(scanner);
	}
		
}
