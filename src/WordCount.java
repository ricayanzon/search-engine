/*
 * Each instance represents a word and its absolute frequency (count) within 
 * a given Document. The frequency of a given word in a Document provides a 
 * basis for the rating of the search engine.
 * 
 * */


public class WordCount {
	
	private String word;
	private int count;
	
	
	public WordCount(String word, int count) {
		
		this.word = word;
		
		if(!this.setCount(count)) {
			
			this.count = 0; 
			
		}
		
	}
	
	
	public String getWord() {
		return this.word;
	}
	
	public int getCount() {
		return this.count;
	}
	
	
	public boolean setCount(int count) {
		
		if(count < 0) {
			
			return false;
			
		}
		else {
			
			this.count = count;
			return true;
			
		}
	}
	
	
	public int incrementCount() {
		
		return ++this.count;
		
	}
	
	public int incrementCount(int n) {
		
		if(n < 0) {
			
			return this.count;
			
		}
		else {
			
			return this.count += n;
			
		}
	}
	
	
	public boolean equals(WordCount wc) {
		
		if(wc != null 
				&& this.word.equals(wc.getWord()) 
				&& this.count == wc.getCount()) 
		{	
			
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}
}
