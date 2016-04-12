package launch;

import data.Data;
import data.DataNoStopWord;
import preprocess.DataNumber;
import preprocess.Voca;

public class MainEntry {

	public static void main(String[] args) {
		
		Data dataDomain = new Data("AlarmClock");
		
		dataDomain.writeDataToFile("AlarmClockBinary.txt");
		
		DataNoStopWord datasetNoStopWord = new DataNoStopWord("AlarmClock");
		
		datasetNoStopWord.createDataNStopWord();
		//datasetNoStopWord.writerDataToFile();
		Voca voca = new Voca(datasetNoStopWord);
		
		voca.writeVocaToFile("AlarmClock.vocab");
		
		DataNumber dataNumber = new DataNumber(datasetNoStopWord);
		
		dataNumber.writeDataNumber();
		
		

	}

}
