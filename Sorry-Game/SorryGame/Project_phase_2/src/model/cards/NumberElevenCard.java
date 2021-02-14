package model.cards;

import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.Square;
import model.GameBoard.Squares.StartSquare;

import java.awt.Color;
import java.util.ArrayList;

import model.Deck;

public class NumberElevenCard extends NumberCard
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
	public NumberElevenCard(String image ,String name ,int value)
	{
		super(image, name,value );
		choices=new String[3];
		choices[0]="1. swap your pawn with number 1 enemy pawn";
		choices[1]="2. move pawn by eleven ";
		choices[2]="3. swap your pawn with number 2 enemy pawn";
	
	
	}
	

	/**
	 * This method moves the pawn by one square ,it checks if the move is valid and set the new position for the current pawn
	 * @param pawn
	 * @param deck
	 * @return true if the move is valid
	 */
	public boolean   moveByEleven(Pawn pawn,Deck deck)
	{
		int possibleMove=pawn.getPosition()+11;
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
			update_Pawn_Current_Square(pawn, possibleMove, deck);
			
			set_Squares_Pawn(pawn, possibleMove, deck);
			pawn.setPosition(possibleMove);
			return true;
			
		}
		return false;
	}
	
	/**
	 * @pre swap must be valid
	 * @param pawn that will be swapped
	 * @param EnemyPawn that will be swapped
	 * @param deck
	 * @return if the swap was completed successfully
	 * @post swap the two pawns
	 */
	public boolean swap_Pawns(Pawn pawn,Pawn EnemyPawn,Deck deck)
	{
		
		if( !check.isPawnOnStartPosition(pawn)&&
			!( EnemyPawn.getCurrentSquare() instanceof SafetyZoneSquare ) &&
			
				
			!( EnemyPawn.getCurrentSquare() instanceof StartSquare ) &&	
			!( EnemyPawn.getCurrentSquare() instanceof HomeSquare ) &&
			!(pawn.getCurrentSquare() instanceof StartSquare ) &&
			!(pawn.getCurrentSquare() instanceof HomeSquare ) 		)
																			
		{
			System.out.println("swap done");
			int pawn_Position=pawn.getPosition();
			Square pawn_Square=pawn.getCurrentSquare();
			int enenemypawn_Position=EnemyPawn.getPosition();
			Square enemypawn_Square=EnemyPawn.getCurrentSquare();
			
			//swap squares and positions
			
			pawn.setPosition(enenemypawn_Position);
			pawn.setCurrentSquare(enemypawn_Square);
			enemypawn_Square.setCurr_pawn(pawn);
			
		
			EnemyPawn.setPosition(pawn_Position);
			EnemyPawn.setCurrentSquare(pawn_Square);
			
			pawn_Square.setCurr_pawn(EnemyPawn);
			return true;
			
		}
		return false;
	}

	@Override
	public void movePawn(Pawn pawn,Pawn EnemyPawn,Deck deck,int choice)
	{
		boolean move_Result=false;
		
		switch (choice)
		{
		case 1:
			if(pawn.getColor()==Color.red)
			{
				move_Result=swap_Pawns(pawn, deck.getYellow_Pawn1(), deck);
				
			}
			else 
			{
				move_Result=swap_Pawns(pawn, deck.getRed_Pawn1(), deck);

			}
			break;
		case 2:
			move_Result=moveByEleven(pawn, deck);
			break;
		default:
			if(pawn.getColor()==Color.red)
			{
				move_Result=swap_Pawns(pawn, deck.getYellow_Pawn2(), deck);
				
			}
			else 
			{
				move_Result=swap_Pawns(pawn, deck.getRed_Pawn2(), deck);

			}
			break;
		}
		//check if the move that the user chose is valid or make the other move if it's possible
 
		if(choice==1 && move_Result==false)
		{
			if(moveByEleven(pawn, deck))
			{
				
			}
			else {
				//fold button to continue
			}
		}
		else if( (choice==2 || choice==3) && move_Result==false)
		{
			  
			
			//let user to chooce if he wants fold or swap
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	public String toString()
	{
			return "Number Eleven card";
	}
	
	
}
