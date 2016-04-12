package preprocess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map.Entry;

import data.DataNoStopWord;
import data.Document;

public class DataNumber {
	DataNoStopWord dataNSWord;
	ArrayList<Document> dataNumber;
	public DataNumber(DataNoStopWord dataset){
		dataNSWord = dataset;
		dataNumber = new ArrayList<Document>();
		Voca voca = new Voca(dataset);
		String[] reviews;
		String allReviewNStopWord = "";
		for(Document d : dataset.getNewDatasets()){
			allReviewNStopWord += d.getReview() + "\n";
		}
		
		Set<Entry<Integer, String>> listWord = voca.entrySet();
		for(Entry<Integer, String> e : listWord){
			allReviewNStopWord = allReviewNStopWord.replaceFirst(e.getValue(), e.getKey().toString());
		}
		reviews = allReviewNStopWord.split("\n");
		int i = 0;
		for(Document d : dataset.getNewDatasets()){
			dataNumber.add(new Document(d.getLabel(), reviews[i]));
			i++;
		}
	}
	
	public void writeDataNumber(){
		try {
			File file = new File(dataNSWord.getNameDomain() + ".docs");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);

			for (Document d : dataNumber) {

				bufferedWriter.write(d.getLabel() + "," + d.getReview() + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
