package model.cards;

import model.Pawn;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import model.Deck;
import model.MovesChecker;

/**
 * 
 * @author George Kokolakis
 *
 */
public abstract class Card
{
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	/**
	 * 
	 * @return the choices that user can make for moving his pawn
	 */
	public String[] getChoices()
	{
		return choices;
	}
	Boolean has_been_played;//This variable check's if the current card has been played
	private String image;//Path of the image
	private String name;//Name of the card
	protected MovesChecker check= new MovesChecker();
	
	/**
	 * Constructor
	 * This constructor Saves the path of the image and the name of the card
	 * @param image The path of the image for the current card
	 * @param name The name of the current card
	 */
	public Card(String  image , String name)
	{
		setName(name);
		setImage(image);
		has_been_played=false;
	}
	
	/**
	 * @pre The move must be valid
	 * This method moves the pawn
	 * @param pawn that the user wants to move
	 * @param deck of the game
	 * @param choice the choice that the user made to which move will be executed
	 * @param numOfBlocks The blocks that the pawn will move
	 * @post Moves the pawn
	 */
	public void movePawn(Pawn pawn, Deck  deck,int choice,int numOfBlocks)
	{
		
	}
	
	/**
	 *  @pre The move must be valid
	 * This method moves the pawn
	 * @param pawn pawn that the user wants to move
	 * @param EnemyPawn that the user selected for some special move of the card
	 * @param deck  of the game
	 * @param choice the choice that the user made to which move will be executed
	 * @post Moves the pawn
	 */
	public void movePawn(Pawn pawn,Pawn EnemyPawn,Deck deck,int choice)
	{
		
	}
	
	/**
	 *  @pre The move must be valid
	 * @param pawn that the user wants to move
	 * @param pawn2 that the user wants to move
	 * @param deck deck  of the game
	 * @param choice choice the choice that the user made to which move will be executed
	 * @param numOfBlocks that the first pawn will be moved
	 * @param numOfBlocks2 that the second pawn will be move
	 * @post Moves the pawn
	 */
	public void movePawn(Pawn pawn,Pawn pawn2, Deck  deck,int choice,int numOfBlocks,int numOfBlocks2)
	{
		
	}
	
	/**
	 * accessor
	 * @return The name of the card
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * transformer
	 * @param name The name of card
	 */
	public void setName(String name)
	{
		this.name = name;
	}


	/**
	 * Observer
	 * @return If the current card has been played
	 */
	public Boolean has_been_played()
	{
		return has_been_played;
	}


	/**
	 * Transformer
	 * @param has_been_played  Takes if the card has been played or not
	 */
	public void sethas_been_played(Boolean has_been_played)
	{
		this.has_been_played = has_been_played;
	}

	/**
	 * This method updates the squares current pawn after the move was made
	 * @param pawn
	 * @param square_index
	 * @param deck
	 */
	public void update_Pawn_Current_Square(Pawn pawn , int square_Index , Deck deck )
	{
		pawn.setCurrentSquare(deck.getSquares().get(square_Index));
	}

	/**
	 * This method updates the pawn current square after it moves
	 * @param pawn
	 * @param square_Index
	 * @param deck
	 */
	public void set_Squares_Pawn(Pawn pawn , int square_Index , Deck deck)
	{
		deck.getSquares().get(pawn.getPosition()).setCurr_pawn(null);//previous square of pawn
		
		deck.getSquares().get(square_Index).setCurr_pawn(pawn);
		
	//	deck.getSquares().get(square_Index).setHasPawn(true);
	
		
	}
	
	/**
	 * This method moves the pawn depending on the card
	 * @pre The move that we want to make must to be valid 
	 * @param pawn The current pawn that we want to move
	 * @param Deck The table to update
	 * @post Moves the pawn in the board and updates the display
	 */
	public abstract void movePawn(Pawn pawn,Deck Deck,int choice);
	
	/**
	 * This method returns the representation  of the card as a string 
	 * @return The card string 
	 */
	public abstract String toString();
	
	/**
	 * Accessor
	 * @return the path of the  image of the current card
	 */
	public String getImage()
	{
		return image;
	}
	/**
	 * Transformer
	 * This method set's the  image for the current ard
	 * @param image The path of the image
	 */
	public void setImage(String image)
	{
		this.image = image;
	}
	
	
}
