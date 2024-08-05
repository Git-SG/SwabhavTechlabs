package com.aurionpro.model;

public class Game {
	
	private char[][] board;
	private char player = 'X';

	public Game() {
		board = new char[3][3];
		createEmptyBoard();
	}

	public void createEmptyBoard() {
		for(int row = 0; row < board.length; row++)
			createColumns(row);
	}
	
	public void createColumns(int row) {
		for(int column = 0; column < board[0].length; column++)
			board[row][column] = ' ';
	}
	
	public void printBoard() {
		System.out.println("    0   1   2");
		System.out.println("  -------------");
		for(int row = 0; row < board.length; row++) {
			System.out.print(row + " | ");
			for(int column = 0; column < board[0].length; column++)
			{
				System.out.print(board[row][column] + " | ");
			}
			System.out.println();
			System.out.println("  -------------");
		}
	}
	
	public void addMark(int row, int column, char player) {		
		board[row][column] = player;
	}
	
	public void switchPlayer() {
		player = (player == 'X') ? 'O':'X';
	}

	public boolean isRowSame() {
		for(int row = 0; row < board.length; row++)
			if(board[row][0] == board[row][1] && board[row][1] == player && board[row][2] == player)
				return true;
		
		return false;
	}
	
	public boolean isColumnSame() {
		for(int column = 0; column < board[0].length; column++)
			if(board[0][column] == player && board[1][column] == player && board[2][column] == player)
				return true;
		
		return false;
	}
	
	public boolean isDiagonalSame() {
		if(board[1][1] != ' ' && ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1] && board[1][1] == board[2][0])))
			return true;
		
		return false;
	}
	
	public boolean isGameOver() {
		if (isRowSame() || isColumnSame() || isDiagonalSame())
			return true;
		
		return false;
	}
	
	public boolean isBoardFull() {
		for(int row = 0; row < board.length; row++)
			for(int column = 0; column < board[0].length; column++)
				if(board[row][column] == ' ')
					return false;
		
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public char getPlayer() {
		return player;
	}

}


