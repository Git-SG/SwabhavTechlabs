package H9_ObjectCalesthenics_model;

public class TurnScore {
	
	private Integer turnScore;
	
	public TurnScore() {
		this.turnScore = 0;
	}

	public Integer getTurnScore() {
		return turnScore;
	}
	
	public void setTurnScore(Integer turnScore) {
		this.turnScore = turnScore;
	}

	public Integer addTurnScore(Integer number) {
		turnScore += number;
		
		return turnScore;
	}

}
