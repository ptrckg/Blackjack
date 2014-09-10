/***********************************
 *    ______________________________
 *     Class: Deck
 *    ______________________________
 *     Fields:
 *        -cards: Card[]
 *    ______________________________
 *     Methods:
 *        +Deck()
 *        +toString(): String
 *        -getRandInt(uupper: int): int
 *        +draw(): Card
 *        -removeIntFromArray(array int[], index: int): int[]
 *    ______________________________
 ***********************************/

 public class Deck {

	/**
	 * Array of the deck's Cards.
	 */
	private Card[] cards = new Card[52];

	/**
	 * Constructor.
	 */
	public Deck() {
		// Iterate through the suit and face values as ints.
		for(int suit = 0; suit < 4; suit++) {
			for(int value = 0; value < 13; value++) {
				cards[(suit * 13) + value] = new Card(value, suit);
			}
		}

		// Shuffle the Deck.
		Card[] newArray = new Card[52];
		// Create an array of index numbers. Randomly select one and use that
		// index number to populate the corresponding index in the new array,
		// and remove that index number from the indexes array.
		int[] indexes = new int[52];
		for(int i = 0; i < indexes.length; i++) {
			indexes[i] = i;
		}
		for(int i = 0; i < 52; i++) {
			int randIndex = getRandInt(indexes.length - 1);
			newArray[i] = cards[indexes[randIndex]];
			indexes = removeIntFromArray(indexes, randIndex);
		}
		cards = newArray;
	}

	/**
	 * Returns a String representation of the Deck.
	 * @return A String representation of the Deck.
	 */
	@Override
	public String toString() {
		String str = "";
		for(Card card: cards) {
			str += "\t" + card + "\n";
		}
		return str;
	}

	/**
	 * Returns a random int in the range with the specified upper bound (inclusive).
	 * Lower bound is 0.
	 * @param upper The upper bound of the range.
	 * @return A random int in the specified range.
	 */
	private int getRandInt(int upper) {
		double randDbl = Math.random();
		int randInt = (int)(randDbl * ((double)(upper + 1)));
		return randInt;
	}

	/**
	 * Removes and returns the first Card from the cards array.
	 * @return The Card that was first in the array.
	 */
	public Card draw() {
		Card[] newArray = new Card[cards.length - 1];
		Card first = cards[0];
		for(int i = 0; i < newArray.length; i++) {
			newArray[i] = cards[i + 1];
		}
		cards = newArray;
		return first;
	}

	/**
	 * Removes the int at a specified index from an array of ints.
	 * @param array The array to be modified.
	 * @param index The index of the int to be removed.
	 * @return Updated array. If the array passed in was of zero length, the original array is
	 *         returned.
	 */
	private int[] removeIntFromArray(int[] array, int index) {
		int[] newArray;
		if(array.length > 0) {
			newArray = new int[array.length - 1];
			for(int i = 0; i < index; i++) {
				newArray[i] = array[i];
			}
			for(int i = index; i < newArray.length; i++) {
				newArray[i] = array[i + 1];
			}
		}
		else {
			newArray = array;
		}
		return newArray;
	}
}