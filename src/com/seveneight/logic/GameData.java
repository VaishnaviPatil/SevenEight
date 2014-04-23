package com.seveneight.logic;

public class GameData {

	private Deck deck;
	
	public Card mHandCards[], hHandCards[];
	public Card mTableCards[], hTableCards[];
	public Card playedCard1, playedCard2;
	
	public char trumpSuite;
	
	public boolean firstTurn = true;
	public boolean machineTurn = false;
	
	public int machineHands;
	public int machineHandsTodo;
	public int turnsOver; 
	
	public static int machineGames;
	public static int humanGames;

	public GameData(int machineHands)
	{
		this.deck = new Deck();
		mHandCards = new Card[5];
		hHandCards = new Card[5];
		
		mTableCards = new Card[10];
		hTableCards = new Card[10];
		
		machineHandsTodo = machineHands;
		machineHands = 0;
		turnsOver = 0;			
	}
	
	public Deck getDeck()
	{
		return deck;
	}
	
	public void setTrumpSuite(char trumpSuite)
	{
		this.trumpSuite = trumpSuite;
	}	
}
