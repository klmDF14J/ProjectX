package roboyobo.ball;

import java.util.ArrayList;

import roboyobo.ball.util.GameInfo;

public class LanguageHandler {
	public static ArrayList<Language> languages;
	
	public static ArrayList<String> names;
	
	
	public static void init() {
		languages = new ArrayList<Language>();
		
		languages.add(new Language("en_GB", new String[]{
				"English",
				"Play",
				"Battle",
				"Options",
				"Select a language",
				"Back to menu",
				"Languages",
				"Game Over",
				"Rocks Destroyed",
				"Time Lasted",
				"Shockwaves Used",
				"Total Score",
				"Submit",
				"Enter username",
				"Highscores",
				"Name",
				"Score",
				"Player",
				"Back",
				"Rocks",
				"Time",
				"Vs",
				"General",
				"Video",
				"Audio",
				"Game",
				"Settings"
		}));
		languages.add(new Language("fr_FR", new String[]{
				"Francais",
				"Jouer",
				"Bataille",
				"Les options",
				"Selectionnez une langue",
				"Retour au menu",
				"Langues",
				"Jeu Termine",
				"Roches detruit",
				"Temps dure",
				"Ondes de choc occasion",
				"Total des points",
				"Soumettre",
				"Entrer un nom d'",
				"Meilleurs scores",
				"Nom",
				"Score",
				"Joueur",
				"Retour",
				"Roches",
				"Heure",
				"Vs",
				"General",
				"Video",
				"Acoustique",
				"Jeu",
				"Reglages"
		}));
	}
	
	public static void setLanguageToKey(String key) {
		for(Language language : languages) {
			if(key.equals(language.getKey())) {
				GameInfo.language = language;
				break;
			}
		}
	}

	public static void changeLanguage(int i) {
		GameInfo.language = languages.get(i);
	}
}
