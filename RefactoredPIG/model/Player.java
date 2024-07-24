package H9_ObjectCalesthenics_model;

public class Player {
	
	private TotalScore totalScore;
	private Integer numberOfTurns;
	
	public Player() {
		this.totalScore = new TotalScore();
		this.numberOfTurns = 0;
	}

	public TotalScore getTotalScore() {
		return totalScore;
	}

	public Integer getNumberOfTurns() {
		return numberOfTurns;
	}
	
	public void setNumberOfTurns(Integer numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}

	public void hold(TurnScore turnScore) {
		totalScore.addToTotalScore(turnScore);
		System.out.println("Score for this turn: " + turnScore.getTurnScore());
		System.out.println("Total Score: " + totalScore.getTotalScore());
	}
	
	public void newTurn() {
		numberOfTurns++;
	}
	
}
