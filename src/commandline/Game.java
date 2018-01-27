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
	private Cards[]  activeCards = new Cards[NUMPLAYERS];
	private AIPlayer hp = new AIPlayer();
	private AIPlayer p1 = new AIPlayer();
	private AIPlayer p2 = new AIPlayer();
	private AIPlayer p3 = new AIPlayer();
	private AIPlayer p4 = new AIPlayer();
	private String winnerString;
	private boolean gameOver = false;
	
	//this is the basic running order of the game - NEED TO UTILISE GAME OVER
	public Game(Cards[] cards) {
		deck = cards;
		round=0;
		turn=0;
		deal();
		while(gameOver==false)	{
			playRound(turn);
			round++;
		}
	}
	
	//deals the cards, human player starts with same card all the time atm, might be shuffle error
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
	
	//player of round determined by turn counter t
	//Always prints human card to screen first, then prints result of either human choice or computer choice
	private void playRound(int t)	{
		try {
		System.out.println(displayCard(hp.getTopCard()));
		}
		catch(NullPointerException n)	{
			System.out.println("No card :(");
		}
		if(t==0)	{
			System.out.println("Please select a category to play:");
			System.out.println("1 = Size, 2 = Speed, 3 = Range, 4 = Firepower, 5 = Cargo");
			System.out.println(playCategory(getUserChoice()));
		}
		else if(t==1)	{
			System.out.println("Player 1 is choosing a category to play:");
			System.out.println(playCategory(p1.selectCategory(p1.getTopCard())));
		}
		else if(t==2)	{
			System.out.println("Player 2 is choosing a category to play:");
			System.out.println(playCategory(p2.selectCategory(p2.getTopCard())));
		}
		else if(t==3)	{
			System.out.println("Player 3 is choosing a category to play:");
			System.out.println(playCategory(p3.selectCategory(p3.getTopCard())));			
		}
		else if(t==4)	{
			System.out.println("Player 4 is choosing a category to play:");
			System.out.println(playCategory(p4.selectCategory(p4.getTopCard())));
		}
		else	{
			System.out.println("play round is fucked");
		}
		
		//Set the top card in each hand to null
		hp.nullTopCard();
		p1.nullTopCard();
		p2.nullTopCard();
		p3.nullTopCard();
		p4.nullTopCard();
		
		//Move each card in each hand down one slot
		hp.sortCards();
		p1.sortCards();
		p2.sortCards();
		p3.sortCards();
		p4.sortCards();
		
	}
	
	//method to make wee card on screen, can def be polished if we have time
	private String displayCard(Cards c)	{
		
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
	
	/*
	 * creates active card array, then zeroes top card in each hand so in following round the remaining cards are pushed up
	 * (using method in AIPlayer) categories are enumerated in same order as on card (desc=0,size=1 etc.)
	 */
	
	private String playCategory(int c)	{
		int victor=0, bestValue=0;
		int hpOut=0, p1Out=0, p2Out=0, p3Out=0, p4Out=0, totalOut=0;
		int[] cardValArray = new int[NUMPLAYERS];
		if(hp.getTopCard()!=null) {
			activeCards[0] = hp.getTopCard();
		}
		else if(hp.getTopCard()==null && hpOut<1) {
			System.out.println("You are out!");
			hpOut++;
			totalOut++;
		}
		
		if(p1.getTopCard()!=null) {
			activeCards[1] = p1.getTopCard();
		}
		else if(p1.getTopCard()==null && p1Out<1) {
			System.out.println("Player 1 is out!");
			p1Out++;
			totalOut++;
		}
		
		if(p2.getTopCard()!=null) {
			activeCards[2] = p2.getTopCard();
		}
		else if(p2.getTopCard()==null && p2Out<1) {
			System.out.println("Player 2 is out!");
			p2Out++;
			totalOut++;
		}
		
		if(p3.getTopCard()!=null) {
			activeCards[3] = p3.getTopCard();
		}
		else if(p3.getTopCard()==null && p3Out<1) {
			System.out.println("Player 3 is out!");
			p3Out++;
			totalOut++;
		}
		
		if(p4.getTopCard()!=null) {
			activeCards[4] = p4.getTopCard();
		}
		else if(p4.getTopCard()==null && p4Out<1) {
			System.out.println("Player 4 is out!");
			p4Out++;
			totalOut++;
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
		
		/*
		 * If 4 players are out someone has won
		 */
		if(totalOut ==4) {
			playerWins();
		}
		
		//Handle choice of first card category
		if(c==1)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getSize();
					if(bestValue<activeCards[i].getSize())	{
						bestValue = activeCards[i].getSize();
						victor = i;
					}
				}
			}
		}
		//Handle choice of second card category
		else if(c==2)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getSpeed();
					if(bestValue<activeCards[i].getSpeed())	{
						bestValue = activeCards[i].getSpeed();
						victor = i;
					}
				}
			}
		}
		//Handle choice of third card category
		else if(c==3)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getRange();
					if(bestValue<activeCards[i].getRange())	{
						bestValue = activeCards[i].getRange();
						victor = i;
					}
				}
			}
		}
		//Handle choice of fourth card category
		else if(c==4)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getFirepower();
					if(bestValue<activeCards[i].getFirepower())	{
						bestValue = activeCards[i].getFirepower();
						victor = i;
					}
				}
			}
		}
		//Handle choice of fifth card category
		else if(c==5)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				if(activeCards[i]!=null) {
					cardValArray[i] = activeCards[i].getCargo();
					if(bestValue<activeCards[i].getCargo())	{
						bestValue = activeCards[i].getCargo();
						victor = i;
					}
				}
			}
		}
		//Any other user choice returns error message and starts round again
		else {
			System.out.println("Your choice did not match any of the options");
			playRound(0);
		}
		//tests for draw basis and passes a number above any possible player index to takePile to process a draw
		Arrays.sort(cardValArray);
		if(cardValArray[NUMPLAYERS-1]==cardValArray[NUMPLAYERS-2])	{
			return takePile(NUMPLAYERS);
		}
		
		return takePile(victor);
	}
	
	
	/*
	 * scans for user input, error if not always scanning for some reason (ie. if it gets closed)
	 */
	private int getUserChoice() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		//sc.close();
		return choice;
	}
	
	
	/*
	 * creates winner string and allocates communal and active cards to winner
	 */
	private String takePile(int v)	{		
		//Give winner their cards
		if(v==0)	{
			hp.givePlayerCards(activeCards, communalPile);
			winnerString = "You won that round!";
			turn = 0;
		}
		else if(v==1)	{
			p1.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 1 won that round!";
			turn = 1;
		}
		else if(v==2)	{
			p2.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 2 won that round!";
			turn = 2;
		}
		else if(v==3)	{
			p3.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 3 won that round!";
			turn = 3;
		}
		else if(v==4)	{
			p4.givePlayerCards(activeCards, communalPile);
			winnerString = "Player 4 won that round!";
			turn = 4;
		}
		//Process draw
		else if(v==5) {
			int comStart = 0;
			while(communalPile[comStart]!= null)	{comStart++;}
			System.out.println("----------Active cards going to Communal---------");
			for(int i=0;i<NUMPLAYERS;i++)	{
				//place all the non null activecards into the communal pile
				if(activeCards[i]!=null) {
					communalPile[comStart] = activeCards[i];
					System.out.println(communalPile[comStart].getDescription() + "----------pre com"); //THIS IS A TEST TO EMPTY ACTIVE PILE, may not actually be needed
					comStart++;
				}
			}
			winnerString = "That round was a draw!";
		}
		
		//Show winning card if hand is not a draw
		if(v<5) {
			System.out.println("----------Round Winning Card----------");
			System.out.println(displayCard(activeCards[v]));
			System.out.println("---------------------------------------");
			//Clear communal pile
			clearComPile();
		}
		//clear active pile
		clearActivePile();
		return winnerString;
	}
	/**
	 * Sets all cards in the communal pile to null after a player has won them and taken them
	 */
	private void clearComPile() {
		  for(int j=0;j<MAXCARDS;j++) {
			  communalPile[j]=null;
		  }
	}
	
	/**
	 * Clears the active pile after the cards have been given to the winner or communal pile
	 */
	private void clearActivePile() {
		  for(int j=0;j<NUMPLAYERS;j++) {
			  activeCards[j]=null;
		  }
	}
	
	/*
	 * Handles a player winning the game
	 */
	private void playerWins() {
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
		gameOver = true;
	}
}