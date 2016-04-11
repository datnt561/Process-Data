package data;

public class Document {
	String label;
	String review;

	public Document(String label, String review) {
		this.label = label;
		this.review = review;
	}

	public String getLabel() {
		return label;
	}

	public String getReview() {
		return review;
	}

}
