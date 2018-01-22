package commandline;

/**
 * Cards class containing the description and relevant attributes of the cards.
 * Getter methods to return each of the attributes and name. No setter methods are included as once the cards
 * are created from the file they will not be altered.
 * @author Jamie
 *
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
	 * @param Desc
	 * @param Siz
	 * @param Spe
	 * @param Ran
	 * @param Fir
	 * @param Car
	 */
	public Cards(String Desc, int Siz, int Spe, int Ran, int Fir, int Car){
		Description = Desc;
		Size = Siz;
		Speed = Spe;
		Range = Ran;
		Firepower = Fir;
		Cargo = Car;

	}

	// Default constructor to enable hand-arrays in other classes to be of type Cards
	public Cards(){}

	/**
	 * Getter methods for each of the attributes
	 * @return
	 */
	public String getDescription() {return Description;}
	public int getSize() {return Size;}
	public int getSpeed() {return Speed;}
	public int getRange() {return Range;}
	public int getFirepower() {return Firepower;}
	public int getCargo() {return Cargo;}

}
