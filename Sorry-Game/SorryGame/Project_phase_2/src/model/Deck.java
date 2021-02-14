package model;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.ImageIcon;

import model.GameBoard.Squares.EndSlideSquare;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.InternalSlideSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.SimpleSquare;
import model.GameBoard.Squares.SlideSquare;
import model.GameBoard.Squares.Square;
import model.GameBoard.Squares.StartSlideSquare;
import model.GameBoard.Squares.StartSquare;
import model.cards.Card;
import model.cards.NumberCard;
import model.cards.NumberElevenCard;
import model.cards.NumberFourCard;
import model.cards.NumberOneCard;
import model.cards.NumberSevenCard;
import model.cards.NumberTenCard;
import model.cards.NumberTwoCard;
import model.cards.SimpleNumberCard;
import model.cards.SorryCard;

/**
 * 
 * @author George Kokolakis
 *
 */
public class Deck
{
	private ArrayList<Square> Squares = new ArrayList<Square>();// array to store the squares
	private ArrayList<Card> gameCards = new ArrayList<Card>();// array to store the cards
	private int index=0;
	private Pawn red_Pawn1; 
	private Pawn red_Pawn2 ;
	private Pawn yellow_Pawn1 ;
	private Pawn yellow_Pawn2 ;
	
	/**
	 * Accessor
	 * @return the first red pawn
	 */
	public Pawn getRed_Pawn1()
	{
		return red_Pawn1;
	}
	/**
	 * Accessor
	 * @return the second red pawn
	 */
	
	public Pawn getRed_Pawn2()
	{
		return red_Pawn2;
	}

	/**
	 * Accessor
	 * @return the first yellow pawn
	 */
	
	public Pawn getYellow_Pawn1()
	{
		return yellow_Pawn1;
	}
	/**
	 * Accessor
	 * @return the second  yellow pawn
	 */
	
	public Pawn getYellow_Pawn2()
	{
		return yellow_Pawn2;
	}

	/**
	 * 
	 * @return the array of squares
	 */
	public ArrayList<Square> getSquares()
	{
		return Squares;
	}

	/**
	 * 
	 * @return the array of cards
	 */
	public ArrayList<Card> getGameCards()
	{
		return gameCards;
	}

	/**
	 * Constructor Initializing the table
	 */
	public Deck()
	{
		init_Table();
	}

	
	/**
	 * Accessor This method is used for allowing the player do draw a new card
	 */
	public Card getNewCard()
	{
		if (!CardsEmpty())
		{

			Card current_Card = gameCards.get(index);
			index++;
			System.out.println("has been played "+ index + " cards");
			return current_Card;
		} else
		{
			System.out.println("shuffle triggerd");
			ShuffleDeck(gameCards);
			index = 0;
			Card current_Card=gameCards.get(index);
			index++;
			return current_Card;
		}

	}

	/**
	 * Observer This method is used to check if there are cards left
	 * 
	 * @return if there are cards left
	 */
	public boolean CardsEmpty()
	{
		if(index>=44)
		{
			
			return true;
		}
		return false;
	}

	/**
	 * Observer This method is used for checking if the player can fold
	 * 
	 * @return if the player can fold
	 */
	public boolean canFold()
	{
		return false;

	}

	/**
	 * This method initialize the Table
	 */
	public void init_Table()
	{
		init_Cards();
	}
	public Card getCurrentCard()
	{
		
		return gameCards.get(index-1);
	}
	/**
	 * This method initialize the array of cards
	 */
	public void init_Cards()
	{

		for (int i = 0; i < 4; i++)
		{
			// Image image = new
			// ImageIcon(mainPanel.getClass().getResource("/images/cards.png")).getImage();
			gameCards.add(new NumberOneCard("/images/cards/card1.png", "NumberOneCard", 1));
			gameCards.add(new NumberTwoCard("/images/cards/card2.png", "NumberTwoCard", 2));
			gameCards.add(new SimpleNumberCard("/images/cards/card3.png", "NumberThreeCard", 3));
			gameCards.add(new NumberFourCard("/images/cards/card4.png", "NumberFourCard", 4));
			gameCards.add(new SimpleNumberCard("/images/cards/card5.png", "NumberFiveCard", 5));
			gameCards.add(new NumberSevenCard("/images/cards/card7.png", "NumberSevenCard", 7));
			gameCards.add(new SimpleNumberCard("/images/cards/card8.png", "NumberEightCard", 8));
			gameCards.add(new NumberTenCard("/images/cards/card10.png", "NumberTenCard", 10));
			gameCards.add(new NumberElevenCard("/images/cards/card11.png", "NumberElevenCard", 11));
			gameCards.add(new SimpleNumberCard("/images/cards/card12.png", "NumberTwelveCard", 12));
			gameCards.add(new SorryCard("/images/cards/cardSorry.png", "SorryCard"));
		}

		gameCards = ShuffleDeck(gameCards);
	}

	/**
	 * This method Shuffle the array with the cards
	 * 
	 * @param gameCards The arraylist from cards
	 * @return The arraylist shuffled
	 */
	public ArrayList<Card> ShuffleDeck(ArrayList<Card> gameCards)
	{
		Collections.shuffle(gameCards);
		return gameCards;
	}

	/**
	 * This method initialize the 2 players
	 */
	public void init_Players()
	{

	}

	/**
	 * This method initialize the squares of the board
	 */
	public void init_Squares()
	{
		for (int i = 1; i < 73; i++)
		{
			// red
			if (i == 2 || i == 16)
			{
				Squares.add(new StartSlideSquare(Color.red, null));
				continue;
			}
			if (i == 3 || i == 4 || i == 17 || i == 18 || i == 19)
			{
				Squares.add(new InternalSlideSquare(Color.red, null));
				continue;
			}
			if (i == 11 || i == 20)
			{
				Squares.add(new EndSlideSquare(Color.red, null));
				continue;
			}
			if (i == 5 || i == 6 || i == 7 || i == 8 || i == 9)
			{
				Squares.add(new SafetyZoneSquare(Color.red, null));
				continue;
			}
			if (i == 10)
			{
				Squares.add(new HomeSquare(Color.red, null));
				continue;
			}

			// blue

			if (i == 23 || i == 31)
			{
				Squares.add(new StartSlideSquare(Color.blue, null));
				continue;
			}
			if (i == 24 || i == 25 || i == 32 || i == 33 || i == 34)
			{
				Squares.add(new InternalSlideSquare(Color.blue, null));
				continue;
			}
			if (i == 26 || i == 35)
			{
				Squares.add(new EndSlideSquare(Color.blue, null));
				continue;
			}

			// yellow
			if (i == 38 || i == 52)
			{
				Squares.add(new StartSlideSquare(Color.yellow, null));
				continue;
			}
			if (i == 39 || i == 46 || i == 53 || i == 54 || i == 55)
			{
				Squares.add(new InternalSlideSquare(Color.yellow, null));
				continue;
			}
			if (i == 47 || i == 56)
			{
				Squares.add(new EndSlideSquare(Color.yellow, null));
				continue;
			}
			if (i == 40 || i == 41 || i == 42 || i == 43 || i == 44)
			{
				Squares.add(new SafetyZoneSquare(Color.yellow, null));
				continue;
			}
			if (i == 45)
			{
				Squares.add(new HomeSquare(Color.yellow, null));
				continue;
			}

			// green

			if (i == 59 || i == 67)
			{
				Squares.add(new StartSlideSquare(Color.green, null));
				continue;
			}
			if (i == 60 || i == 61 || i == 68 || i == 69 || i == 70)
			{
				Squares.add(new InternalSlideSquare(Color.green, null));
				continue;
			}
			if (i == 62 || i == 71)
			{
				Squares.add(new EndSlideSquare(Color.green, null));
				continue;
			}

			// white simple squares

			Squares.add(new SimpleSquare(Color.white, null));

		}

	}

	/**
	 * This method initialize the pawns of each player
	 */
	public void init_Pawns()
	{
		 red_Pawn1 = new Pawn(1);
		 red_Pawn2 = new Pawn(2);
		 yellow_Pawn1 = new Pawn(1);
		 yellow_Pawn2 = new Pawn(2);

		red_Pawn1.setColor(Color.red);
		red_Pawn1.setCurrentSquare(new StartSquare(Color.red, null));
		red_Pawn1.setPosition(-10);

		red_Pawn2.setColor(Color.red);
		red_Pawn2.setCurrentSquare(new StartSquare(Color.red, null));
		red_Pawn2.setPosition(-10);

		yellow_Pawn1.setColor(Color.yellow);
		yellow_Pawn1.setCurrentSquare(new StartSquare(Color.yellow, null));
		yellow_Pawn1.setPosition(-10);

		yellow_Pawn2.setColor(Color.yellow);
		yellow_Pawn2.setCurrentSquare(new StartSquare(Color.yellow, null));
		yellow_Pawn2.setPosition(-10);

		

	}

}