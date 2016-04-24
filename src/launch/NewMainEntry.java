package launch;

import java.util.ArrayList;

import data.Data;
import data.DataNoStopWord;
import data.NameDomains;
import preprocess.DataNumber;
import preprocess.Voca;

public class NewMainEntry {

	public static void main(String[] args) {
		
		NameDomains namedomain = new NameDomains("ACL2015-Chen-Datasets");
		ArrayList<String> listDomain = namedomain.getListNameDomain();
		for(String s : listDomain){
			Data dataDomain = new Data(s);
			
			DataNoStopWord datasetNoStopWord = new DataNoStopWord(s);
			
			datasetNoStopWord.createDataNStopWord();
			Voca voca = new Voca(datasetNoStopWord);
			voca.writeVocaToFile();
			
//			DataNumber dataNumber = new DataNumber(datasetNoStopWord);
//			
//			dataNumber.writeReviewNoLable();
		}
		
		
		

	}

}
