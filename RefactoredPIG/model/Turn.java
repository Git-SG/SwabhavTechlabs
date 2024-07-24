package H9_ObjectCalesthenics_model;

import java.lang.Math;
import java.util.Scanner;

public class Turn {
	
	private Player player;
	private TurnScore turnScore;
	
	public Turn(Player player) {
		this.player = player;
		this.turnScore = new TurnScore();
	}
	
	public void startTurn() {
		player.newTurn();
		System.out.println("TURN " + player.getNumberOfTurns());
		String action = selectAction();
		while(action.equalsIgnoreCase("r")) {
			rollDice();
			if (turnScore.getTurnScore() == 0) {
				return;
			}
			action = selectAction();
		}
		
		if (action.equalsIgnoreCase("h")) {
			player.hold(turnScore);
			return;
		}
		
		System.out.println("Invalid action");
		player.setNumberOfTurns(player.getNumberOfTurns()-1);
		startTurn();
	}
	
	public void rollDice() {
		Integer rolledNumber = (int)((Math.random()*6)+1);
		System.out.println("Die: " + rolledNumber);
		if (rolledNumber == 1) {
			turnScore.setTurnScore(0);
			return;
		}
		
		turnScore.addTurnScore(rolledNumber);
	}
	
	public String selectAction() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Turn Score = " + turnScore.getTurnScore() + " Total Score = " + player.getTotalScore().getTotalScore() + " Roll or Hold? (r/h)");
		String action = scanner.next();
		
		return action;
	}

}
