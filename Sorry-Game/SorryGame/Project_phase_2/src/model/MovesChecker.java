package model;

import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.StartSquare;

public class MovesChecker
{

	/**
	 * Observer This method checks if the move that the user chose , will lead to
	 * move the pawn to Enemy start square
	 * 
	 * @param pawn the pawn that we want to move
	 * @param pos  the position that the pawn will move
	 * @param deck
	 * @return if the move will lead to enemy start square
	 */
	public boolean IsEnemyStartSquare(Pawn pawn, int pos, Deck deck)
	{
		int possibleMove = pos;
		if ((deck.getSquares().get(possibleMove) instanceof StartSquare)
				&& (deck.getSquares().get(possibleMove).getColor() != pawn.getColor()))
		{
			return true;
		}

		return false;

	}

	/**
	 * Observer This method checks if the move that the user chose , will lead to
	 * move the pawn to Enemy safety zone square
	 * 
	 * @param pawn the pawn that we want move
	 * @param pos  the position that the pawn will move
	 * @param deck
	 * @return if the move will lead to enemy safety zone square
	 */
	public boolean IsEnemySafetyZone(Pawn pawn, int pos, Deck deck)
	{

		int possibleMove = pos;

		if ((deck.getSquares().get(possibleMove) instanceof SafetyZoneSquare)
				&& (deck.getSquares().get(possibleMove).getColor() != pawn.getColor()))
		{
			return true;
		}

		return false;

	}

	/**
	 * Observer This method checks if the move that the user chose , will lead to
	 * move the pawn to Enemy Home square
	 * 
	 * @param pawn the pawn that we want move
	 * @param pos  the position that the pawn will move
	 * @param deck
	 * @return if the move will lead to enemy home square
	 */
	public boolean IsEnemyHome(Pawn pawn, int pos, Deck deck)
	{
		int possibleMove = pos;

		if ((deck.getSquares().get(possibleMove) instanceof HomeSquare)
				&& (deck.getSquares().get(possibleMove).getColor() != pawn.getColor()))
		{
			return true;
		}

		return false;
	}

	/**
	 * Observer This Method checks if the move that the user chose , will lead to
	 * move the pawn to a square that there is already a friendly pawn
	 * 
	 * @param pawn The pawn that we want move
	 * @param pos  The position that the pawn will move
	 * @param deck
	 * @return if the move will lead to a square that there is already a friendly
	 *         pawn
	 */
	public boolean IsTwoSamePawnsOnSquare(Pawn pawn, int pos, Deck deck)
	{
		int possibleMove = pos;
		if(deck.getSquares().get(possibleMove).getCurr_pawn()!=null
				&& (deck.getSquares().get(possibleMove).getCurr_pawn().getColor() == pawn.getColor())
				&& deck.getSquares().get(possibleMove) instanceof HomeSquare)
				{
				System.out.println("probably a winner");
					return false;
				}
		if ((deck.getSquares().get(possibleMove).getCurr_pawn()!=null)
				&& (deck.getSquares().get(possibleMove).getCurr_pawn().getColor() == pawn.getColor()))
		{
			System.out.println("same pawns on square is not allowed");
			return true;
		}

		return false;

	}

	/**
	 * Observer This Method checks if the move that the user chose , will lead to
	 * move the pawn to a square that there is an enemy pawn
	 * 
	 * @param pawn The pawn that we want move
	 * @param pos  The position that the pawn will move
	 * @param deck
	 * @return if the move will lead to a square that there is already an enemy pawn
	 */
	public boolean IsEnemyPawnOnSquare(Pawn pawn, int pos, Deck deck)
	{
		int possibleMove = pos;

		if ((deck.getSquares().get(possibleMove).getCurr_pawn()!=null )
			&& (deck.getSquares().get(possibleMove).getCurr_pawn().getColor() != pawn.getColor()))
		{
			return true;
		}

		return false;
	}
	public boolean isPawnOnStartPosition(Pawn pawn)
	{
		if(pawn.getPosition()==-10)
		{
			return true;
		}
		return false;
	}

}
