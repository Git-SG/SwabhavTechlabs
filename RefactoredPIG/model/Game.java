package H9_ObjectCalesthenics_model;

public class Game {

	private Player player;

	public Game() {
		this.player = new Player();
	}

	public void playGame() {
		while (player.getTotalScore().isLessThan(20)) {
			startTurn();
		}
		endGame();
	}

	public void endGame() {
		System.out.println("You Finished in " + player.getNumberOfTurns() + " turns. \nGame Over");
	}

	public void startTurn() {
		Turn turn = new Turn(player);
		turn.startTurn();
	}

}
