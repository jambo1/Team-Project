package commandline;

import java.sql.*;

import commandline.Interaction;

public class Interaction {

	private Connection connection =null;
	private int newGame;
	private int newGame1;
	

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
	
	

	public void updateStats ()
	{ 
		Statement stmt = null;
		String query = "select max (gamenumber) as gamenumber from toptrumps.gamestats";

		try {
			stmt = connection.createStatement();
			ResultSet rsid = stmt.executeQuery(query);
			while (rsid.next()) {

				newGame = rsid.getInt("gamenumber") + 1;
				newGame1 = rsid.getInt("totalgames") + 1;

			}

			String query1 = "Insert into toptrumps.gamestats Values ("+ newGame +", " + hrw +", " + p1rw + ", " + p2rw + ", " + p3rw + ", " + p4rw +", " + td + ", " + tr + " )";
			PreparedStatement insert = connection.prepareStatement(query1);
			insert.executeUpdate();	

			String query2 = "Insert into toptrumps.overallstats Values (" + newGame1 +", " + aiwins + ", " + humanwins + ")";
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

}
