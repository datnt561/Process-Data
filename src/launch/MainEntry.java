package launch;

import data.Data;
import data.DataNoStopWord;
import preprocess.DataNumber;
import preprocess.Voca;

public class MainEntry {

	public static void main(String[] args) {
		
		Data dataDomain = new Data("CableModem");
		
		//dataDomain.writeDataToFile("AlarmClockBinary.txt");
		
		DataNoStopWord datasetNoStopWord = new DataNoStopWord("CableModem");
		
		datasetNoStopWord.createDataNStopWord();
		//datasetNoStopWord.writerDataToFile();
		Voca voca = new Voca(datasetNoStopWord);
		
		voca.writeVocaToFile();
		
		DataNumber dataNumber = new DataNumber(datasetNoStopWord);
		
		dataNumber.writeReviewNoLable();
		
		

	}

}
