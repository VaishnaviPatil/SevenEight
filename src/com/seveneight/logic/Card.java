package com.seveneight.logic;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.seveneight.R;

public class Card 
{

	/* Suits */
	public static final char HEART = 'h';
	public static final char SPADE = 's';
	public static final char DIAMOND = 'd';
	public static final char CLUB = 'c';
	
	/* Picture Cards */
	public static final char JACK = 'j';
	public static final char QUEEN = 'q';
	public static final char KING = 'k';
	public static final char TEN = 't';

	private char suite;
	private char value;
	private boolean isVisible;
	
	private static final HashMap<Character, Integer> valuesMap = new HashMap<Character, Integer>();
	static
	{
		valuesMap.put('1', 14);
		valuesMap.put('k', 13);
		valuesMap.put('q', 12);
		valuesMap.put('j', 11);
		valuesMap.put('t', 10);
		valuesMap.put('9', 9);
		valuesMap.put('8', 8);
		valuesMap.put('7', 7);
		valuesMap.put('6', 6);
		valuesMap.put('5', 5);
		valuesMap.put('4', 4);
		valuesMap.put('3', 3);
		valuesMap.put('2', 2);
	}
			
	public Card()
	{
		
	}
	
	public Card(char suite, char value)
	{
		this.suite = suite;
		this.value = value;
	}
	
	public void setSuite(char suite)
	{
		this.suite = suite;
	}
	
	public void setValue(char value)
	{
		this.value = value;
	}
	
	public void setIsVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}
	
	public char getSuite()
	{
		return suite;
	}
	
	public char getValue()
	{
		return value;
	}
	
	public boolean getIsVisible()
	{
		return isVisible;
	}
	
	public String getImageName()
	{
		return suite+"_"+value;
	}
	
	public int getImageResourceId(){
		Field f;
		int id = -1;
		try {			
			f = R.drawable.class.getDeclaredField(getImageName());
			id = f.getInt(null);
			//TODO: Add logging!
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return id;
	}
		
	public boolean isSameSuite(Card c)
	{
		if(this.suite == c.suite)
		{
			return true;
		}
		
		return false;
	}
	
	/* Returns true if the current card has a greater value than card c */
	public boolean isGreater(Card c)
	{		
		if(valuesMap.get(this.value) > valuesMap.get(c.value))
		{
			return true;
		}
		
		return false;
	}
}
