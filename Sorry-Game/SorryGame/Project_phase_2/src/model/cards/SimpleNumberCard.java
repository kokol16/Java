package model.cards;

import java.util.ArrayList;

import model.Deck;
import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.StartSquare;

public class SimpleNumberCard extends NumberCard
{
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	public String[] getChoices()
	{
		return choices;
	}
	String Name;
	/**
	 * Constructor 
	 * @param image Path of the image
	 * @param name Name of card
	 * @param value  of card
	 */
	public SimpleNumberCard(String image ,String name ,  int value)
	{
		super(image, name, value);
		Name=name;
		choices = new String[1];
		choices[0]="1. move pawn by "+getValue();
		
	}
	
	@Override
	public boolean move_By_Blocks(Pawn pawn, Deck deck,int numberOfBlocks)
	{
		int possibleMove=pawn.getPosition()+numberOfBlocks;
		possibleMove=Math.floorMod(possibleMove, 72);
		if ( !check.isPawnOnStartPosition(pawn)&&
				!check.IsEnemyHome(pawn, possibleMove, deck) && !check.IsEnemySafetyZone(pawn, possibleMove, deck) 
		&& !check.IsEnemyStartSquare(pawn, possibleMove, deck) && !check.IsTwoSamePawnsOnSquare(pawn, possibleMove, deck) )
		{
			if(check.IsEnemyPawnOnSquare(pawn, possibleMove, deck))
			{
				deck.getSquares().get(possibleMove).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
				deck.getSquares().get(possibleMove).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(possibleMove).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
				
			}
			pawn.getCurrentSquare().setCurr_pawn(null);
			deck.getSquares().get(pawn.getPosition()).setCurr_pawn(null);
			update_Pawn_Current_Square(pawn, possibleMove, deck);
			
			set_Squares_Pawn(pawn, possibleMove, deck);
			pawn.setPosition(possibleMove);
			return true;
			
		}
		return false;
	}
	@Override
	public void movePawn(Pawn pawn, Deck deck,int numberOfBlocks)
	{
		//check if the current pawn can be moved number of blocks front
		
		//move it , changing his position
		if(move_By_Blocks(pawn, deck, numberOfBlocks))
		{
			
		}
		else {
			//fold
		}
	}
	
	@Override
	public String toString()
	{
		return this.Name;
	}


	
}
