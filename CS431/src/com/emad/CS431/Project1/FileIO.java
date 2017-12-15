package com.emad.CS431.Project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

	protected static ArrayList<String> getFileContent(String file) {
		ArrayList<String> a = new ArrayList<>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				a.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return a;
	}
}