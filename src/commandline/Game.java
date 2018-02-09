package commandline;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {
	/*
	 * Instance variables for the deck and communal pile which could potentially hold all of the cards in the game
	 * and the active pile which will have at most the one card for each of the five players.
	 * The active cards pile is set to this limit to simplify comparing the cards in play.
	 */
	private final int MAXCARDS = 40;
	private final int NUMPLAYERS = 5;
	private Cards[] deck = new Cards[MAXCARDS];
	private Cards[] communalPile = new Cards[MAXCARDS];
	public Cards[]  activeCards = new Cards[NUMPLAYERS];

	private AIPlayer[] players = new AIPlayer[NUMPLAYERS];

	private boolean timer = false;
	private int hpOut=0, p1Out=0, p2Out=0, p3Out=0, p4Out=0, totalOut=0, drawCount=0, drawNo=0;
	
	/**
	 * Method to run the game. Begins by assigning the deck, starting the initial round and dealing to the players.
	 * @param cards
	 */
	public Game(Cards[] cards) {
		deck = cards;
		for(int i = 0; i < NUMPLAYERS; i++) {
			players[i] = new AIPlayer();
		}

	}
	

	/**
	 * Deals the cards between players until there are no cards in the deck.
	 */
	public void deal()	{
		int playerCount =0;
		for(int i=0;i<MAXCARDS;) {
			for(int j=0; j<NUMPLAYERS;j++) {
				players[j].getPlayerHand()[playerCount] = deck[i];
				i++;
			}
			playerCount++;
		}
	}
		
	
	

	
	/**
	 * Method to display a visual card on the commandline
	 * @param c
	 * @return
	 */
	public String displayCard(Cards c)	{
		
		StringBuilder dSBuild = new StringBuilder("");
		dSBuild.append("______________________________\r\n"); //30
		dSBuild.append(String.format("| %26s |\r\n", c.getDescription()));
		dSBuild.append(String.format("| Size: %20d |\r\n", c.getSize()));
		dSBuild.append(String.format("| Speed: %19d |\r\n", c.getSpeed()));
		dSBuild.append(String.format("| Range: %19d |\r\n", c.getRange()));
		dSBuild.append(String.format("| Firepower: %15d |\r\n", c.getFirepower()));
		dSBuild.append(String.format("| Cargo: %19d |\r\n", c.getCargo()));
		dSBuild.append("______________________________"); //30
		return dSBuild.toString();
	}
	
	/**
	 * Creates an active card array of the current cards being played.
	 * The top card in each players hand, which is being used in the active pile, is set to null  and the sort cards method form AIPlayer
	 * is used to push the cards into their new order so the card in the first position is not empty.
	 * When a player is put they're out variable is set to 1 to keep track of the number of players in the game.
	 * Categories are enumerated in same order as on card (desc=0,size=1 etc.)
	 */
	
	public boolean playCategory(int c)	{
		
		//If human player has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(players[0].getTopCard()!=null&&hpOut<1) {
			activeCards[0] = players[0].getTopCard();
		}
		else if(players[0].getTopCard()==null) {
			System.out.println("You are out!");
			activeCards[0] = null;
			hpOut=1;
			timer = false;
		}
		
		//If player one has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(players[1].getTopCard()!=null&&p1Out<1) {
			activeCards[1] = players[1].getTopCard();
		}
		else if(players[1].getTopCard()==null) {
			System.out.println("Player 1 is out!");
			activeCards[1] = null;
			p1Out=1;
		}

		//If player two has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(players[2].getTopCard()!=null&&p2Out<1) {
			activeCards[2] = players[2].getTopCard();
		}
		else if(players[2].getTopCard()==null) {
			System.out.println("Player 2 is out!");
			activeCards[2] = null;
			p2Out=1;
		}

		//If player three has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(players[3].getTopCard()!=null&&p3Out<1) {
			activeCards[3] = players[3].getTopCard();
		}
		else if(players[3].getTopCard()==null) {
			System.out.println("Player 3 is out!");
			activeCards[3] = null;
			p3Out=1;
		}

		//If player four has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(players[4].getTopCard()!=null&&p4Out<1) {
			activeCards[4] = players[4].getTopCard();
		}
		else if(players[4].getTopCard()==null) {
			System.out.println("Player 4 is out!");
			activeCards[4] = null;
			p4Out=1;
		}
		

		/*
		 * If 4 players are out someone has won
		 */
		totalOut = hpOut+p1Out+p2Out+p3Out+p4Out;
		if(totalOut == 4) {
		//	playerWins();
			return true;
		}
		
		// Determines which card wins the round depending on which category was chosen by the current active player
		 
		//return doRoundCalc(c);
		return false;
	}
	
	/**
	 * Calculates which player has won the round or if it was a draw
	 * @param c
	 * @return
	 */
	public int doRoundCalc(int c) {
		//Values of the victor of the round, representing each player or a draw and the best value in the cards
		int victor=0, bestValue=0;
		//The value of the chosen categories for each player
		int[] cardValArray = new int[NUMPLAYERS];
		
		//Plays chosen category 1, size. Checks for the largest value in the category and sets that player as the victor
		if(c==1)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getSize();
					if(bestValue<activeCards[i].getSize())	{
						bestValue = activeCards[i].getSize();
						victor = i;
					}
				}
				else if(activeCards[i]==null)	{
					cardValArray[i]=-1;
				}
			}
		}
		//Plays chosen category 2, speed. Checks for the largest value in the category and sets that player as the victor
		else if(c==2)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getSpeed();
					if(bestValue<activeCards[i].getSpeed())	{
						bestValue = activeCards[i].getSpeed();
						victor = i;
					}
				}
				else if(activeCards[i]==null)	{
					cardValArray[i]=-1;
				}
			}
		}
		//Plays chosen category 3, range. Checks for the largest value in the category and sets that player as the victor
		else if(c==3)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getRange();
					if(bestValue<activeCards[i].getRange())	{
						bestValue = activeCards[i].getRange();
						victor = i;
					}
				}
				else if(activeCards[i]==null)	{
					cardValArray[i]=-1;
				}
			}
		}
		//Plays chosen category 4, firepower. Checks for the largest value in the category and sets that player as the victor
		else if(c==4)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getFirepower();
					if(bestValue<activeCards[i].getFirepower())	{
						bestValue = activeCards[i].getFirepower();
						victor = i;
					}
				}
				else if(activeCards[i]==null)	{
					cardValArray[i]=-1;
				}
			}
		}
		//Plays chosen category 5, cargo. Checks for the largest value in the category and sets that player as the victor
		else if(c==5)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getCargo();
					if(bestValue<activeCards[i].getCargo())	{
						bestValue = activeCards[i].getCargo();
						victor = i;
					}
				}
				else if(activeCards[i]==null)	{
					cardValArray[i]=-1;
				}
			}
		}
		else {
			System.out.println("Your choice did not match any of the options");
		
		}
		//tests for draw bases and passes a number above any possible player index to takePile to process a draw
		Arrays.sort(cardValArray);
		if(cardValArray[NUMPLAYERS-1]==cardValArray[NUMPLAYERS-2])	{
			return NUMPLAYERS;
		}
		
		return victor;
	}
	
	
	/**
	 * Gets the user input from the command line using scanner and returns the chosen category
	 * @return
	 */
	public int getPlayerChoice() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		//sc.close();
		return choice;
	}
	
	


	public void clearActiveCards() {
		for (int i = 0; i < activeCards.length; i++) {
			activeCards[i] = null;
		}
	}
	public void draw() {
//		drawCount = drawNo*NUMPLAYERS;
//		for(int i = 0; i < NUMPLAYERS; i++) {
//			communalPile[drawCount] = activeCards[i];
//			drawCount++;
//		}
//		
//		drawNo++;
		
		
		
		
		int active =0;
		for(int i=0; i<MAXCARDS;i++) {
			if(communalPile[i]==null) {
				if(active<NUMPLAYERS) {
					if(activeCards[active]!=null) {
						communalPile[i]=activeCards[active];
					}
					active++;
				}
				else {
					break;
				}
			}
		}
	
	}
	
	
	
	/**
	 * Clears the communal pile
	 */
	public void comClearer()	{
		for(int i=0; i<MAXCARDS; i++)	{
			communalPile[i]=null;
		}
		drawCount = 0;
	}
	
	public void nullAndSort() {
		/*
		 * Nulls the top cards in all of the player piles after placing them in the active pile
		 * Pushes all the cards up one slot in the array so the top card is not void
		 */
		
		for (int i = 0; i < NUMPLAYERS; i++) {
			players[i].nullTopCard();
			players[i].sortCards();
		}		
	}
	
	public void setDrawNo(int no) {
		drawNo = no;
	}
	
	public String getString() {
		return "Yes";
	}
	public Cards[] getActiveCards() {
		return activeCards;
	}
	public Cards[] getCommunalPile() {
		return communalPile;
	}
	public Cards getActiveCard(int c) {
		return activeCards[c];
	}
	
	public AIPlayer getPlayer(int p) {
		return players[p];
	}
	
	public AIPlayer[] getAllPlayers() {
		return players;
	}
	
	public int getNumPlayers() {
		return NUMPLAYERS;
	}
	
	public boolean isHPin() {
		if(players[0].getTopCard()!=null) {
			return true;
		}
		else {
			return false;
		}
	}
}
