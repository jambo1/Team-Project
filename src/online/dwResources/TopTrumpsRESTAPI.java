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
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		final int MAXCARDS = 40;
		final Cards[] deck = new Cards[MAXCARDS];
		int NUMPLAYERS;
		
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
	@GET
	@Path("/playCategory")
	public int playCategory(@QueryParam("Category") int category) throws IOException {
		int victor = 10;
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
			
		}
		else {
			victor = aGame.doRoundCalc(category);
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
	
	
	
	@GET
	@Path("/gameIsOver")
	public int gameIsOver() {
		return finalVictor;
	}
	
	@GET
	@Path("/isGameOver")
	public boolean isGameOver() {
		return gameOver;
	}
	
	@GET
	@Path("/startGame")
	public String startGame() throws IOException {
		createGame();
		gameOver = false;
		turn = 0;
		round = 0;
		return "game started se command line";
	}
	
	
	@GET
	@Path("/selection")
	public String getSelection(@QueryParam("Selection") int selection) throws IOException {
		return "" + selection + " was chosen";
	}
	
	@GET
	@Path("/numberOfPlayers")
	public String setNumberOfPlayers(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException {
		// Game.setNUMPLAYERS = numberOfPlayers;
		//System.out.println("You choose " + numberOfPlayers + " players. Good luck!");
		return "You choose " + numberOfPlayers + " players. Good luck!";
	}
	
	@GET
	@Path("/displayCard")
	public String displayCard() throws IOException {
		String cardThings = aGame.getPlayer(0).getTopCard().getDescription();
		return cardThings; 
		
	}
	
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Let's");
		listOfWords.add("Play!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	
	
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String word) throws IOException {
		return "Hello "+ word;
	}
	
	
	public boolean createGame() {
		try {
		TopTrumpsCLIApplication.createDeck();
		TopTrumpsCLIApplication.shuffle();
		deck = TopTrumpsCLIApplication.getDeck();
		System.out.println(deck[0].getDescription());
		aGame = new Game(deck);
		
		}
		catch(NullPointerException e) { 
			System.out.println("NullP"); 
		}
		
		return true;
	}
	
	
	
	
}
