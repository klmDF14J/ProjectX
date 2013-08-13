package roboyobo.ball;

import java.util.ArrayList;
import java.util.Locale;

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
				"More",
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
				"General(Unused)",
				"Video",
				"Audio",
				"Game",
				"Settings",
				"Colour",
				"Aim Line",
				"On",
				"Off",
				"Shop",
				"HUD Scale"
		}));
		languages.add(new Language("fr_FR", new String[]{
				"Francais",
				"Jouer",
				"Bataille",
				"Plus",
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
				"General(Unused)",
				"Video",
				"Acoustique",
				"Jeu",
				"Reglages",
				"Couleur",
				"But ligne",
				"Sur",
				"De",
				"Magasiner",
				"HUD Echelle"
		}));
	}
	
	public static void workOutAndSetLanguage() {
		Locale locale = Locale.getDefault();
		String key = locale.getDisplayLanguage();
		System.out.println(key);
		for(int i = 0; i < languages.size(); i++) {
			if(key == languages.get(i).getName()) {
				GameInfo.language = languages.get(i);
			}
		}
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
		System.out.println("Language is now " + languages.get(i).getName());
		GameInfo.language = languages.get(i);
	}
}
