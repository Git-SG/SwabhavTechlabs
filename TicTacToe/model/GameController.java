package com.aurionpro.model;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.aurionpro.exception.ColumnNotValidException;
import com.aurionpro.exception.PositionNotEmptyException;
import com.aurionpro.exception.RowNotValidException;

public class GameController {

	private Game game;
	boolean gameOver = false;

	public GameController() {
		game = new Game();
	}

	public void startGame(Scanner scanner) {
		while (!gameOver) {
			playTurn(scanner);
		}

	}
	
	public void playTurn(Scanner scanner) {
		try {
		game.printBoard();
		System.out.println("Player " + game.getPlayer() + " select a position: ");
		int row = scanner.nextInt();
		if(row > 2 || row < 0)
			throw new RowNotValidException();
		int column = scanner.nextInt();
		if(column > 2 || column < 0)
			throw new ColumnNotValidException();
		if(game.getBoard()[row][column] != ' ')
			throw new PositionNotEmptyException();
		game.addMark(row, column, game.getPlayer());
		if(game.isGameOver()) {
			System.out.println("Player " + game.getPlayer() + " wins the game!");
			game.printBoard();
			gameOver = true;
			return;
		}
		if(game.isBoardFull()) {
			System.out.println("It is a draw!");
			gameOver = true;
			return;
		}
		game.switchPlayer();
	}
		catch(RowNotValidException exception) {
			System.out.println(exception.getMessage());
		}
		catch(ColumnNotValidException exception) {
			System.out.println(exception.getMessage());
		}
		catch(PositionNotEmptyException exception) {
			System.out.println(exception.getMessage());
		}
		catch(InputMismatchException exception) {
			System.out.println("Enter a valid number");
			scanner.nextLine();
		}
		catch(Exception exception) {
			System.out.println("Something went wrong");
			return;
		}
	}
	

}
