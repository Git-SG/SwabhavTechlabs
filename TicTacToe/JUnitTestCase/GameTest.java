package com.aurionpro.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

	Game game;

	@BeforeEach
	void init() {
		game = new Game();
	}

	@AfterEach
	void status() {
		System.out.println("Tested successfully");
	}

	@Test
	void testCreateEmptyBoard() {
		game.createEmptyBoard();
		char[][] board = game.getBoard();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(' ', board[i][j]);
			}
		}
	}

	@Test
	void testAddMark() {
		game.addMark(0, 0, 'X');
		assertEquals('X', game.getBoard()[0][0]);
	}

	@Test
	void testSwitchPlayer() {
		assertEquals('X', game.getPlayer());
		game.switchPlayer();
		assertEquals('O', game.getPlayer());
		game.switchPlayer();
		assertEquals('X', game.getPlayer());
	}

	@Test
	void testIsRowSame() {
		game.addMark(0, 0, 'X');
		game.addMark(0, 1, 'X');
		game.addMark(0, 2, 'X');
		assertTrue(game.isRowSame());
	}

	@Test
	void testIsColumnSame() {
		game.addMark(0, 0, 'X');
		game.addMark(1, 0, 'X');
		game.addMark(2, 0, 'X');
		assertTrue(game.isColumnSame());
	}

	@Test
	void testIsDiagonalSame() {
		game.addMark(0, 0, 'X');
		game.addMark(1, 1, 'X');
		game.addMark(2, 2, 'X');
		assertTrue(game.isDiagonalSame());
	}

	@Test
	void testIsGameOver() {
		game.addMark(0, 2, 'X');
		game.addMark(1, 1, 'X');
		game.addMark(2, 0, 'X');
		assertTrue(game.isGameOver());
	}

	@Test
	void testIsBoardFull() {
		game.addMark(0, 0, 'X');
		game.addMark(0, 1, 'X');
		game.addMark(0, 2, 'X');
		game.addMark(1, 0, 'X');
		game.addMark(1, 1, 'X');
		game.addMark(1, 2, 'X');
		game.addMark(2, 0, 'X');
		game.addMark(2, 1, 'X');
		game.addMark(2, 2, 'X');
		assertTrue(game.isBoardFull());
	}

}
