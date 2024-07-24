package H9_ObjectCalesthenics_model;

public class TotalScore {
	
	private Integer totalScore;

	public TotalScore() {
		this.totalScore = 0;
	}


	public Integer getTotalScore() {
		return totalScore;
	}
	
	public Integer addToTotalScore(TurnScore turnScore) {
		totalScore += turnScore.getTurnScore();
		return totalScore;
	}
	
	public boolean isLessThan(Integer limit) {
		return totalScore < limit;
		
	}

}
