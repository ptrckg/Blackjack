/*
 * Represents a playing card.
 ***********************************
 *    ______________________________
 *     Class: Card
 *    ______________________________
 *     Fields:
 *        -faceValue: String
 *        -suit: String
 *        -pointValue: int
 *        -faceValues: final static String[]
 *        -suits:  final static String[]
 *        -pointValues: final static int[]
 *    ______________________________
 *     Methods:
 *        +Card(faceValue: int, suit: int)
 *        +toString(): String
 *        +getFaceValue(): String
 *        +getPointValue(): int
 *        +getSuit(): String
 *    ______________________________
 ***********************************/

public class Card {

	/**
	 * The Card's face value.
	 */
	private String faceValue;

	/**
	 * The Card's suit.
	 */
	private String suit;

	/**
	 * The Card's point value. Aces  count as 11.
	 */
	private int pointValue;

	/**
	 * Used to populate faceValue field.
	 */
	final private static String[] faceValues = { "Two", "Three", "Four", "Five", "Six", "Seven",
		"Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace" };

	/**
	 * Used to populate suit field.
	 */
	final private static String[] suits = { "Spades", "Hearts", "Clubs", "Diamonds" };

	/**
	 * Used to populate pointValue field.
	 */
	final private static int[] pointValues = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

	/**
	 * Constructor.
	 * @param faceValue The new Card's face value as an int. This should be a
	 *                  value from 0 to 12.
	 * @param suit The new Card's suit as an int. This should be a value
	 *             from 0 to 3.
	 */
	public Card(int faceValue, int suit) {
		this.suit = suits[suit];
		this.faceValue = faceValues[faceValue];
		this.pointValue = pointValues[faceValue];
	}

	/**
	 * Returns a String representation of the Card.
	 * @return A String representation of the Card.
	 */
	@Override
	public String toString() {
		return faceValue + " of " + suit + " (" + pointValue + ")";
		// return faceValue + suit + "(" + pointValue + ")";
	}

	/**
	 * Returns the String face value for the Card.
	 * @return The face value as a String.
	 */
	public String getFaceValue() {
		return faceValue;
	}

	/**
	 * Returns the point value for the Card.
	 * @return The point value as an int.
	 */
	public int getPointValue() {
		return pointValue;
	}

	/**
	 * Returns the String suit name for the Card.
	 * @return The suit name as a String.
	 */
	public String getSuit() {
		return suit;
	}

}