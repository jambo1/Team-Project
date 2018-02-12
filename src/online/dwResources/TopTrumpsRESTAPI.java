package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.*;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private Cards[] deck;
	private Game aGame;
	private boolean gameOver;
	private int round, turn;
	private int finalVictor;
	private static int humanRounds, p1Rounds, p2Rounds, p3Rounds, p4Rounds, drawRounds; 
	private static int winner;
	private Interaction in = new Interaction();

	private int numberOfPlayers;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		
		
		
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		/*
		
		 */
		//
		
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	/**
	 * This method is called whenever user presses 'Play Round'. Pretty much reasembles the 
	 * command line game logic but without any system.out. This funciton @returns who won
	 * the round. 
	 * @param category
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/playCategory")
	public int playCategory(@QueryParam("Category") int category) throws IOException {
		int victor = 10;
		round++;
		if (turn == 0) {
			// do nothing, use QueryParam since it's the users choice
		} else {
			category = aGame.getPlayer(turn).selectCategory(aGame.getPlayer(turn).getTopCard(), false);
		}
		gameOver = aGame.playCategory(category);
		aGame.nullAndSort();
		if (gameOver) {
			for (int i = 0; i < aGame.getNumPlayers(); i++) {
				if (aGame.getPlayer(i).getTopCard() != null ) {
					finalVictor = i;
				}
			}
			updateRoundWinner(finalVictor);
			updatePersistent();
			
		}
		else {
			victor = aGame.doRoundCalc(category);
			updateRoundWinner(victor);
			if (victor != aGame.getNumPlayers()) {
				aGame.setDrawNo(0);
				aGame.getPlayer(victor).givePlayerCards(aGame.getActiveCards(), aGame.getCommunalPile());
				
				turn = victor;
				aGame.comClearer();
				
			}
			else {
				aGame.draw();
			}
			aGame.clearActiveCards();

		}

	
		return victor;
	}
	
	
	/**
	 * Method called when isGameOver has returned true. This method @returns who won the game
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/gameIsOver")
	public int gameIsOver()  throws IOException {
		return finalVictor;
	}
	
	/**
	 * This method is called to check wether or not the game is over.
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/isGameOver")
	public int isGameOver() throws IOException {
		
		if (gameOver) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@GET
	@Path("isHumanOut")
	public int isHumanOut() throws IOException {
		if (aGame.isHPin() == true) 
			return 0;
		else
			return 1;
	}
	/**
	 * This method is called when user press 'Start Game'. it creates a new game and 
	 * set all necessary parameters to their default values. Doesn't need to return anything.
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/startGame")
	public String startGame() throws IOException {
		createGame();
		gameOver = false;
		turn = 0;
		round = 0;
		return "game started";
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getNumberOfPlayers")
	public int getNumberOfPlayers() throws IOException {
		return aGame.getNumPlayers();
	}
	
	@GET
	@Path("/autoPlay")
	public int autoPlay() throws IOException {
		while (gameOver == false) {
			playCategory(0);
		}
		
		return finalVictor;
	}
	
	
	
	/**
	 * This funciton is called whenever a user presses a button on the home screen.
	 * This function can be deleted i think -----------------------------------
	 * @param selection
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/selection")
	public String getSelection(@QueryParam("Selection") int selection) throws IOException {
		return "" + selection + " was chosen";
	}
	
	/**
	 * This function is meant to be called when the user starts the game. A way to set the 
	 * amount of players for the game. Does not need to return anything.
	 * @param numberOfPlayers
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/numberOfPlayers")
	public String setNumberOfPlayers(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException {
		this.numberOfPlayers = numberOfPlayers;
		//Game.setNUMPLAYERS = numberOfPlayers;
		//System.out.println("You choose " + numberOfPlayers + " players. Good luck!");
		return "You choose " + numberOfPlayers + " players. Good luck!";
	}
	
	/**
	 * This method has been replaced by getCardDescription and getCardValues
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/displayCard")
	public String displayCard() throws IOException {
		String cardThings = aGame.getPlayer(0).getTopCard().getDescription();
		return cardThings; 
	}
	
	/**
	 * This method returns the Description of a certain players current top card. 
	 * @param player
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getCardDescription")
	public String getCardDescription(@QueryParam("player") int player) throws IOException {
		try {
		System.out.println(aGame.getPlayer(player).getTopCard().getDescription());
		return aGame.getPlayer(player).getTopCard().getDescription();
		}
		catch(NullPointerException n1)	{
			return "Player not in game";
		}
	}
	
	@GET
	@Path("/getCardSize")
	public String getCardSize(@QueryParam("player") int player) throws IOException {
	try {
		return "" + aGame.getPlayer(player).getTopCard().getSize();
	}
	catch(NullPointerException n2)	{
		return "Player not in game";
	}
	}
	
	@GET
	@Path("/getCardSpeed")
	public String getCardSpeed(@QueryParam("player") int player) throws IOException {
try {
		return "" + aGame.getPlayer(player).getTopCard().getSpeed();
}
catch(NullPointerException n3)	{
	return "Player not in game";
}
	}
	
	@GET
	@Path("/getCardRange")
	public String getCardRange(@QueryParam("player") int player) throws IOException {
try {
		return "" + aGame.getPlayer(player).getTopCard().getRange();
}
catch(NullPointerException n4)	{
	return "Player not in game";
}
	}
	@GET
	@Path("/getCardFirepower")
	public String getCardFirepower(@QueryParam("player") int player) throws IOException {
try {
		return "" + aGame.getPlayer(player).getTopCard().getFirepower();
}
catch(NullPointerException n5)	{
	return "Player not in game";
}
	}
	@GET
	@Path("/getCardCargo")
	public String getCardCargo(@QueryParam("player") int player) throws IOException {
try {
		return "" + aGame.getPlayer(player).getTopCard().getCargo();
}
catch(NullPointerException n6)	{
	return "Player not in game";
}
	}
	
	/**
	 * This method returns a string with all values of a certain players current top card.
	 * @param player
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getCardValues")
	public String getCardValues(@QueryParam("player") int player)  throws IOException {
		try {
		StringBuilder sb = new StringBuilder("");
		sb.append(String.format("%26s\r\n\r\n", aGame.getPlayer(player).getTopCard().getDescription()));
		sb.append(String.format("Size: %20d\r\n", aGame.getPlayer(player).getTopCard().getSize()));
		sb.append(String.format("Speed: %19d\r\n", aGame.getPlayer(player).getTopCard().getSpeed()));
		sb.append(String.format("Range: %19d\r\n",aGame.getPlayer(player).getTopCard().getRange()));
		sb.append(String.format("Firepower: %15d\r\n",aGame.getPlayer(player).getTopCard().getFirepower()));
		sb.append(String.format("Cargo: %19d\r\n",aGame.getPlayer(player).getTopCard().getCargo()));
		
		return sb.toString();
		}
		catch(NullPointerException n1)	{
			return "Not in game";
		}
	}
	

	

	/**
	 * This method creates a deck, shuffles the deck and initialises a new game with said deck.
	 * 
	 * @return
	 */
	public boolean createGame() {
		try {
		TopTrumpsCLIApplication.createDeck();
		
		deck = TopTrumpsCLIApplication.getDeck();
		Collections.shuffle(Arrays.asList(deck));
		aGame = new Game(deck, numberOfPlayers);
		aGame.deal();

		gameOver = false;
		turn = 0;
		round = 0;
		humanRounds = 0;
		p1Rounds = 0;
		p2Rounds = 0;
		p3Rounds = 0;
		p4Rounds = 0;
		drawRounds = 0;
		
		}
		catch(NullPointerException e) { 
			System.out.println("NullP"); 
		}
		
		return true;
	}
	

	@GET
	@Path("/totalGames")
	public int totalGames() {
		int stats = in.TotalGames();
		return stats;
	}
	@GET
	@Path("/humanWins")
	public int humanWins() {
		int stats = in.HumanWins();
		return stats;
	}
	@GET
	@Path("/AIwins")
	public int aiWins() {
		int stats = in.AIwins();
		return stats;
	}
	
	@GET
	@Path("/averageDraws")
	public int averageDraws() {
		int stats = in.AverageDraws();
		return stats;
	}
	
	@GET
	@Path("/longestGame")
	public int longestGame() {
		int stats = in.HighestRounds();
		return stats;
	}
	
	
	@GET
	@Path("/connect")
	public void connect() {
		in.connection();
	}

	@GET
	@Path("/disconnect")
	public void disconnect() {
		in.disconnect();
	}
	private static void updateRoundWinner(int winner) {
	System.out.println(winner);	
		if(winner == 0) { 
			humanRounds++;
		}
		else if(winner == 1) { 
			p1Rounds++;
		}
		else if(winner == 2) { 
			p2Rounds++;
		}
		else if(winner == 3) { 
			p3Rounds++;
		}
		else if(winner == 4) { 
			p4Rounds++;
		}
		else if(winner == 5) { 
			drawRounds++;
		}
	}
	private void updatePersistent() {
		//Update data for the sql queries
		
		in.updateSQL(humanRounds, p1Rounds, p2Rounds, p3Rounds, p4Rounds, drawRounds, round, winner);
		//Update statistics in the SQL database
		in.updateStats();
		
		
	}
}
