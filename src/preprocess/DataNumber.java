package preprocess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.DataNoStopWord;
import data.Document;

public class DataNumber {
	DataNoStopWord dataNSWord;
	ArrayList<Document> dataNumber;

	public DataNumber(DataNoStopWord dataset) {
		dataNSWord = dataset;
		dataNumber = new ArrayList<Document>();
		Voca voca = new Voca(dataset);
		for (Document d : dataset.getNewDatasets()) {
			dataNumber.add(new Document(d.getLabel(), replaceWord(d.getReview().trim(), voca)));
		}
	}

	public void writeDataNumber() {
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

	private String replaceWord(String review, Voca voca) {
		String[] arrReview = review.split(" ");
		StringBuffer sb = new StringBuffer();
		int id;
		String s;
		int i = 0;
		for (String word : arrReview) {
			if(!word.isEmpty()){
				try {
					id = voca.getIndexWord(word);
					if (i == 0){
						sb.append(id);
						i++;
					}
						
					else
						sb.append(" " + id);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}
		return sb.toString().trim();

	}

	public void writeReviewNoLable() {
		try {
			File file = new File(dataNSWord.getNameDomain() + ".docs");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWritter);

			for (Document d : dataNumber) {

				bufferedWriter.write(d.getReview() + "\n");
			}
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
