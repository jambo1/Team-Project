package commandline;

/**
 * Cards class containing the description and relevant attributes of the cards.
 * Getter methods to return each of the attributes and name. No setter methods are included as once the cards
 * are created from the file they will not be altered.
 */

public class Cards {
	/*
	 * Instance variables for the card
	 */
	private String Description;
	private int Size;
	private int Speed;
	private int Range;
	private int Firepower;
	private int Cargo;

	/**
	 * Constructor taking each of the instance variables as input
	 */
	public Cards(String Desc, int Siz, int Spe, int Ran, int Fir, int Car){
		Description = Desc;
		Size = Siz;
		Speed = Spe;
		Range = Ran;
		Firepower = Fir;
		Cargo = Car;

	}

	 //Getter methods for each of the attributes
	public String getDescription() {return Description;}
	public int getSize() {return Size;}
	public int getSpeed() {return Speed;}
	public int getRange() {return Range;}
	public int getFirepower() {return Firepower;}
	public int getCargo() {return Cargo;}

}
