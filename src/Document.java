/* 
 * Each instance of this class represents a Document.
 * 
 * */

public class Document {
	
	private String title;
	private String language;
	private String summary;
	private Date releaseDate;
	private Author author;
	private WordCountsArray wca;
	
	/* 
	 * Each document contains a text variable that represents the content of the document. In order for the
	 * search engine to work more efficiently, the content is processed so that only the roots of the words
	 * in the text are retained. Only the resulting word stems are then indexed by the search engine and used
	 * to compare the different documents. The following array contains the most common German suffixes.
	 * 
	 * */
	
	public static final String[] SUFFIXES = {"ab", "al", "ant", "artig", "bar", "chen", "ei", "eln", "en", "end",
			"ent", "er", "fach", "fikation", "fizieren", "fähig", "gemäß", "gerecht", "haft", "haltig", "heit", 
			"ie", "ieren", "ig", "in", "ion", "iren", "isch", "isieren", "isierung", "ismus", "ist", "ität", "iv",
			"keit", "kunde", "legen", "lein", "lich", "ling", "logie", "los", "mal", "meter", "mut", "nis", "or", 
			"sam", "schaft", "tum", "ung", "voll", "wert", "würdig"};
	
	
	public Document(String title, String content, String language, String summary, Date releaseDate, Author author) {
		
		if(!this.setTitle(title)) this.title = "Unknown";

		/* needs to initialize wca before content can be added to it */
		
		this.wca = new WordCountsArray(0);
		this.addContent(content);
		
		if(!this.setLanguage(language)) this.language = "Unknown";
		
		if(!this.setSummary(summary)) this.summary = "/";
		
		if(!this.setReleaseDate(releaseDate)) this.releaseDate = new Date();

		/* null values are replaced in the class implementation */
		
		if(!this.setAuthor(author)) this.setAuthor(new Author(null, null, null, null, null));
		
	}
	
	
	public String getTitle() {
		return this.title;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public String getSummary() {
		return this.summary;
	}
	
	public Date getReleaseDate() {
		return this.releaseDate;
	}
	
	public Author getAuthor() {
		return this.author;
	}
	
	public WordCountsArray getWordCounts() {
		return this.wca;
	}
	
	
	public boolean setTitle(String title) {
		
		if(title == null) {
			
			return false;
			
		}
		
		else {
			
			this.title = title;
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

	
	public boolean setSummary(String summary) {
		
		if(summary == null) {
			
			return false;
			
		}
		
		else {
			
			this.summary = summary;
			return true;
			
		}
	}

	
	public boolean setReleaseDate(Date releaseDate) {
		
		if(releaseDate == null) {
			
			return false;
		}
		
		else {
			
			this.releaseDate = releaseDate;
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

	
	public String toString() {
		
		return "Title: " + this.title + "\n\nSummary: " + this.summary + "\n\nRelease date: " 
					+ this.releaseDate.toString() + "\nAuthor: " + this.author.toString();
		
	}
	
	
	/* 
	 * Return age in DAYS.
	 * 
	 * */
	
	public int getAgeAt(Date today) {
		
		return this.releaseDate.getAgeInDaysAt(today);
		
	}
	
	
	/* 
	 * The method takes the text of the document and splits it into single words.
	 * Returned is a string array containing these words, whereas a word appearing
	 * multiple times throughout the content, is added to the array for each 
	 * occurrence. Dots, commas, colons, semicolons and brackets excluded.
	 *  
	 *  */
	
	private static String[] tokenize(String content) {
		
		if(content.length() < 1) return null;

		int startLastWord = 0;
		int currentIndex = 0;
		
		String[] words = new String[countWords(content)];
		
		
		for(int j = 0; j < content.length(); j++) {
			
			if(content.charAt(j) == ' ') {
				
				/* skip opening bracket or quote*/
				
				if(content.charAt(startLastWord) == '(' || content.charAt(startLastWord) == '"') {
					
					startLastWord++; 
					
				}
				
				char beforeSpace = content.charAt(j-1);
				
				if(beforeSpace == ',' || beforeSpace == '.' || beforeSpace == ':' || beforeSpace == ';' ||
						beforeSpace == ')' || beforeSpace == '"' || beforeSpace == '?' || beforeSpace == '!') {
					
					words[currentIndex++] = content.substring(startLastWord, j-1);
					
				}
				else {
					
					words[currentIndex++] = content.substring(startLastWord, j);
					
				}
				
				startLastWord = j+1;
				
			}
			
		}
		
		/* 
		 * Save last word, which is not followed by a space and therefore not
		 * captured by the for-loop before. Also, the text can end with a dot 
		 * '.', a question mark '?', an exclamation mark '!' or with the last
		 * letter of a word. Don't store the signs.
		 *  
		 *  */
		
		char lastChar = content.charAt(content.length()-1);
		
		if(lastChar == '.' || lastChar == '?' || lastChar == '!') {
			
			words[currentIndex] = content.substring(startLastWord, content.length()-1);
			
		}
		else {
			
			words[currentIndex] = content.substring(startLastWord, content.length());
			
		}
		
		return words;
		
	}
	
	
	private static int countWords(String content) {
		
		int count = 0;
		
		for(int i = 0; i < content.length(); i++) {
			
			/* 
			 * Spaces separate words, even when after a '.' or a ','.
			 * The following counts the words in content.
			 * 
			 * */
			
			if(content.charAt(i) == ' ') {
				count++;
			}
			
		}
		
		/* last word is not followed by a space */
		
		count++;
		
		return count;
		
	}
	
	
	/* 
	 * Returns true, if and only if the last n characters of the strings
	 * w1 and w2 are identical.
	 * 
	 * */
	
	private static boolean suffixesEqual(String w1, String w2, int n) {
		
		int w1l = w1.length();
		int w2l = w2.length();
		
		if(w1l < n || w2l < n) { return false; }
		
		for(int i = 1; i < n; i++) {
			
			if(w1.charAt(w1l-i) == w2.charAt(w2l-i)) {
				
				continue;
				
			}
			
			return false;
			
		}
		
		return true;
		
	}
	
	
	/*
	 * Checks whether one of the elements in the SUFFIXES array is
	 * a suffix of the passed string parameter. If this is the case,
	 * the method returns the corresponding suffix. Otherwise, it 
	 * returns an empty string ("").
	 * 
	 * */
	
	private static String findSuffix(String word) {
		
		String suffix = "";
		boolean isSuffix = false;
		
		for(int i = 0; i < SUFFIXES.length; i++) {
			
			suffix = SUFFIXES[i];
			isSuffix = suffixesEqual(word, suffix, suffix.length());
			
			if(isSuffix) {
				
				return suffix;
				
			}
			
		}
		
		return "";
		
	}
	
	
	/*
	 * Cuts the suffix and returns a string containing the passed word,
	 * without the suffix. If the passed suffix is not a suffix of the
	 * word parameter, the passed word is returned unchanged.
	 * 
	 * */
	
	private static String cutSuffix(String word, String suffix) {
		
		if(word.length() <= suffix.length()) return word;
		
		int suffixPos = 0;
		
		if(suffixesEqual(word, suffix, suffix.length())) {
			
			suffixPos = word.length()-suffix.length();
			return word.substring(0, suffixPos);
			
		}
		else {
			
			return word;
			
		}
		
	}
	
	
	/*
	 * The method takes the text of the document, i.e. the content, as parameter.
	 * It splits the text into separate words and removes the suffix for each word.
	 * The resulting word stems are then added to the class's WordCountsArray 
	 * attribute (wca).
	 * 
	 * */
	
	private void addContent(String content) {
		
		if(content == null) return;
		
		String[] tokens = tokenize(content);
		
		if(tokens == null) return;
		
		String currentWord;
		String suffix;
		
		for(int i = 0; i < tokens.length; i++) {
			
			currentWord = tokens[i];
			
			/* cut ending off only if word is longer than 4 chars */
			
			if(currentWord.length() > 4) {
				
				suffix = findSuffix(currentWord);
				
				currentWord = cutSuffix(currentWord, suffix);
				
				
			}

			this.wca.add(currentWord, 1);
			System.out.println(i + ": " + this.wca.getWord(i));
			
		}
		
		System.out.println(this.wca.size());
		
	}
	
	
	public boolean equals(Document document) {
		
		if(document != null 
				&& this.author.equals(document.getAuthor()) 
				&& this.title.equals(document.getTitle())
				&& this.releaseDate.equals(document.getReleaseDate())) 
		{
			
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}
	
	
	
	
	
	
	
	
	
}
