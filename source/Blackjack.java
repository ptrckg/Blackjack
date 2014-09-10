/***********************************
 *    ______________________________
 *     Class: Blackjack
 *    ______________________________
 *     Fields:
 *        -deck: Deck
 *        -user: User
 *        -dealer: Dealer
 *    ______________________________
 *     Methods:
 *        +Blackjack()
 *        +Blackjack(user: User, dealer: Dealer)
 *        +play(): void
 *        +getUser(): User
 *        +getDealer(): Dealer
 *        +getDeck(): Deck
 *    ______________________________
 ***********************************/

 public class Blackjack {

	/**
	 * The deck from which Cards are drawn.
	 */
	private Deck deck;

	/**
	 * The human player.
	 */
	private User user;

	/**
	 * The computer player.
	 */
	private Dealer dealer;

	/**
	 * Zero-argument constructor. Creates new deck, user and dealer. Used for the first hand.
	 */
	public Blackjack() {
		this.deck = new Deck();
		this.user = new User(this);
		this.dealer = new Dealer(this);
	}

	/**
	 * Constructor which reuses existing User and Dealer. Used for subsequent hands so that players'
	 * scores can be maintained.
	 * @param user The existing User.
	 * @param dealer The existing Dealer.
	 */
	public Blackjack(User user, Dealer dealer) {
		this.deck = new Deck();
		this.user = user;
		this.dealer = dealer;
		user.clearHand();
		dealer.clearHand();
	}

	/**
	 * Plays a hand of blackjack.
	 */
	public void play() {
		// Each Player gets two Cards to start.
		user.hit();
		dealer.hit();
		user.hit();
		dealer.hit();

		// Show the dealer's first card.
		System.out.println("Dealer has: " + dealer.hand[0]);

		// User's turn.
		System.out.println("\nPlayer's turn:");
		user.makeMove();

		System.out.println();

		// Dealer's turn. Skip this if player has opted to quit.
		if(!user.getQuit()) {
			dealer.makeMove();
		}

		// Figure out who won.
		// If player quit, it counts as a loss.
		if(user.getQuit()) {
			user.lose();
			dealer.win();
			System.out.println("Player quits.");
		}
		// Only output hands and scores if player has NOT quit.
		else {
			System.out.print("Dealer:");
			dealer.showHand();
			System.out.println(dealer.getPoints() + " points");
			if(dealer.getPoints() > Player.MAX) {
				System.out.println("Dealer busts!");
			}
			System.out.println();
			System.out.print("Player:");
			user.showHand();
			System.out.println(user.getPoints() + " points");
			if(user.getPoints() > Player.MAX) {
				System.out.println("Player busts!");
			}

			// If both bust, both lose.
			if(user.getPoints() > Player.MAX && dealer.getPoints() > Player.MAX) {
				System.out.println("Dealer and player both bust!");
				user.lose();
				dealer.lose();
			}
			// If player busts, they lose.
			else if(user.getPoints() > Player.MAX) {
				System.out.println("Dealer wins!");
				user.lose();
				dealer.win();
			}
			// If dealer busts, it loses.
			else if(dealer.getPoints() > Player.MAX) {
				System.out.println("Player wins!");
				user.win();
				dealer.lose();
			}
			// No busts, so compare point values.
			else if(user.getPoints() > dealer.getPoints()) {
				System.out.println("Player wins!");
				user.win();
				dealer.lose();
			}
			else if(user.getPoints() < dealer.getPoints()) {
				System.out.println("Dealer wins!");
				user.lose();
				dealer.win();
			}
			// Tie.
			else if(user.getPoints() == dealer.getPoints()) {
				System.out.println("It's a tie.");
				user.tie();
				dealer.tie();
			}
			System.out.println();
		}
	}

	/**
	 * Returns the User.
	 * @return The User.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Returns the Dealer.
	 * @return The Dealer.
	 */
	public Dealer getDealer() {
		return dealer;
	}

	/**
	 * Returns the Deck.
	 * @return The Deck.
	 */
	public Deck getDeck() {
		return deck;
	}

}