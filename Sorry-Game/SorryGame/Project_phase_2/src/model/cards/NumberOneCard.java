package model.cards;

import java.awt.Color;
import java.util.ArrayList;

import model.Deck;
import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.StartSquare;

public class NumberOneCard extends NumberCard
{
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	
	public String[] getChoices()
	{
		return choices;
	}
	/**
	 * Constructor
	 * 
	 * @param image Path of the image
	 * @param name  Name of card
	 * @param value of card
	 */
	public NumberOneCard(String image, String name, int value)
	{

		super(image, name, value);
		choices=new String[2];
		choices[0]="1. Begin from start position";
		choices[1]="2. move pawn by one ";
		
	}

	/**
	 * This method moves the pawn from the start square 
	 * @param pawn
	 * @return if the move can is valid
	 */
	public boolean beginFromStart(Pawn pawn, Deck deck)
	{

		// check if the current pawn is at the home square
		if (pawn.getPosition()==-10  )//instanceof StartSquare
		{
			if (pawn.getColor() == Color.red && !check.IsTwoSamePawnsOnSquare(pawn, 11, deck))
			{
				if(check.IsEnemyPawnOnSquare(pawn, 11, deck))
				{
					deck.getSquares().get(11).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
					deck.getSquares().get(11).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(11).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
					
					
				}
				pawn.setPosition(11);
				pawn.getCurrentSquare().setCurr_pawn(null);
				update_Pawn_Current_Square(pawn, 11, deck);
				set_Squares_Pawn(pawn, 11, deck);
				
			} 
			else if(pawn.getColor() == Color.yellow && !check.IsTwoSamePawnsOnSquare(pawn, 46, deck))
			{
				if(check.IsEnemyPawnOnSquare(pawn, 46, deck))
				{
					deck.getSquares().get(46).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
					deck.getSquares().get(46).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(46).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
					
					
				}
				pawn.getCurrentSquare().setCurr_pawn(null);
				pawn.setPosition(46);
				update_Pawn_Current_Square(pawn, 46, deck);
				set_Squares_Pawn(pawn, 46, deck);
				
			}
			// set pawn's position
			// update the table
			return true;
		}
		else {
			return false;
		}

	}
	
	/**
	 * This method moves the pawn by one square ,it checks if the move is valid and set the new position for the current pawn
	 * @param pawn
	 * @param deck
	 * @return true if the move is valid
	 */
	public boolean   moveByOne(Pawn pawn,Deck deck)
	{
		int possibleMove=pawn.getPosition()+1;
		possibleMove=Math.floorMod(possibleMove, 72);
		if ( !check.isPawnOnStartPosition(pawn)&&
				!check.IsEnemyHome(pawn, possibleMove, deck) && !check.IsEnemySafetyZone(pawn, possibleMove, deck) 
		&& !check.IsEnemyStartSquare(pawn, possibleMove, deck) && !check.IsTwoSamePawnsOnSquare(pawn, possibleMove, deck) )
		{
			if(check.IsEnemyPawnOnSquare(pawn, possibleMove, deck))
			{
				deck.getSquares().get(possibleMove).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
				deck.getSquares().get(possibleMove).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(possibleMove).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
			//	deck.getSquares().get(possibleMove).setCurr_pawn(pawn);
			
			}
			
			///pawn.getCurrentSquare().setCurr_pawn(null);
			update_Pawn_Current_Square(pawn, possibleMove, deck);
			
			set_Squares_Pawn(pawn, possibleMove, deck);
			pawn.setPosition(possibleMove);
			return true;
			
		}
		return false;
	}

	/**
	 * This method moves the pawn by one front or allow the player to start the pawn from home square
	 * @param pawn  the current pawn of the player
	 * @param table the table of the game 
	 * @param choice Take an int as which choice the user made  1 first choice, 2 second choice etc...
	 */
	@Override
	public void movePawn(Pawn pawn, Deck  deck,int choice)
	{
		boolean move_Result=false;
		switch (choice)
		{
		case 1:
			move_Result=beginFromStart(pawn,deck);
			break;
		case 2:
			move_Result=moveByOne(pawn, deck);
			break;
		default:
			break;
		}
		//check if the move that the user chose is valid or make the other move if it's possible
		if(choice==1 && move_Result==false)
		{
			if(moveByOne(pawn, deck))
			{
				
			}
			else {
				//fold to continue
			}
		}
		else if (choice==2 && move_Result==false)
		{
			if(beginFromStart(pawn,deck))
			{
				
			}
			else {
				//fold to continue
			}
		}
		
		
		
	}

	@Override
	public String toString()
	{
		return "Number One Card";

	}
}
