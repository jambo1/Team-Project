
package commandline;

import java.sql.*;

import commandline.Interaction;

public class Interaction {

	private Connection connection =null;
	private int newGame;

	//Variables to store the round winds for each player
	private int hrw, p2rw, p3rw, p4rw , p5rw; 
	//Total draws and total rounds
	private int td , tr;
	//updates the wins for either ai player or human
	private int aiwins, humanwins;

	

	public Interaction() {
	}

	public void connection () {

		String dbname = "m_17_2356209m";
		String username = "m_17_2356209m";
		String password = "2356209m";


		try {
			connection =
					DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" +
							dbname,username, password);
		}
		catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
//			System.out.println("Connection successful");
		}
		else {
			System.err.println("Failed to make connection!");
		}

	}

	public int TotalGames () 
	{
		Statement stmt = null;
		String query = "SELECT max(totalgames) AS totalgames\r\n" +
				"FROM toptrumps.overallstats;";


		int TotalGames = 0;
	 
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				TotalGames = rs.getInt("totalgames");

			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		return TotalGames;

	}

	public int AIwins ()
	{
		Statement stmt = null;
		String query = "SELECT max(aiwins) AS aiwins\r\n" +
				"FROM toptrumps.overallstats;";

		int AIwins = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				AIwins = rs.getInt("aiwins");

			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		return AIwins;

	}

	public int HumanWins ()
	{
		Statement stmt = null;
		String query = "SELECT max(humanwins) AS humanwins\r\n" +
				"FROM toptrumps.overallstats;";

		int HumanWins = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				HumanWins = rs.getInt("humanwins");

			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		return HumanWins;

	}

	public int AverageDraws ()
	{
		Statement stmt = null;
		String query = "SELECT ROUND (AVG(totaldraws)) AS averagedraws\r\n" +
				"FROM toptrumps.gamestats;";

		int AverageDraws = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				AverageDraws = rs.getInt("averagedraws");

			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		return AverageDraws;
		

	}
	
	public int HighestRounds ()
	{
		Statement stmt = null;
		String query = "Select max(totalrounds) as highestrounds\r\n" +
				"FROM toptrumps.gamestats;";

		int HighestRounds = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {

				HighestRounds = rs.getInt("highestrounds");

			}
		}
		catch (SQLException e ) {
			e.printStackTrace();
			System.err.println("error executing query " + query);
		}

		return HighestRounds;

	}
	
	


	public void updateSQL ()
	{
	}

	public void updateStats ()

	{ 
		Statement stmt = null;
		String query = "select max (gamenumber) as gamenumber from toptrumps.gamestats";

		try {
			stmt = connection.createStatement();
			ResultSet rsid = stmt.executeQuery(query);
			while (rsid.next()) {

				newGame = rsid.getInt("gamenumber") + 1;

			}

			String query1 = "Insert into toptrumps.gamestats Values ("+ newGame +", " + hrw +", " + p2rw + ", " + p3rw + ", " + p4rw + ", " + p5rw +", " + td + ", " + tr + " )";
			PreparedStatement insert = connection.prepareStatement(query1);
			insert.executeUpdate();	

			String query2 = "Insert into toptrumps.overallstats Values (" + newGame +", " + aiwins + ", " + humanwins + ")";
			PreparedStatement insert1 = connection.prepareStatement(query2);
			insert.executeUpdate();	
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query ");

		}

	}
	
	public void disconnect()
	{

		try {
			connection.close();
//			System.out.println("Connection closed");
		}
		catch (SQLException e) {
			e.printStackTrace();

			System.out.println("Connection could not be closed – SQL exception");
		}
	}
	/**
	 * Updates all of the required stats at points of the game
	 * @param human
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param p4
	 * @param totalDraws
	 * @param totalRounds
	 * @param humanWinner
	 * @param AIWinner
	 */
	public void statisticsUpdate(int human, int p1, int p2, int p3, int p4, int totalDraws, int totalRounds, int humanWinner, int AIWinner) {
		hrw += human;
		p2rw += p1;
		p3rw += p2;
		p4rw += p3;
		p5rw += p4;
		td += totalDraws;
		tr += totalRounds;
		humanwins += humanWinner;
		aiwins += AIWinner;
	}

}


