package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class DataNoStopWord extends Data {
	public DataNoStopWord(String namedomain) {
		super(namedomain);
		// TODO Auto-generated constructor stub
	}

	private Hashtable<String, ArrayList<String>> newDatasets;

	public void createDataNStopWord() {
		newDatasets = new Hashtable<String, ArrayList<String>>();
		ArrayList<String> bagOfWordReview = new ArrayList<String>();
		int i = 0;
		ArrayList<ArrayList<String>> reviewNStopWord = removeStopWords();
		if (reviewNStopWord.isEmpty()) {
			System.out.println("ReviewNStopWord is Null");
		} else {
			for (Document d : data) {
				newDatasets.put(d.label, reviewNStopWord.get(i));
				i++;
			}
		}

	}

	private ArrayList<String> readFileStopWords(String nameFile) {
		nameFile += ".txt";
		ArrayList<String> stopWords = new ArrayList<String>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(nameFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				stopWords.add(line);
			}

			fileReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + nameFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return stopWords;
	}

	public void writerDataToFile() {
		try {
			File file = new File(super.getNameDomain() + ".docs");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);
			Set<Entry<String, ArrayList<String>>> documents = newDatasets.entrySet();
			String review;
			for (Entry<String, ArrayList<String>> e : documents) {
				review = "";
				for(String s : e.getValue()){
					review += s + " ";
				}
				bufferedWriter.write(e.getKey() + "," + review + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ArrayList<String>> removeStopWords() {
		ArrayList<String> stopWords = readFileStopWords("stopwords");
		ArrayList<String> reviews = super.getReviews();
		ArrayList<ArrayList<String>> reviewsNoStopWord = new ArrayList<ArrayList<String>>();
		ArrayList<String> words;

		for (String s : reviews) {
			words = Util.Utility.splitReviewtoWords(s);
			words.removeAll(stopWords);

			reviewsNoStopWord.add(words);
		}

		return reviewsNoStopWord;
	}

	// public ArrayList<String> getReviews() {
	// ArrayList<String> wordReviews = new ArrayList<String>();
	// for (Document d : newDatasets) {
	// wordReviews.add(d.review);
	// }
	//
	// return wordReviews;
	// }

}
