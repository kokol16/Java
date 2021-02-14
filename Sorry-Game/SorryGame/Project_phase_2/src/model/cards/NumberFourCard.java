package model.cards;

import java.util.ArrayList;

import model.Deck;
import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.StartSquare;

public class NumberFourCard extends NumberCard
{
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	
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
	public NumberFourCard(String image , String name , int value )
	{
		super(image, name,value);
		choices = new String[1];
		choices[0]="1.move pawn by four Backwards";
		
	}
	
	/*
	 * This method moves the pawn position on backwards by 4
	 * @param pawn The current pawn of the player
	 * @param table the table of the game
	 */
	@Override
	public void movePawn(Pawn pawn, Deck deck,int choice)
	{

		//check if the move is valid 
		int possibleMove=pawn.getPosition()-4;
		possibleMove=Math.floorMod(possibleMove, 72);
		if ( !check.isPawnOnStartPosition(pawn)&&
				!check.IsEnemyHome(pawn, possibleMove, deck) && !check.IsEnemySafetyZone(pawn, possibleMove, deck) 
		&& !check.IsEnemyStartSquare(pawn, possibleMove, deck) && !check.IsTwoSamePawnsOnSquare(pawn, possibleMove, deck) )
		{
			if(deck.getSquares().get(possibleMove) instanceof HomeSquare
				 ||deck.getSquares().get(possibleMove) instanceof SafetyZoneSquare
				 && ! (pawn.getCurrentSquare() instanceof HomeSquare )
				 && ! (pawn.getCurrentSquare() instanceof SafetyZoneSquare ))
			{
				possibleMove-=6;
			}
			if(check.IsEnemyPawnOnSquare(pawn, possibleMove, deck))
			{
				deck.getSquares().get(possibleMove).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
				deck.getSquares().get(possibleMove).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(possibleMove).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
				
			
			}
			pawn.getCurrentSquare().setCurr_pawn(null);
			update_Pawn_Current_Square(pawn, possibleMove, deck);
			set_Squares_Pawn(pawn, possibleMove, deck);
			pawn.setPosition(possibleMove);
			
			
		}
		else {
			//fold
		}
		
	
	
	}
	
	@Override
	public String toString()
	{
			return "Number Four Card";
	}
}
