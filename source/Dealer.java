/***********************************
 *    ______________________________
 *     Class: Dealer extends Player
 *    ______________________________
 *     Fields: None
 *    ______________________________
 *     Methods:
 *        +Dealer(Blackjack blackjack)
 *        +makeMove(): void
 *    ______________________________
 ***********************************/

class Dealer extends Player {

	/**
	 * Constructor.
	 * @param blackjack The parent Blackjack.
	 */
	public Dealer(Blackjack blackjack) {
		super(blackjack);
	}

	/**
	 * Makes moves until point total exceeds the minimum.
	 */
	@Override
	public void makeMove() {
		// The minimum number of points needed.
		int min = MIN;
		if(blackjack.getUser().getPoints() > min && blackjack.getUser().getPoints() <= MAX) {
			min = blackjack.getUser().getPoints();
		}
		while(getPoints() <= min) {
			System.out.println("Dealer takes a card.");
			hit();
		}
	}
}