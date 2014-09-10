/***********************************
 *    ______________________________
 *     Class: abstract Player
 *    ______________________________
 *     Fields:
 *        +MAX = 21: final static int
 *        +MIN = 17: final static int
 *        #hand: Card[]
 *        #wins: int
 *        #losses: int
 *        #ties: int
 *        #blackjack: Blackjack
 *    ______________________________
 *     Methods:
 *        +Player(blackjack: Blackjack)
 *        +clearHand(): void
 *        +win(): void
 *        +lose(): void
 *        +tie(): void
 *        +getPoints(): int
 *        +hit(): void
 *        +stay(): void
 *        +makeMove(): abstract void
 *        +showHand(): void
 *    ______________________________
 ***********************************/

 public abstract class Player {

	/**
	 * The maximum point value a hand can have without going bust.
	 */
	public final static int MAX = 21;

	/**
	 * The minimum point value at which the Dealer can stay.
	 */
	public final static int MIN = 17;

	/**
	 * The Player's hand.
	 */
	protected Card[] hand;

	/**
	 * Total number of games won.
	 */
	protected int wins;

	/**
	 * Total number of games lost.
	 */
	protected int losses;

	/**
	 * Total number of games tied.
	 */
	protected int ties;

	/**
	 * Reference back to the parent Blackjack.
	 */
	protected Blackjack blackjack;

	/**
	 * Constructor. Creates a new Player with a clean slate of wins, losses, ties and Cards.
	 * @param blackjack The parent Blackjack.
	 */
	public Player(Blackjack blackjack) {
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
		this.blackjack = blackjack;
		this.hand = new Card[0];
	}

	/**
	 * Clears the Player's hand.
	 */
	public void clearHand() {
		hand = new Card[0];
	}

	/**
	 * Increments the wins counter.
	 */
	public void win() {
		wins++;
	}

	/**
	 * Increments the losses counter.
	 */
	public void lose() {
		losses++;
	}

	/**
	 * Increments the ties counter.
	 */
	public void tie() {
		ties++;
	}

	/**
	 * Returns the current value of the Player's hand.
	 * @return The current value of the Player's hand.
	 */
	public int getPoints() {
		// Score will be sum of individual Cards' point values.
		int score = 0;
		// Keep a count of aces in the hand.
		int aces = 0;
		for(Card card : hand) {
			int pointValue = card.getPointValue();
			score += pointValue;
			// Increment ace counter if card is an ace.
			if(pointValue == 11) {
				aces++;
			}
		}

		// If score is too high and there are aces in the hand, count aces as 1 point until you run
		// out of aces or get below the max.
		while(score > MAX && aces > 0) {
			score -= 10;
			aces--;
		}
		return score;
	}

	/**
	 * Takes a Card from the deck and adds it to the Player's hand.
	 */
	public void hit() {
		Card[] newHand = new Card[hand.length + 1];
		for(int i = 0; i < hand.length; i++) {
			newHand[i] = hand[i];
		}
		newHand[hand.length] = blackjack.getDeck().draw();
		hand = newHand;
	}

	/**
	 * Stays. Basically just a placeholder, but also prints a message.
	 */
	public void stay() {
		System.out.println("Player stands.");
	}

	/**
	 * Determine whether to hit, stay or quit.
	 */
	public abstract void makeMove();

	/**
	 * Displays the current hand.
	 */
	public void showHand() {
		for(Card card : hand) {
			System.out.println("\t" + card);
		}
	}
}