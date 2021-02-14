package model.cards;

import java.util.ArrayList;

import model.Deck;
import model.Pawn;
/**
 * 
 * @author George Kokolakis
 *
 */
public class NumberCard extends Card
{
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	private int value;//card value
	
	/**
	 * Accessor
	 * @return the array of the choices for the current card
	 */
	public String[] getChoices()
	{
		return choices;
	}
	
	/**
	 * Constructor 
	 * @param image Path of the image
	 * @param name Name of card
	 * @param value  of card
	 */
	public NumberCard(String image, String name,int value)
	{
		super(image, name);
		setValue(value);
		
	}
	/**
	 * Transformer
	 * @param value The value of the card
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	
	/**
	 * @pre move must be valid
	 * @param pawn that will be moved
	 * @param deck of the game
	 * @param numberOfBlocks How many squares will the pawn move
	 * @return if the move was succesful
	 * @post moves the pawn
	 */
	public boolean move_By_Blocks(Pawn pawn, Deck deck,int numberOfBlocks)
	{
		return has_been_played;
		
	}
	

	@Override
	public String toString()
	{
		return "Number Card\n" ;
	}

	/**
	 * Accessor
	 * @return the value of the card
	 */
	public int getValue()
	{
		return value;
	}

	@Override
	public void movePawn(Pawn pawn, Deck deck,int choice)
	{
		
	}
	

	

}
