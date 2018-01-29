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
	private int hpOut=0, p1Out=0, p2Out=0, p3Out=0, p4Out=0, totalOut=0;
	
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
	
	//deals the cards
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
		/*else if(t==0&&hpOut==1)	{
			t++;
			return;
		}*/
		if(t==1)	{
			System.out.println("Player 1 is choosing a category to play:");
			System.out.println(playCategory(p1.selectCategory(p1.getTopCard())));
		}
		/*else if(t==1&&p1Out==1)	{
			t++;
			return;
		}*/
		if(t==2)	{
			System.out.println("Player 2 is choosing a category to play:");
			System.out.println(playCategory(p2.selectCategory(p2.getTopCard())));
		}
		/*else if(t==2&&p2Out==1)	{
			t++;
			return;
		}*/
		if(t==3)	{
			System.out.println("Player 3 is choosing a category to play:");
			System.out.println(playCategory(p3.selectCategory(p3.getTopCard())));			
		}
		/*else if(t==3&&p3Out==1)	{
			t++;
			return;
		}*/
		if(t==4)	{
			System.out.println("Player 4 is choosing a category to play:");
			System.out.println(playCategory(p4.selectCategory(p4.getTopCard())));
		}
		/*else if (t==4&&p4Out==1)	{
			t=0;
			return;
		}*/
		
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
		int[] cardValArray = new int[NUMPLAYERS];
		
		if(hp.getTopCard()!=null&&hpOut<1) {
			activeCards[0] = hp.getTopCard();
		}
		else if(hp.getTopCard()==null) {
			System.out.println("You are out!");
			activeCards[0] = null;
			hpOut=1;
		}
		
		if(p1.getTopCard()!=null&&p1Out<1) {
			activeCards[1] = p1.getTopCard();
		}
		else if(p1.getTopCard()==null) {
			System.out.println("Player 1 is out!");
			activeCards[1] = null;
			p1Out=1;
		}
		
		if(p2.getTopCard()!=null&&p1Out<1) {
			activeCards[2] = p2.getTopCard();
		}
		else if(p2.getTopCard()==null) {
			System.out.println("Player 2 is out!");
			activeCards[2] = null;
			p2Out=1;
		}
		
		if(p3.getTopCard()!=null&&p3Out<1) {
			activeCards[3] = p3.getTopCard();
		}
		else if(p3.getTopCard()==null) {
			System.out.println("Player 3 is out!");
			activeCards[3] = null;
			p3Out=1;
		}
		
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
		
		hp.nullTopCard();
		p1.nullTopCard();
		p2.nullTopCard();
		p3.nullTopCard();
		p4.nullTopCard();
		
		hp.sortCards();
		p1.sortCards();
		p2.sortCards();
		p3.sortCards();
		p4.sortCards();
		
		/*
		 * If 4 players are out someone has won
		 */
		totalOut = hpOut+p1Out+p2Out+p3Out+p4Out;
		if(totalOut == 4) {
			playerWins();
		}
		
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
			playRound(0);
		}
		//tests for draw bases and passes a number above any possible player index to takePile to process a draw
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
			int comStart = 0;
			while(communalPile[comStart]!= null)	{comStart++;}
			System.out.println("---------------Active going to Comm---------");
			for(int i=0;i<NUMPLAYERS;i++)	{
				communalPile[comStart] = activeCards[i];
				if(communalPile[comStart]==null)	{
					System.out.println(comStart + "null----------pre com");
				}
				else {
				System.out.println(communalPile[comStart].getDescription() + "----------pre com");
				comStart++;
				}
			}
			winnerString = "That round was a draw!";
		}
		
		//Show winning card if hand is not a draw
	/*	if(v<5) {
			System.out.println("----------Round Winning Card----------");
			System.out.println(displayCard(activeCards[v]));
			System.out.println("---------------------------------------");
			int comClearer = 0;
			for(int i=0; i<communalPile.length; i++)	{
		//		System.out.println("Pre-clear val");
			//	System.out.println(communalPile[i]);
				communalPile[i]=null;
		//		System.out.println("Post-clear val");
			//	if(communalPile[comClearer]==null)	{
				//	System.out.println("null");
			//	}
				comClearer++;
			}
		}
		*/
		return winnerString;
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
	
	private void comClearer()	{
		for(int i=0; i<MAXCARDS; i++)	{
			communalPile[i]=null;
		}
	}
}