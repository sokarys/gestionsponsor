package fr.iut2.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileFactory {



	// Prend en paramètre le chemin vers un repertoire
	// Retourne la liste des chemins des fichiers contenus dans ce repertoire
	public static ArrayList<String> getFilesNames(String path) {
		ArrayList<String> lisFilesNames = new ArrayList<String>();
		File repertoire = new File(path);

		if (repertoire.isDirectory()) {
			File[] list = repertoire.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					lisFilesNames.add(list[i].getPath());
				}
			} else {
				System.err.println(repertoire + " : Erreur de lecture.");
			}

		}
		return lisFilesNames;
	}

	// Prend en paramètre le chemin vers un fichier texte
	// Retourne le texte de ce fichier
	public static String getText(String filePath) throws FileNotFoundException {

		String text = "";
		File fichier = new File(filePath);

               // System.out.print(fichier.getAbsolutePath());
		Scanner scanner = new Scanner(fichier);
		while (scanner.hasNextLine()) {
			text = text + scanner.nextLine() + "\n";
                        
		}
		scanner.close();
		return text;
	}

	// Prend en paramètre le chemin vers un fichier texte
	// Et le texte à ajouter en fin de ce fichier
	public static void setText(String filePath, String text) {

		File fichier = new File(filePath);
		FileWriter writer = null;

		try {
			fichier.createNewFile();
		} catch (IOException e) {
			System.out.println("do nothing");
		}

		try {
			writer = new FileWriter(fichier, false);
			writer.write(text);

		} catch (IOException ex) {
			ex.printStackTrace();

		}
		
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}