package com.seveneight.ui;

import java.util.ArrayList;
import java.util.List;

import com.seveneight.R;
import com.seveneight.logic.Card;
import com.seveneight.logic.GameData;

import android.opengl.Visibility;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SevenEight extends Activity implements OnClickListener
{
	public static final String LOG_TAG = "SevenEight";
	
	final Context context = this;
	private GameData gameData;

	private ImageView mHandCardsImageViews[] = new ImageView[5];
	private ImageView mTableCardsImageViews[] = new ImageView[10];

	private ImageView hHandCardsImageViews[] = new ImageView[5];
	private ImageView hTableCardsImageViews[] = new ImageView[10];

	private ImageView playedCard1, playedCard2;
	private ImageView hukumImageView;
	
	private TextView machineScore, humanScore;

	/* HUKUM dialog elements */
	private Dialog hukumDialog;
	private ImageView[] suitesImageViews = new ImageView[4];	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seven_eight);
		
		Log.i(LOG_TAG, "onCreate");
		
		initialiseImages();
		dealHandCards();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seven_eight, menu);
		return true;
	}

	private void initialiseImages()
	{
		mHandCardsImageViews[0] = (ImageView) findViewById(R.id.mHandCard1);
		mHandCardsImageViews[1] = (ImageView) findViewById(R.id.mHandCard2);
		mHandCardsImageViews[2] = (ImageView) findViewById(R.id.mHandCard3);
		mHandCardsImageViews[3] = (ImageView) findViewById(R.id.mHandCard4);
		mHandCardsImageViews[4] = (ImageView) findViewById(R.id.mHandCard5);

		mTableCardsImageViews[0] = (ImageView) findViewById(R.id.mTableCard1);
		mTableCardsImageViews[1] = (ImageView) findViewById(R.id.mTableCard2);
		mTableCardsImageViews[2] = (ImageView) findViewById(R.id.mTableCard3);
		mTableCardsImageViews[3] = (ImageView) findViewById(R.id.mTableCard4);
		mTableCardsImageViews[4] = (ImageView) findViewById(R.id.mTableCard5);
		mTableCardsImageViews[5] = (ImageView) findViewById(R.id.mTableCard6);
		mTableCardsImageViews[6] = (ImageView) findViewById(R.id.mTableCard7);
		mTableCardsImageViews[7] = (ImageView) findViewById(R.id.mTableCard8);
		mTableCardsImageViews[8] = (ImageView) findViewById(R.id.mTableCard9);
		mTableCardsImageViews[9] = (ImageView) findViewById(R.id.mTableCard10);

		hHandCardsImageViews[0] = (ImageView) findViewById(R.id.hHandCard1);
		hHandCardsImageViews[1] = (ImageView) findViewById(R.id.hHandCard2);
		hHandCardsImageViews[2] = (ImageView) findViewById(R.id.hHandCard3);
		hHandCardsImageViews[3] = (ImageView) findViewById(R.id.hHandCard4);
		hHandCardsImageViews[4] = (ImageView) findViewById(R.id.hHandCard5);

		hTableCardsImageViews[0] = (ImageView) findViewById(R.id.hTableCard1);
		hTableCardsImageViews[1] = (ImageView) findViewById(R.id.hTableCard2);
		hTableCardsImageViews[2] = (ImageView) findViewById(R.id.hTableCard3);
		hTableCardsImageViews[3] = (ImageView) findViewById(R.id.hTableCard4);
		hTableCardsImageViews[4] = (ImageView) findViewById(R.id.hTableCard5);
		hTableCardsImageViews[5] = (ImageView) findViewById(R.id.hTableCard6);
		hTableCardsImageViews[6] = (ImageView) findViewById(R.id.hTableCard7);
		hTableCardsImageViews[7] = (ImageView) findViewById(R.id.hTableCard8);
		hTableCardsImageViews[8] = (ImageView) findViewById(R.id.hTableCard9);
		hTableCardsImageViews[9] = (ImageView) findViewById(R.id.hTableCard10);

		playedCard1 = (ImageView) findViewById(R.id.pCard1);
		playedCard2 = (ImageView) findViewById(R.id.pCard2);

		hukumImageView = (ImageView) findViewById(R.id.hukum);
		
		machineScore = (TextView) findViewById(R.id.machineScore);
		humanScore = (TextView) findViewById(R.id.humanScore);
		Log.i(LOG_TAG, "initialized ImageViews");
	}

	private void dealHandCards()
	{
		gameData = new GameData();		

		/* Deal Hand Cards */		
		for(int i=0; i<5; i++)
		{
			gameData.mHandCards[i] = gameData.getDeck().popTopCard();
			gameData.hHandCards[i] = gameData.getDeck().popTopCard();

			gameData.mHandCards[i].setIsVisible(true);
			gameData.hHandCards[i].setIsVisible(true);
			
			mHandCardsImageViews[i].setImageResource(gameData.mHandCards[i].getImageResourceId());
			hHandCardsImageViews[i].setImageResource(gameData.hHandCards[i].getImageResourceId());

			hHandCardsImageViews[i].setOnClickListener(this);
		}

		Log.i(LOG_TAG, "Hand cards dealt");
		
		/* Declare HUKUM */
		declareHukum();					
	}

	private void declareHukum()
	{
		hukumDialog = new Dialog(context);
		hukumDialog.setTitle(R.string.hukum_dialog_title);
		hukumDialog.setContentView(R.layout.hukum_dialog);

		suitesImageViews[0] = (ImageView) hukumDialog.findViewById(R.id.spade);
		suitesImageViews[1] = (ImageView) hukumDialog.findViewById(R.id.heart);
		suitesImageViews[2] = (ImageView) hukumDialog.findViewById(R.id.diamond);
		suitesImageViews[3] = (ImageView) hukumDialog.findViewById(R.id.club);

		for(int i=0; i<4; i++)
		{
			suitesImageViews[i].setOnClickListener(this);
		}
		hukumDialog.show();
		Log.i(LOG_TAG, "Hukum Dialog shown");
	}

	private void dealTableCards()
	{
		/* Deal Table cards */
		for(int i=0; i<10; i++)
		{
			gameData.mTableCards[i] = gameData.getDeck().popTopCard();
			gameData.hTableCards[i] = gameData.getDeck().popTopCard();

			/* set images and onClickListener for the open cards on the table for now */
			if(i>=5) 
			{
				gameData.mTableCards[i].setIsVisible(true);
				gameData.hTableCards[i].setIsVisible(true);

				mTableCardsImageViews[i].setImageResource(gameData.mTableCards[i].getImageResourceId());
				hTableCardsImageViews[i].setImageResource(gameData.hTableCards[i].getImageResourceId());
				hTableCardsImageViews[i].setOnClickListener(this);
			}
			else
			{
				gameData.mTableCards[i].setIsVisible(false);
				gameData.hTableCards[i].setIsVisible(false);
			}
		}		
		Log.i(LOG_TAG, "Table cards dealt");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int ID = v.getId();
		switch(ID)
		{

		/* Set HUKUM */
		case R.id.spade:
			hukumImageView.setImageResource(R.drawable.spade);
			gameData.setTrumpSuite('s');
			hukumDialog.dismiss();
			Log.i(LOG_TAG, "Trump suite=Spade");
			dealTableCards();
			break;			
		case R.id.heart:
			hukumImageView.setImageResource(R.drawable.heart);
			gameData.setTrumpSuite('h');
			hukumDialog.dismiss();
			Log.i(LOG_TAG, "Trump suite=Heart");
			dealTableCards();
			break;			
		case R.id.diamond:
			hukumImageView.setImageResource(R.drawable.diamond);
			gameData.setTrumpSuite('d');
			hukumDialog.dismiss();
			Log.i(LOG_TAG, "Trump suite=Diamond");
			dealTableCards();
			break;			
		case R.id.club:
			hukumImageView.setImageResource(R.drawable.club);
			gameData.setTrumpSuite('c');
			hukumDialog.dismiss();
			Log.i(LOG_TAG, "Trump suite=Club");
			dealTableCards();
			break;		


			/* Play Hand Card */
		case R.id.hHandCard1:
			playCard(gameData.hHandCards[0], hHandCardsImageViews[0], 0);
			break;
		case R.id.hHandCard2:
			playCard(gameData.hHandCards[1], hHandCardsImageViews[1], 1);
			break;
		case R.id.hHandCard3:
			playCard(gameData.hHandCards[2], hHandCardsImageViews[2], 2);
			break;
		case R.id.hHandCard4:
			playCard(gameData.hHandCards[3], hHandCardsImageViews[3], 3);
			break;
		case R.id.hHandCard5:
			playCard(gameData.hHandCards[4], hHandCardsImageViews[4], 4);
			break;

			/* Play Table Card */
		case R.id.hTableCard1:
			Log.i(LOG_TAG,"played card 0!");
			playCard(gameData.hTableCards[0], hTableCardsImageViews[0], 0);
			break;
		case R.id.hTableCard2:
			playCard(gameData.hTableCards[1], hTableCardsImageViews[1], 1);
			break;
		case R.id.hTableCard3:
			playCard(gameData.hTableCards[2], hTableCardsImageViews[2], 2);
			break;
		case R.id.hTableCard4:
			playCard(gameData.hTableCards[3], hTableCardsImageViews[3], 3);
			break;
		case R.id.hTableCard5:
			playCard(gameData.hTableCards[4], hTableCardsImageViews[4], 4);
			break;
		case R.id.hTableCard6:
			playCard(gameData.hTableCards[5], hTableCardsImageViews[5], 5);
			break;
		case R.id.hTableCard7:
			playCard(gameData.hTableCards[6], hTableCardsImageViews[6], 6);
			break;
		case R.id.hTableCard8:
			playCard(gameData.hTableCards[7], hTableCardsImageViews[7], 7);
			break;
		case R.id.hTableCard9:
			playCard(gameData.hTableCards[8], hTableCardsImageViews[8], 8);
			break;
		case R.id.hTableCard10:
			playCard(gameData.hTableCards[9], hTableCardsImageViews[9], 9);
			break;
		}		
	}

	private void playCard(Card card, ImageView cardImageView, int pos)
	{		
		Log.i(LOG_TAG, "Human trying to a play card at position: "+pos);
		
		if(gameData.machineTurn)
		{
			Toast.makeText(context, "Wait for your turn!", Toast.LENGTH_LONG).show();
			return;
		}		
		cardImageView.setOnClickListener(null);		
		cardImageView.setVisibility(View.INVISIBLE);
		card.setIsVisible(false);
		
		if(gameData.firstTurn)
		{
			gameData.playedCard1 = card;
			playedCard1.setImageResource(card.getImageResourceId());			
		}
		else
		{
			gameData.playedCard2 = card;
			playedCard2.setImageResource(card.getImageResourceId());			
		}	
		
		if(pos>=5)
		{
			/* Open up folded card */
			Log.i(LOG_TAG,"Opening human card at position: "+(pos-5));
			hTableCardsImageViews[pos-5].setImageResource(gameData.hTableCards[pos-5].getImageResourceId());
			gameData.hTableCards[pos-5].setIsVisible(true);
			hTableCardsImageViews[pos-5].setOnClickListener(this);
		}		
		gameData.machineTurn = true;
		
		if(gameData.firstTurn)
		{
			gameData.firstTurn = false;
			machinePlay();
		}
		else
		{			
			gameData.firstTurn = true;
			
			/* Same Suite cards */
			if(gameData.playedCard1.isSameSuite(gameData.playedCard2))
			{
				if(gameData.playedCard1.isGreater(gameData.playedCard2))
				{
					updateScore(true);
				}
				else
				{
					updateScore(false);
				}
			}
			/* Human played a different suite card */
			else
			{
				/* Check if trump suite played */
				updateScore(true);
			}			
		}
	}
	
	private void machinePlay()
	{	
		Card cardToPlay = null;
		ImageView cardImageView = null;
		int cardPos = 0;
		boolean machineHand = false;

		if(gameData.firstTurn)
		{
			Log.i(LOG_TAG, "Machine playing first");			
			for(int i=0; i<5; i++)
			{
				if(gameData.mHandCards[i].getIsVisible())
				{
					cardToPlay = gameData.mHandCards[i];
					cardImageView = mHandCardsImageViews[i];
					cardPos = i;
					break;
				}					
			}
			if(cardToPlay == null)
			{
				for(int i=0; i<10; i++)
				{
					if(gameData.mTableCards[i].getIsVisible())
					{
						cardToPlay = gameData.mTableCards[i];
						cardImageView = mTableCardsImageViews[i];
						cardPos = i;
						break;
					}					
				}					
			}			
		}
		/* If not first turn, play according to the card played */
		else
		{			
			Log.i(LOG_TAG, "Machine playing second");
			List<Card> sameSuiteCards = new ArrayList<Card>();
			Card highestCard = null, lowestCard = null;
			boolean ishighestHand = false, isLowestHand = false;
			int highestPos = 0, lowestPos = 0;
			
			/* Check if machine has a same suite card */
			/* Check hand cards */
			for(int i=0; i<5; i++)
			{
				if(gameData.mHandCards[i].getIsVisible() && gameData.mHandCards[i].isSameSuite(gameData.playedCard1))
				{
					/* Keep track of the highest and lowest card */
					if(highestCard == null)
					{
						highestCard = gameData.mHandCards[i];
						lowestCard = gameData.mHandCards[i];
						ishighestHand = true; isLowestHand = true;
						highestPos = i; lowestPos = i;
					}
					else
					{
						if(gameData.mHandCards[i].isGreater(highestCard))
						{
							highestCard = gameData.mHandCards[i];
							highestPos = i;
						}
						if(lowestCard.isGreater(gameData.mHandCards[i]))
						{
							lowestCard = gameData.mHandCards[i];
							lowestPos = i;
						}
					}
					sameSuiteCards.add(gameData.mHandCards[i]);
				}
			}
			/* Check table cards */
			for(int i=0; i<10; i++)
			{
				if(gameData.mTableCards[i].getIsVisible() && gameData.mTableCards[i].isSameSuite(gameData.playedCard1))
				{
					/* Keep track of the highest and lowest card */
					if(highestCard == null)
					{
						highestCard = gameData.mTableCards[i];
						lowestCard = gameData.mTableCards[i];
						ishighestHand = false; isLowestHand = false;
						highestPos = i; lowestPos = i;
					}
					else
					{
						if(gameData.mTableCards[i].isGreater(highestCard))
						{
							highestCard = gameData.mTableCards[i];
							ishighestHand = false;
							highestPos = i;
						}
						if(lowestCard.isGreater(gameData.mTableCards[i]))
						{
							lowestCard = gameData.mTableCards[i];
							isLowestHand = false;
							lowestPos = i;
						}
					}
					sameSuiteCards.add(gameData.mTableCards[i]);
				}
			}
			
			/* Same suite card present */
			if(sameSuiteCards.size() > 0)
			{				
				/* Machine wins this hand: play highest */
				if(highestCard.isGreater(gameData.playedCard1))
				{
					/* Can optimize here to play lowest amongst the high */
					cardToPlay = highestCard;
					cardPos = highestPos;
					if(ishighestHand)
					{
						cardImageView = mHandCardsImageViews[highestPos];
					}
					else
					{
						cardImageView = mTableCardsImageViews[highestPos];
					}				
					machineHand = true;
				}
				/* Machine loses this hand: play lowest */
				else
				{
					cardToPlay = lowestCard;
					cardPos = lowestPos;
					if(isLowestHand)
					{
						cardImageView = mHandCardsImageViews[lowestPos];
					}
					else
					{
						cardImageView = mTableCardsImageViews[lowestPos];
					}
				}				
			}
			/* Try and kill */
			else
			{
				for(int i=0; i<5; i++)
				{
					if(gameData.mHandCards[i].getIsVisible())
					{
						cardToPlay = gameData.mHandCards[i];
						cardImageView = mHandCardsImageViews[i];
						cardPos = i;
						break;
					}					
				}
				if(cardToPlay == null)
				{
					for(int i=0; i<10; i++)
					{
						if(gameData.mTableCards[i].getIsVisible())
						{
							cardToPlay = gameData.mTableCards[i];
							cardImageView = mTableCardsImageViews[i];
							cardPos = i;
							break;
						}					
					}					
				}
			}
		}
		Log.i(LOG_TAG, "Machine playing card at position: "+ cardPos);
		if(gameData.firstTurn)
		{
			playedCard1.setImageResource(cardToPlay.getImageResourceId());			
		}
		else
		{
			playedCard2.setImageResource(cardToPlay.getImageResourceId());
		}
		cardImageView.setOnClickListener(null);		
		cardImageView.setVisibility(View.INVISIBLE);
		cardToPlay.setIsVisible(false);
		
		if(cardPos>=5)
		{
			/* Open up folded card */
			Log.i(LOG_TAG,"Opening machine card at position: "+(cardPos-5));
			mTableCardsImageViews[cardPos-5].setImageResource(gameData.mTableCards[cardPos-5].getImageResourceId());
			gameData.mTableCards[cardPos-5].setIsVisible(true);
			mTableCardsImageViews[cardPos-5].setOnClickListener(this);
		}				
		gameData.machineTurn = false;
		
		if(gameData.firstTurn)
		{
			gameData.playedCard1 = cardToPlay;
			gameData.firstTurn = false;			
		}
		else
		{
			gameData.playedCard2 = cardToPlay;
			gameData.firstTurn = true;
			updateScore(machineHand);
		}
	}
	
	private void updateScore(boolean machineHand)
	{
		CharSequence score;
		int scoreValue;
		
		if(machineHand)
		{
			gameData.machineTurn = true;
			score = machineScore.getText();
			scoreValue = Integer.parseInt(score.toString());
			scoreValue++;
			machineScore.setText(Integer.toString(scoreValue));			
			Toast.makeText(context, "Machine won this hand!", Toast.LENGTH_LONG).show();
			machinePlay();
		}
		else
		{
			gameData.machineTurn = false;
			score = humanScore.getText();
			scoreValue = Integer.parseInt(score.toString());
			scoreValue++;
			humanScore.setText(Integer.toString(scoreValue));
			Toast.makeText(context, "You won this hand!", Toast.LENGTH_LONG).show();
		}
		
/*		playedCard1.setVisibility(View.INVISIBLE);
		playedCard2.setVisibility(View.INVISIBLE);
*/	}
}
