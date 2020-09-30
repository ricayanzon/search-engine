/*
 * This class manages the frequencies of the different words in a document.
 * Therefore, each instance of the Document class has an instance of this 
 * class (WordCountsArray), which in turn contains many different instances
 * of the WordCount class.
 * 
 * */

public class WordCountsArray {

	private WordCount[] wca;
	private int nextEmptyPos;
	
	
	public WordCountsArray(int initSize) {
		
		if(initSize < 1) {
			
			this.wca = new WordCount[1];
			
		}
		else {
			
			this.wca = new WordCount[initSize];
			
		}
		
		this.nextEmptyPos = 0;
		
	}
	
	
	public WordCount[] getWordCount() {
		
		return this.wca;
		
	}
	
	
	public void add(String word, int count) {	
		
		if(word == null || word.isEmpty() || word.equals(" ")) {
			
			return; 
			
		}
		
		WordCount wc = new WordCount(word, count);
		
		/* 
		 * if array full (too small), create new array with double
		 * the size and copy old wca to new array. re-link wca to
		 * reference the new (copied version) array.
		 * 
		 * */
		
		if(this.nextEmptyPos == this.wca.length) {
			
			increaseSize();
			
		}
		
		this.wca[this.nextEmptyPos++] = wc;
		
	}
	
	
	/* 
	 * Helper method for add(word, count).
	 * 
	 * */
	
	private void increaseSize() {
		
		WordCount[] tmp = new WordCount[this.wca.length * 2];
		
		for(int i = 0; i < this.nextEmptyPos; i++) {
			
			tmp[i] = this.wca[i];
			
		}
		
		/* re-link wca to reference new array - tmp will be deleted by GC */
		
		this.wca = tmp;
		
	}
	
	
	public int size() {
		
		return this.nextEmptyPos;
		
	}
	
	
	public String getWord(int index) {
		
		if(index < 0 && index >= this.nextEmptyPos) {
			
			return "";
			
		}
		
		return this.wca[index].getWord();
		
	}
	
	
	public int getCount(int index) {
		
		if(index < 0 && index >= this.nextEmptyPos) {
			
			return -1;
			
		}
		
		return this.wca[index].getCount();
		
	}
	
	
	public void setCount(int index, int count) {
		
		if(index < 0 && index >= this.nextEmptyPos) {
			
			return;
			
		}
		
		this.wca[index].setCount(count);
		
	}
	
	
	public boolean equals(WordCountsArray wca) {
		
		if(wca != null && this.wca.equals(wca.getWordCount())) {
			
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}
	
}
