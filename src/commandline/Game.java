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
	private int nPlayers;
	private Cards[] deck = new Cards[MAXCARDS];
	private Cards[] communalPile = new Cards[MAXCARDS];
	public Cards[]  activeCards;

	private AIPlayer[] players;

	private boolean timer = false;
	private int hpOut=0, p1Out=0, p2Out=0, p3Out=0, p4Out=0, totalOut=0, drawCount=0, drawNo=0;
	
	/**
	 * Method to run the game. Begins by assigning the deck, starting the initial round and dealing to the players.
	 * @param cards
	 */
	public Game(Cards[] cards) {
		deck = cards;
		nPlayers=NUMPLAYERS;
		activeCards = new Cards[nPlayers];
		players = new AIPlayer[nPlayers];
		for(int i = 0; i < NUMPLAYERS; i++) {
			players[i] = new AIPlayer();
		}

	}
	//////////////////////////////
	public Game(Cards[] cards, int nPlayers) {
		this.nPlayers = nPlayers;
		deck = cards;
		activeCards = new Cards[nPlayers];
		players = new AIPlayer[nPlayers];
		for(int i = 0; i < nPlayers; i++) {
			players[i] = new AIPlayer();
		}

	}
	////////////////////////////////////////

	/**
	 * Deals the cards between players until there are no cards in the deck.
	 */
	public void deal()	{
/*		int playerCount =0;
		for(int i=0;i<MAXCARDS;) {
			for(int j=0; j<nPlayers;j++) {
				players[j].getPlayerHand()[playerCount] = deck[i];
				i++;
			}
			playerCount++;
		}
*/		
		for(int i=0; i<MAXCARDS; i++)	{
			players[i%nPlayers].getPlayerHand()[i/nPlayers]=deck[i];
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
		
		for(int i=0; i<nPlayers; i++)	{
			if(players[i].getTopCard()!=null&&players[i].getOut()==false) {
				activeCards[i] = players[i].getTopCard();
			}
			else if(players[i].getTopCard()==null) {
				if (i == 0) {
					System.out.println("You are out!");
					activeCards[i] = null;
					players[i].setOut();
					if(i==1)	{
						timer = false;
					}	
				} else {
					System.out.println(String.format("Player %d is out", i));
					activeCards[i] = null;
					players[i].setOut();
				}
			}
		}
		


		/*
		 * If totalOut = nPlayers - 1 someone has won
		 */
		totalOut = 0;
		for(int i=0; i<nPlayers; i++)	{
			if(players[i].getOut()==true)	{totalOut++;}
		}
		if(totalOut == nPlayers-1) {
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
		int[] cardValArray = new int[nPlayers];
		
		//Plays chosen category 1, size. Checks for the largest value in the category and sets that player as the victor
		if(c==1)	{
			for(int i=0; i<nPlayers; i++)	{
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
			for(int i=0; i<nPlayers; i++)	{
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
			for(int i=0; i<nPlayers; i++)	{
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
			for(int i=0; i<nPlayers; i++)	{
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
			for(int i=0; i<nPlayers; i++)	{
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
		if(cardValArray[nPlayers-1]==cardValArray[nPlayers-2])	{
			return nPlayers;
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
		if (choice >0 && choice<6)	{
			return choice;
		}
		else {
		System.err.println("Invalid input. Please select a number between 1 and 5");
		return getPlayerChoice();
		}
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
				if(active<nPlayers) {
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
		
		for (int i = 0; i < nPlayers; i++) {
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
		try{return players[p];}
		catch(ArrayIndexOutOfBoundsException n)	{
			return null;
		}
	}
	
	public AIPlayer[] getAllPlayers() {
		return players;
	}
	
	public int getNumPlayers() {
		return nPlayers;
	}
	
	public void setNumPlayers(int nP)	{nPlayers = nP;}
	
	public boolean isHPin() {
		if(players[0].getTopCard()!=null) {
			return true;
		}
		else {
			return false;
		}
	}
}
