/*
 * Each instance represents a Date, such as the birthday 
 * of an Author or the release date of a Document/Review.
 * 
 * */

import java.util.Calendar;

public class Date {
	
	private int day;
	private int month;
	private int year;
	
	
	public Date(int day, int month, int year) {
		
		/* set day to first if invalid input */
		
		if(!this.setDay(day)) {
			this.day = 1;
		}
		
		/* set month to January if invalid input */
		
		if(!this.setMonth(month)) {
			this.month = 1;
		}
		
		this.setYear(year);
	}
	
	
	public Date() {
		Calendar cal = Calendar.getInstance();
		this.day = cal.get(Calendar.DAY_OF_MONTH);
		this.month = cal.get(Calendar.MONTH) + 1; // MONTH starts with January which is 0
		this.year = cal.get(Calendar.YEAR);
	}
	
	
	
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
	
	
	/* 
	 * Change day only if input is valid for given month.
	 * 
	 * */
	
	public boolean setDay(int day) {
		if(day > 0 && day <= this.daysInMonth(this.month)) {
			this.day = day;
			return true;
		} 
		else { return false; }
	}
	
	
	/* 
	 * Input between 1-12 including 1 and 12. Only change month if value for
	 * day allows it.
	 * 
	 * */
	
	public boolean setMonth(int month) {
		
		if(month > 0 && month < 13 && this.day <= this.daysInMonth(month)) {
			
			this.month = month;
			return true;
			
		}
		else { 
			
			return false; 
			
		}
	}
	
	
	/* 
	 * If input value smaller than 1970, use 1970 - we start counting 
	 * from 1st of January 1970 .
	 * 
	 * */
	
	public void setYear(int year) {
		this.year = (year < 1970) ? 1970 : year;
	}
	
	
	public String toString() {
		
		return this.day + "." + this.month + "." + this.year;
		
	}
	
	
	
	/* 
	 * Starts with month 1 for January.
	 * 
	 * */
	
	private int daysInMonth(int month) {
		
		switch(month) {
		
			case 1: return 31;
			case 2: 
				if(this.year % 4 == 0) {
					return 29;
				} 
				else { 
					return 28;
				}
			case 3: return 31;
			case 4: return 30;
			case 5: return 31;
			case 6: return 30;
			case 7: return 31;
			case 8: return 31;
			case 9: return 30;
			case 10: return 31;
			case 11: return 30;
			case 12: return 31;
			default: return 0;
			
		}
		
	}
	
	
	private int daysSince1970() {
		
		int daysFullYears = (this.year - 1970) * 365;
		daysFullYears += (this.year - 1972) / 4; // counts extra days for completed leap years
		
		/* counts days for all completed months */
		
		int daysFullMonths = 0;
		
		for(int i = 1; i < this.month; i++) { 
			
			daysFullMonths += this.daysInMonth(i);
			
		}
		
		int days =  this.day - 1; // counts days in current month
		
		return daysFullYears + daysFullMonths + days;
	}
	
	
	/* 
	 * Returns negative integer if release date in the future.
	 * 
	 * */
	
	public int getAgeInDaysAt(Date today) {
		
		return today.daysSince1970() - this.daysSince1970();
		
	}
	
	
	public int getAgeInYearsAt(Date today) {
		
		int years = today.getYear() - this.getYear() - 1;
		
		/* add a day if birthday is today or was this year already */
		
		if(today.getMonth() >= this.getMonth() && today.getDay() >= this.getDay()) {
			
			years++;
			
		}
		
		return years;
	}
	
	
	public boolean equals(Date date) {
		
		if(date != null && this.daysSince1970() == date.daysSince1970()) {
			
			return true;
			
		}
		else {
			
			return false;
			
		}
		
	}
}
