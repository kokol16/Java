package model.cards;

import java.util.ArrayList;

import model.Deck;
import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.StartSquare;

public class NumberTenCard extends NumberCard
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
	public NumberTenCard(String image ,String name,int value )
	{
			super(image, name, value);
			choices=new String[2];
			choices[0]="1. move pawn by 1 backwards";
			choices[1]="2. move pawn by ten ";
	}
	
	/**
	 * This method moves the pawn by one square ,it checks if the move is valid and set the new position for the current pawn
	 * @param pawn
	 * @param deck
	 * @return true if the move is valid
	 */
	public boolean   moveByTen(Pawn pawn,Deck deck)
	{
		int possibleMove=pawn.getPosition()+10;
		possibleMove=Math.floorMod(possibleMove, 72);
		if (!check.isPawnOnStartPosition(pawn)&&
				!check.IsEnemyHome(pawn, possibleMove, deck) && !check.IsEnemySafetyZone(pawn, possibleMove, deck) 
		&& !check.IsEnemyStartSquare(pawn, possibleMove, deck) && !check.IsTwoSamePawnsOnSquare(pawn, possibleMove, deck) )
		{
			if(check.IsEnemyPawnOnSquare(pawn, possibleMove, deck))
			{
				deck.getSquares().get(possibleMove).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
				deck.getSquares().get(possibleMove).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(possibleMove).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
				
			}
			
			pawn.getCurrentSquare().setCurr_pawn(null);
			update_Pawn_Current_Square(pawn, possibleMove, deck);
			
			set_Squares_Pawn(pawn, possibleMove, deck);
			pawn.setPosition(possibleMove);
			return true;
			
		}
		return false;
	}
	
	
	/**
	 * This method moves the pawn by 1 backwards
	 * @return if the move is valid
	 */
	public boolean moveBackwards(Pawn pawn,Deck deck)
	{
		int possibleMove=pawn.getPosition()-1;
		possibleMove=Math.floorMod(possibleMove, 72);
		if (!check.isPawnOnStartPosition(pawn)  
			&&!check.IsEnemyHome(pawn, possibleMove, deck) 
			&& !check.IsEnemySafetyZone(pawn, possibleMove, deck) 
			&& !check.IsEnemyStartSquare(pawn, possibleMove, deck)
			&& !check.IsTwoSamePawnsOnSquare(pawn, possibleMove, deck) )
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
			return true;
			
		}
		return false;
	}
	/**
	 * This method moves the position of pawn 10 blocks front or 1 block backwards
	 */
	@Override
	public void movePawn(Pawn pawn, Deck deck,int choice)
	{
		//check if the pawn can move numOfBlocks
		//set pawn position to numOfBlocks
		boolean move_Result=false;
		
		
		switch (choice)
		{
		case 1:
			move_Result=moveBackwards(pawn, deck);
			break;
		case 2:
			move_Result=moveByTen(pawn, deck);
			break;
		default:
			break;
		}
		//check if the move that the user chose is valid or make the other move if it's possible
 
		if(choice==1 && move_Result==false)
		{
			if(moveByTen(pawn, deck))
			{
				
			}
			else {
				//fold button to continue
			}
		}
		else if(choice==2 && move_Result==false)
		{
			if(moveBackwards(pawn, deck))
			{
				
			}else {
				//fold button to continue
			}
		}
		
	}

	

	@Override
	public String toString()
	{

		return "Number Ten Card";
	}

}
