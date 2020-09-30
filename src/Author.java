/*
 * Each instance represents the Author of a Document or Review, or several.
 * 
 * Uses streams: to verify that firstName and lastName consist only of letters.
 * Uses RegEx: to verify email address.
 * 
 * */

import java.util.regex.Pattern;

public class Author {
	
	private String firstName;
	private String lastName;
	private Date birthday;
	private String residence;
	private String email;
	
	/* 
	 * Regular expression and related classes to verify email: 
	 * 	- for simplicity reasons, not all valid variations are accepted here 
	 *	- sub-domains after the @-sign are supported
	 * 
	 * */
	
	private static final String EMAIL_REGEX = "[\\w]+@[\\w]+(\\.[\\w]+)*(\\.[a-z]{2,})";
	
	
	public Author(String firstName, String lastName, Date birthday, String residence, String email) {
		
		if(!this.setFirstName(firstName)) {
			this.firstName = "Unknown";
		}
		
		if(!this.setLastName(lastName)) {
			this.lastName = "Unknown";
		}
		
		/* 
		 * if invalid parameter is passed into constructor, 
		 * author's birthday is set to Epoch (1.1.1970) 
		 * 
		 * */
		
		if(!this.setBirthday(birthday)) {
			this.birthday = new Date(1,1,1970);
		}
		
		if(!this.setResidence(residence)) {
			this.residence = "Unknown";
		}
		
		if(!this.setEmail(email)) { 
			this.email = "Unknown"; 
		}
	}
	
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public Date getBirthday() {
		return this.birthday;
	}
	
	public String getResidence() {
		return this.residence;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	
	/*
	 * firstName and lastName should only contain letters:
	 * String to char-stream to check if all match with 
	 * isLetter() method from class Character
	 * 
	 * */
	
	public boolean setFirstName(String firstName) {
		
		if(firstName != null && firstName.chars().allMatch(Character::isLetter)) {
			
			this.firstName = firstName;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
	}

	
	public boolean setLastName(String lastName) {
		
		if(lastName != null && lastName.chars().allMatch(Character::isLetter)) {
			
			this.lastName = lastName;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
	}

	
	/* 
	 * Validity is checked when object is created - no need here.
	 * 
	 * */
	
	public boolean setBirthday(Date birthday) {
		
		if(birthday != null) {
			
			this.birthday = birthday;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
	}

	
	public boolean setResidence(String residence) {
		
		if(residence != null) {
			
			this.residence = residence;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
	}

	
	/* 
	 * Uses Java Regular Expression to validate email.
	 * 
	 * */
	
	public boolean setEmail(String email) {
		
		if(email != null && Pattern.matches(EMAIL_REGEX, email)) {
			
			this.email = email;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
	}

	
	public String toString() {
		return this.firstName + " " + this.lastName;
	}
	
	
	public String getContactInformation() {
		return this.toString() + "\n" + this.email + "\n" + this.residence;
	}
	
	
	/* 
	 * Returns age in YEARS.
	 * 
	 * */
	
	public int getAgeAt(Date today) {
		return this.birthday.getAgeInYearsAt(today);
	}
	
	
	/* 
	 * Returns true only if first name, last name and birthday are equal.
	 * 
	 * */
	
	public boolean equals(Author author) {
		
		if(author != null 
				&& this.firstName.equals(author.getFirstName()) 
				&& this.lastName.equals(author.getLastName())
				&& this.birthday.equals(author.getBirthday())) 
		{
			
			return true;
			
		}
		else {

			return false;
			
		}
		
	}
	
}
