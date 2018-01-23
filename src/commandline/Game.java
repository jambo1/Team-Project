package commandline;

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
	private Cards[]  activeCards = new Cards[NUMPLAYERS];
	//private AIplayer[] opponents = new AIPlayer[NUMPLAYERS-1];

	public Game(Cards[] cards) {
		deck = cards;
		 AIplayer[] opponents = CreateAIPlayers(NUMPLAYERS-1);
	}

	/**
	 *	Method to create desired number of AI players. The method returns an array of AIPlayer objects.
	 *	@return
	 */
	private AIPlayer[] CreateAIPlayers(int numberOfPlayers) {
		AIPlayer [] newPlayers = new AIPlayer[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			newPlayers[i] = new AIPlayer(MAXCARDS);
		}
		return newPlayers;
	}

}
