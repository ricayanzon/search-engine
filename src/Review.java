/* 
 * Each instance of this class represents a Review, 
 * which is created to an existing Document.
 * 
 * */


public class Review {
	
	private String content;
	private Author author;
	private Document reviewedDocument;
	private String language;
	private Date releaseDate;
	private int rating;
	
	
	public Review(String content, Author author, Document reviewedDocument, String language, Date releaseDate, 
			int rating) {
		
		if(!this.setContent(content)) {
			this.content = "-";
		}
		
		if(!this.setAuthor(author)) {
			
			/* null values are replaced in the class implementation */
			
			this.setAuthor(new Author(null, null, null, null, null));
		}
		
		if(!this.setReviewedDocument(reviewedDocument)) {
			
			this.reviewedDocument = new Document(null, null, null, null, null, null);
			
		}
		
		if(!this.setLanguage(language)) {
			this.language = "Unknown";
		}
		
		/* 
		 * The release date of this review is set to the date 
		 * of the day when the object was created, if constructor
		 * is called with invalid input.
		 * 
		 * */
		
		if(!this.setReleaseDate(releaseDate)) {
			this.releaseDate = new Date();
		}
		
		if(!this.setRating(rating)) {
			this.rating = -1;
		};
	}
	
	
	public String getContent() {
		return this.content;
	}
	
	public Author getAuthor() {
		return this.author;
	}
	
	public Document getReviewedDocument() {
		return this.reviewedDocument;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	
	public boolean setContent(String content) {
		
		if(content == null) {
			
			return false;
			
		}
		else {
			
			this.content = content;
			return true;
			
		}
		
	}

	
	public boolean setAuthor(Author author) {
		
		if(author == null) {
			
			return false;
			
		}
		else {
			
			this.author = author;
			return true;
			
		}
		
	}

	
	public boolean setReviewedDocument(Document reviewedDocument) {
		
		if(reviewedDocument == null) {
			
			return false;
			
		}
		else {
			
			this.reviewedDocument = reviewedDocument;
			return true;
			
		}
		
	}

	public boolean setLanguage(String language) {
		
		if(language == null) {
			
			return false;
			
		}
		else {
			
			this.language = language;
			return true;
			
		}
	}

	
	/* 
	 * Returns false if this.releaseDate is older than reviewedDocument.releaseDate.
	 * 
	 *  */
	
	public boolean setReleaseDate(Date releaseDate) {
		
		if(releaseDate == null || this.reviewedDocument.getReleaseDate().getAgeInDaysAt(releaseDate) < 0) {
			
			return false;
			
		}
		else {
			
			this.releaseDate = releaseDate;
			return true;
			
		}
	}
	
	
	/* 
	 * Valid rating between 1-10.
	 * 
	 * */

	public boolean setRating(int rating) {
		
		if(rating >= 0 && rating <= 10) {
			
			this.rating = rating;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
		
	}

	
	public String toString() {
		
		return "Title: " + this.reviewedDocument.getTitle() + "\nReviewed by: " + this.author.toString()
				+ "\nRating: " + this.rating + "\nRelease date: " + this.releaseDate.toString();
		
	}
	
	
	/* 
	 * Return age in DAYS.
	 * 
	 * */
	
	public int getAgeAt(Date today) {
		
		return this.releaseDate.getAgeInDaysAt(today);
		
	}
	
	
	public boolean equals(Review review) {
		
		if(review != null 
				&& this.author.equals(review.getAuthor()) 
				&& this.reviewedDocument.equals(review.getReviewedDocument())
				&& this.releaseDate.equals(review.getReleaseDate())) 
		{
			
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}
	
}
