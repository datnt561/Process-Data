package preprocess;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

import data.DataNoStopWord;

public class Voca {
	private Hashtable<Integer, String> voca;

	public Voca(DataNoStopWord d) {
		voca = new Hashtable<Integer, String>();
		Set<String> reviews = createVoca(d);
		for (String s : reviews) {
			addWord(s);
		}
	}
	
	public int getIndexWord(String word) throws Exception{
		Set<Entry<Integer, String>> listWord = voca.entrySet();
		for(Entry<Integer, String> e : listWord){
			if(e.getValue().equals(word))
				return e.getKey();
		}
		System.out.println("Error");
		throw new Exception();
		
	}
	public Set<Entry<Integer, String>> entrySet(){
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

	public void writeVocaToFile(String nameFile) {
		FileWriter writer;
		try {
			writer = new FileWriter(nameFile);
			Set<Entry<Integer, String>> keys = voca.entrySet();
			
			for(Entry<Integer, String> e : keys){
				writer.write(e.getKey() + ":" + e.getValue());
				writer.write("\n");
			}
			

			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
