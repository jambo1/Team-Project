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
	private int round, turn;
	private Cards[] deck = new Cards[MAXCARDS];
	private Cards[] communalPile = new Cards[MAXCARDS];
	public Cards[]  activeCards = new Cards[NUMPLAYERS];
	public AIPlayer hp = new AIPlayer();
	private AIPlayer p1 = new AIPlayer();
	private AIPlayer p2 = new AIPlayer();
	private AIPlayer p3 = new AIPlayer();
	private AIPlayer p4 = new AIPlayer();
	private String winnerString;
	private boolean gameOver = false;
	private boolean timer = true;
	private int hpOut=0, p1Out=0, p2Out=0, p3Out=0, p4Out=0, totalOut=0, drawCount=0, drawNo=0;
	
	/**
	 * Method to run the game. Begins by assigning the deck, starting the initial round and dealing to the players.
	 * @param cards
	 */
	public Game(Cards[] cards) {
		deck = cards;
		round=1;
		turn=0;
		deal();
		//Loops through the game and continues until one player is victorious
		
		
		
		/**
		 * EUREKA
		 * NEEDS TO BE MOVED TO CLI... then use aGame.playRound(turn); from there.
		 * 
		 *  otherwise aGame can never be initialized. it will be stuck in the constructor
		 *  until the game is finished, hence - we cant play the game from Online.
		 */
//		while(gameOver==false)	{
//			StringBuilder roundString = new StringBuilder("");
//			System.out.println(roundString.append(String.format("--------------- Round %2d ---------------", round)).toString());
//			playRound(turn);
//			round++;
//		}
	}
	
	/**
	 * Deals the cards between players until there are no cards in the deck.
	 */
	private void deal()	{
		int cardCount = 0, playerCount=0;
		while(cardCount<MAXCARDS)	{
			hp.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p1.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p2.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p3.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p4.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			playerCount++;
		}
	}
	
	
	/**
	 * Uses turn counter "t" to determine which player is choosing the category. 
	 * If the player is the human then they are asked to select a category to play. 
	 * Non human players use the method in AIPlayer to select and play the highest card 
	 * @param t
	 */
	private void playRound(int t)	{
		//Checks if the human still has cards to play
		try {
			System.out.println(displayCard(hp.getTopCard()));
		}
		//If not prints a message to say they have no card
		catch(NullPointerException n)	{
			System.out.println("No card :(");
		}
		//Human user's turn
		if(t==0)	{
			System.out.println("Please select a category to play:");
			System.out.println("1 = Size, 2 = Speed, 3 = Range, 4 = Firepower, 5 = Cargo");
			System.out.println(playCategory(getUserChoice()));
		}
		//AIPlayers' turns
		if(t==1)	{
			System.out.println("Player 1 is choosing a category to play:");
			System.out.println(playCategory(p1.selectCategory(p1.getTopCard(), timer)));
		}
		if(t==2)	{
			System.out.println("Player 2 is choosing a category to play:");
			System.out.println(playCategory(p2.selectCategory(p2.getTopCard(), timer)));
		}
		if(t==3)	{
			System.out.println("Player 3 is choosing a category to play:");
			System.out.println(playCategory(p3.selectCategory(p3.getTopCard(), timer)));			
		}
		if(t==4)	{
			System.out.println("Player 4 is choosing a category to play:");
			System.out.println(playCategory(p4.selectCategory(p4.getTopCard(), timer)));
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
	
	private String playCategory(int c)	{
		
		//If human player has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(hp.getTopCard()!=null&&hpOut<1) {
			activeCards[0] = hp.getTopCard();
		}
		else if(hp.getTopCard()==null) {
			System.out.println("You are out!");
			activeCards[0] = null;
			hpOut=1;
			timer = false;
		}
		
		//If player one has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(p1.getTopCard()!=null&&p1Out<1) {
			activeCards[1] = p1.getTopCard();
		}
		else if(p1.getTopCard()==null) {
			System.out.println("Player 1 is out!");
			activeCards[1] = null;
			p1Out=1;
		}

		//If player two has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(p2.getTopCard()!=null&&p2Out<1) {
			activeCards[2] = p2.getTopCard();
		}
		else if(p2.getTopCard()==null) {
			System.out.println("Player 2 is out!");
			activeCards[2] = null;
			p2Out=1;
		}

		//If player three has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(p3.getTopCard()!=null&&p3Out<1) {
			activeCards[3] = p3.getTopCard();
		}
		else if(p3.getTopCard()==null) {
			System.out.println("Player 3 is out!");
			activeCards[3] = null;
			p3Out=1;
		}

		//If player four has a topcard it is placed in the activecards pile, otherwise they are set to being out
		if(p4.getTopCard()!=null&&p4Out<1) {
			activeCards[4] = p4.getTopCard();
		}
		else if(p4.getTopCard()==null) {
			System.out.println("Player 4 is out!");
			activeCards[4] = null;
			p4Out=1;
		}
		
		System.out.println("-----HUMAN HAND --------");
		hp.printHand();
		System.out.println("-----P1 HAND --------");
		p1.printHand();
		System.out.println("-----P2 HAND --------");
		p2.printHand();
		System.out.println("-----P3 HAND --------");
		p3.printHand();
		System.out.println("-----P4 HAND --------");
		p4.printHand();
		
		nullAndSort();
	
		/*
		 * If 4 players are out someone has won
		 */
		totalOut = hpOut+p1Out+p2Out+p3Out+p4Out;
		if(totalOut == 4) {
			playerWins();
		}
		
		// Determines which card wins the round depending on which category was chosen by the current active player
		 
		return doRoundCalc(c);
	}
	
	/**
	 * Calculates which player has won the round or if it was a draw
	 * @param c
	 * @return
	 */
	public String doRoundCalc(int c) {
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
			doRoundCalc(c);
		}
		//tests for draw bases and passes a number above any possible player index to takePile to process a draw
		Arrays.sort(cardValArray);
		if(cardValArray[NUMPLAYERS-1]==cardValArray[NUMPLAYERS-2])	{
			return takePile(NUMPLAYERS);
		}
		
		return takePile(victor);
	}
	
	
	/**
	 * Gets the user input from the command line using scanner and returns the chosen category
	 * @return
	 */
	private int getUserChoice() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		return choice;
	}
	
	
	/**
	 * Gives the winner the communal and active piles.
	 * If the round is a draw the cards go into the communal pile.
	 * @param v
	 * @return
	 */
	private String takePile(int v)	{		
		//If there is not a draw then the round winning card is printed
		if(v<5) {
			System.out.println("----------Round Winning Card----------");
			System.out.println(displayCard(activeCards[v]));
			System.out.println("---------------------------------------");
			drawNo=0;
		}
		/*
		 * Process each of the winners
		 */
		if(v==0)	{
			hp.givePlayerCards(activeCards, communalPile);
			winnerString = "You won that round!";
			comClearer();
			turn = 0;
		}
		else if(v==1)	{
			p1.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 1 won that round!";
			comClearer();
			turn = 1;
		}
		else if(v==2)	{
			p2.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 2 won that round!";
			comClearer();
			turn = 2;
		}
		else if(v==3)	{
			p3.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 3 won that round!";
			comClearer();
			turn = 3;
		}
		else if(v==4)	{
			p4.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 4 won that round!";
			comClearer();
			turn = 4;
		}
		//Process draw
		else if(v==5) {
			drawCount = drawNo*NUMPLAYERS;
			for(int i=0;i<NUMPLAYERS;i++)	{
				communalPile[drawCount] = activeCards[i];
				drawCount++;
			}
			drawNo++;
			winnerString = "That round was a draw!";
		}
		return winnerString;
	}
	
	/**
	 * Process when a player wins the game
	 */
	private void playerWins() {
		//Print a buffer
		System.out.println("");
		System.out.println("");
		for(int i =0;i<30;i++) {
			System.out.print("-");
		}
		System.out.println("");
		
		//Print the winner
		if(hp.getTopCard()!=null) {
			System.out.println("Congratulations you have won!");
		}
		else if(p1.getTopCard()!=null){
			System.out.println("Player 1 has won the game!");
		}
		else if(p2.getTopCard()!=null){
			System.out.println("Player 2 has won the game!");
		}
		else if(p3.getTopCard()!=null){
			System.out.println("Player 3 has won the game!");
		}
		else if(p4.getTopCard()!=null){
			System.out.println("Player 4 has won the game!");
		}
		
		//Print another buffer
		for(int i =0;i<30;i++) {
			System.out.print("-");
		}
		System.out.println("");
		System.out.println("");
		
		gameOver = true;
	}
	
	/**
	 * Clears the communal pile
	 */
	private void comClearer()	{
		for(int i=0; i<MAXCARDS; i++)	{
			communalPile[i]=null;
		}
	}
	
	private void nullAndSort() {
		/*
		 * Nulls the top cards in all of the player piles after placing them in the active pile
		 */
		hp.nullTopCard();
		p1.nullTopCard();
		p2.nullTopCard();
		p3.nullTopCard();
		p4.nullTopCard();
		
		/*
		 * Pushes all the cards up one slot in the array so the top card is not void
		 */
		hp.sortCards();
		p1.sortCards();
		p2.sortCards();
		p3.sortCards();
		p4.sortCards();
		
	}
	public String getString() {
		return "Yes";
	}
//	public AIPlayer getPlayer(int p) {
//		if (p>=0 && p<=4) {
//			if (p==0)
//				return this.hp;
//			else if (p==1)
//				return this.p1;
//			else if (p==2)
//				return this.p2;
//			else if (p==3)
//				return this.p3;
//			else // must be player 4
//				return this.p4;
//		}
//		else
//			return null;
//	}
}