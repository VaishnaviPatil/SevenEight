package com.seveneight.logic;

import java.util.Collections;
import java.util.Stack;

public class Deck 
{
		
	protected char[] suites = {Card.CLUB,Card.SPADE,Card.HEART,Card.DIAMOND};
	protected char[] values = {'1','7','8','9',Card.TEN,Card.JACK,Card.QUEEN,Card.KING};

	protected Stack<Card> cards;
	
	public Deck()
	{
		Stack<Card> tempDeck = new Stack<Card>();
		for (char suite : suites)  
		{
			for (char value : values) 
			{
				if(value == '7')
				{
					/* Skip CLUB and DIAMOND sevens */
					if(suite == Card.CLUB || suite == Card.DIAMOND)
						continue;
				}
				tempDeck.add(new Card(suite,value));
			}
		}
		
		Collections.shuffle(tempDeck);
		cards = tempDeck;
	}
	
	public void reLoad()
	{
		Stack<Card> tempDeck = new Stack<Card>();
		for (char suite : suites)  
		{
			for (char value : values) 
			{
				if(value == '7')
				{
					/* Skip CLUB and DIAMOND sevens */
					if(suite == Card.CLUB || suite == Card.DIAMOND)
						continue;
				}
				tempDeck.add(new Card(suite,value));
			}
		}
		
		Collections.shuffle(tempDeck);
		cards = tempDeck;
	}
	
	public Card popTopCard()
	{		
		Card topCard = null;
		
		if(!cards.isEmpty())
		{
			topCard = cards.pop();
		}
		
		return topCard;
	}
}