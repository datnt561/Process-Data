package launch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Util.Utility;
import data.Data;
import data.DataNoStopWord;
import data.NameDomains;
import preprocess.ComputeAverageWordTF_IDF;
import preprocess.DataNumber;
import preprocess.TF_IDF;
import preprocess.Voca;

public class NewTest {

	public static void main(String[] args) {
		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		// for(String s : listDomain){
		// Data dataDomain = new Data(s);
		//
		// DataNoStopWord datasetNoStopWord = new DataNoStopWord(s);
		//
		// datasetNoStopWord.createDataNStopWord();
		//
		// String[] reviews = null;
		// datasetNoStopWord.getReviewsNewDatasets().toArray(reviews);
		// TF_IDF tf_idf = new TF_IDF(reviews);
		// for (String s1 : tf_idf.getWordVector()) {
		// System.out.print(s1 + "\t");
		// }
		// System.out.println("");
		// for (double[] docV : tf_idf.getTF_IDFMatrix()) {
		// for (double v : docV) {
		// System.out.print(v + "\t");
		// }
		// System.out.println("");
		// }
		//
		//// Voca voca = new Voca(datasetNoStopWord);
		//// voca.writeVocaToFile();
		////
		//// DataNumber dataNumber = new DataNumber(datasetNoStopWord);
		////
		//// dataNumber.writeReviewNoLable();
		// }
		
		
		// tao du lieu
		Data dataDomain = new Data("AlarmClock");
		DataNoStopWord datasetNoStopWord = new DataNoStopWord("AlarmClock");

		datasetNoStopWord.createDataNStopWord();
		ArrayList<String> allReviews = datasetNoStopWord.getReviewsNewDatasets();

		// Tinh tf-idf
		ComputeAverageWordTF_IDF cAverageWordTF_IDF = new ComputeAverageWordTF_IDF(allReviews);
		
		Set set = cAverageWordTF_IDF.getWordAverageTFIDF().entrySet();
		
		Iterator iterator = set.iterator();
		int i = 0;
		ArrayList<String> topWord = new ArrayList<String>();
		while(iterator.hasNext()){
			Map.Entry ma = (Map.Entry)iterator.next();
			if((Double)ma.getValue() > 0.1){
				topWord.add( ma.getKey().toString());
				System.out.println(ma.getKey());
			}
				
			
		}
		
		Utility.writerListWordToFile(topWord, "topword.txt");

	}

}
