package preprocess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import data.Document;

import java.util.Set;

public class ComputeAverageWordTF_IDF {
	private HashMap<String, Double> wordAverageTFIDF;
	private ArrayList<String> topWord;

	public ComputeAverageWordTF_IDF(ArrayList<String> reviews) {
		// Tinh TF-IDF
		String[] allReview = reviews.toArray(new String[reviews.size()]);
		TF_IDF tf_idf = new TF_IDF(allReview);

		// Tinh trung binh TF-IDF cua cac word
		wordAverageTFIDF = new HashMap<String, Double>();
		double[][] tfMatrix = tf_idf.getTF_IDFMatrix();
		String[] wordVector = tf_idf.getWordVector();
		int timeWord;
		double sumTFWord;
		int wordVectorLength = tfMatrix[0].length;

		for (int i = 0; i < wordVectorLength; i++) {
			sumTFWord = 0;
			timeWord = 1;
			for (int j = 0; j < tfMatrix.length; j++) {

				if (tfMatrix[j][i] > 0) {
					sumTFWord += tfMatrix[j][i];
					timeWord++;
				}
			}
			wordAverageTFIDF.put(wordVector[i], sumTFWord / timeWord);
		}
		
		topWord = new ArrayList<String>();
		Set set = wordAverageTFIDF.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			Map.Entry ma = (Map.Entry)iterator.next();
			topWord.add((String) ma.getKey());
		}
		
		 
		//wordAverageTFIDF = sortByValues(wordAverageTFIDF);
	}

	public HashMap<String, Double> getWordAverageTFIDF() {
		return wordAverageTFIDF;
	}
	
	private static HashMap<String, Double> sortByValues(HashMap<String, Double> map) {
		 List list = new LinkedList(map.entrySet());
		 // Defined Custom Comparator here
		 Collections.sort(list, new Comparator() {
		 public int compare(Object o1, Object o2) {
		 return ((Comparable) ((Map.Entry)
		 (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
		 }
		 });
		
		 // Here I am copying the sorted list in HashMap
		 // using LinkedHashMap to preserve the insertion order
		 HashMap sortedHashMap = new LinkedHashMap();
		 for (Iterator it = list.iterator(); it.hasNext();) {
		 Map.Entry entry = (Map.Entry) it.next();
		 sortedHashMap.put(entry.getKey(), entry.getValue());
		 }
		 return sortedHashMap;

	}
	 
//	public static ArrayList<String> getSentimentWord(String nameFile){
//			String line = null;
//			ArrayList<String> sentimentWord = new ArrayList<String>();
//			ArrayList<Document> dataSet = new ArrayList<Document>();
//			try {
//				FileReader fileReader = new FileReader(nameFile);
//				BufferedReader bufferedReader = new BufferedReader(fileReader);
//				Document document = null;
//
//				while ((line = bufferedReader.readLine()) != null) {
//						dataSet.add(document);
//					}
//				}
//
//				fileReader.close();
//			} catch (FileNotFoundException ex) {
//				System.out.println("Unable to open file '" + nameFile);
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		
//	}
////
	
}
