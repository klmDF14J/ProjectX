package roboyobo.ball;

public class Language {
	

	private String key, name;
	
	public String play, battle, options, selectLanguage, backToMenu, languages, gameOver, rocksDestroyed, timeLasted, shockwavesUsed, totalScore, submit, enterUsername, highscore, username, score;
	
	public Language(String key, String[] values) {
		this.key = key;
		
		name = values[0];
		
		play = values[1];
		battle = values[2];
		options = values[3];
		selectLanguage = values[4];
		backToMenu = values[5];
		languages = values[6];
		gameOver = values[7];
		rocksDestroyed = values[8];
		timeLasted = values[9];
		shockwavesUsed = values[10];
		totalScore = values[11];
		submit = values[12];
		enterUsername = values[13];
		highscore = values[14];
		username = values[15];
		score = values[16];
	}
	
	public String getKey() {
		return key;
	}
	
	public String getName() {
		return name;
	}
}
