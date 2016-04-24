package launch;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import Util.Utility;
import data.Data;
import data.DataNoStopWord;
import data.Document;
import data.NameDomains;
import preprocess.Voca;

public class CreateVectorBinaryFeature {
	public static void main(String[] args) {

		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		for (String s : listDomain) {
			Data dataDomain = new Data(s);

			DataNoStopWord datasetNoStopWord = new DataNoStopWord(s);

			datasetNoStopWord.createDataNStopWord();
			Voca voca = new Voca(datasetNoStopWord);
			// voca.writeVocaToFile();
			
			// tao vocab
			ArrayList<String> vocab = voca.getWordInVoca();
			
			//
			ArrayList<String> vectorFeature = new ArrayList<String>();
			StringBuffer sb ;
			ArrayList<String> allReview = datasetNoStopWord.getReviewsNewDatasets();
			ArrayList<String> lables = datasetNoStopWord.getLables();
			ArrayList<String> words;
			int i = 0;
			for(String review : allReview){
				sb = new StringBuffer();
				words = new ArrayList<String>(Arrays.asList(review.split(" ")));
				for(String w : vocab){
					if(words.contains(w))
						sb.append(1);
					else
						sb.append(0);
					sb.append(",");
				}
					
				vectorFeature.add(sb.toString() + lables.get(i));
				i++;
			}
			Utility.writerDataToARFF(vectorFeature, vocab, s);
		}
	}
	
//	public ArrayList<String> readSentimentWord() {
//		String line = null;
//		ArrayList<String> vocab = new ArrayList<String>();
//		try {
//			FileReader fileReader = new FileReader("neg.txt");
//			BufferedReader bufferedReader = new BufferedReader(fileReader);
//			Document document = null;
//
//			while ((line = bufferedReader.readLine()) != null) {
//					vocab.
//				}
//			}
//
//			fileReader.close();
//		} catch (FileNotFoundException ex) {
//			System.out.println("Unable to open file '" + nameFile);
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		return dataSet;
//	}
}
