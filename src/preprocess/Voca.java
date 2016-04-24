package preprocess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import data.DataNoStopWord;
import data.Document;

public class Voca {
	private Hashtable<Integer, String> voca;
	String nameDomainVoca;

	public Voca(DataNoStopWord d) {
		nameDomainVoca = d.getNameDomain();
		voca = new Hashtable<Integer, String>();
		Set<String> reviews = createVoca(d);
		for (String s : reviews) {
			if (!s.isEmpty())
				addWord(s);
		}
	}

	public int getIndexWord(String word) throws Exception {
		Set<Entry<Integer, String>> listWord = voca.entrySet();
		System.out.println(listWord.toString());
		for (Entry<Integer, String> e : listWord) {
			System.out.println(e.getValue());
			if (e.getValue().equals(word))
				return e.getKey();
		}
		System.out.println("Error" + word + "sd");
		throw new Exception();

	}

	public Set<Entry<Integer, String>> entrySet() {
		return voca.entrySet();
	}

	public void addWord(String word) {
		if (!voca.contains(word)) {
			voca.put(nextKey(), word);
		}
	}

	private int nextKey() {
		if (voca.isEmpty())
			return 0;
		return voca.size();
	}
	
	public ArrayList<String> getWordInVoca(){
		ArrayList<String> wordInVoca = new ArrayList<String>();
		for(Entry<Integer, String> e : voca.entrySet()){
			wordInVoca.add(e.getValue());
		}
		return wordInVoca;
	}
	public Set<String> createVoca(DataNoStopWord d) {
		ArrayList<String> reviews = d.getReviewsNewDatasets();

		Set<String> wordInReviews = new HashSet<String>();
		String[] words;
		for (String s : reviews) {
			words = s.split(" ");
			wordInReviews.addAll(Arrays.asList(words));
		}

		return wordInReviews;
	}

	public void writeVocaToFile() {

		FileWriter writer;
		try {
			writer = new FileWriter(nameDomainVoca + ".vocab");
			List<Integer> ids = new ArrayList<Integer>(voca.keySet());
			Collections.sort(ids);
			String review = "";
			for (Integer i : ids) {
				review += i + ":" + voca.get(i) + "\n";
				System.out.println(review);

			}
			writer.write(review);

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// File file = new File(nameDomainVoca + ".vocab");
		//
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		//
		// FileWriter fileWritter = new FileWriter(file.getName(), true);
		// BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);
		// Set<Entry<Integer, String>> keys = voca.entrySet();
		// String voca = "";
		// for(Entry<Integer, String> e : keys){
		//
		// voca += e.getKey() + ":" + e.getValue() + "\n";
		// //bufferedWriter.write(e.getKey() + ":" + e.getValue() + "\n");
		// }
		// bufferedWriter.write(voca);
		// bufferedWriter.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

}
