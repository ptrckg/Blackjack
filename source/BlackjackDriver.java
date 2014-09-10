/*
 * a) Project Information
 *    1. Sean McCooey & Patrick Gannon
 *    2. Due date: 12/4/2013
 *    3. Project name: Blackjack
 *    4. Description: Allow user to play multiple games of blackjack against
 *       dealer. Allow for Hit, Stay and Quit play options. Keep track of number
 *       of wins, losses, and ties. Output number of wins, losses, and ties
 *       after user is finished playing.
 *       INPUT:
 *          User has the following input options:
 *             Hit = user draws another card and adds it to hand
 *             Stay = user does not draw another card. Dealer draws if necessary, and then
 *                    the dealer's and user's card totals are compared
 *             Quit = user quits out of current game
 *             playAgain = user decides whether or not to play another game of blackjack
 *       OUTPUT:
 *          Player's hand
 *          Dealer's first card in hand / dealer's hand after Player is finished drawing cards
 *          Player's pointValue total
 *          Dealer's pointValue total
 *          Total number of player's wins/losses/ties
 *          Various win/loss/ask to play again messages
 * b) List of classes:
 *       BlackjackDriver: Driver class. Creates Blackjack and handles looping
 *          for multiple games.
 *       Blackjack: Controls a round of blackjack. Deals initial hand and
 *          allows Players to take their turns.
 *       Player: Abstract superclass for User and Dealer.
 *       Dealer: Player extension representing the dealer.
 *       User: Player extension representing the human player.
 *       Deck: Represents a deco of Cards.
 *       Card: Represents a playing card.
 *
 * c) UML Class diagram:
 *    ______________________________
 *    Class: BlackjackDriver
 *    ______________________________
 *    Fields: None
 *    ______________________________
 *    Methods:
 *       +main(args: String): static void
 *    ______________________________
 ***********************************
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
 ***********************************
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
 ***********************************
 *    ______________________________
 *     Class: Dealer extends Player
 *    ______________________________
 *     Fields: None
 *    ______________________________
 *     Methods:
 *        +Dealer(Blackjack blackjack)
 *        +makeMove(): void
 *    ______________________________
 ***********************************
 *    ______________________________
 *     Class: User extends Player
 *    ______________________________
 *     Fields:
 *        -scanner: Scanner
 *        -quit: boolean
 *    ______________________________
 *     Methods:
 *        +User(Blackjack blackjack)
 *        +makeMove(): void
 *        +playAgain(): boolean
 *        +report(): void
 *        +getQuit(): boolean
 *        -getNextInputToken(): String
 *        -quit(): void
 *    ______________________________
 ***********************************
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
 ***********************************
 *
 * d) Pseudocode:
 *    1. BlackjackDriver.main(String[] args):
 *       a. Create a new Blackjack using zero-argument constructor.
 *       b. Call blackjack.play().
 *       c. Use while loop; repeat based on user.playAgain():
 *          (1) Create a new Blackjack using 2-argument constructor, passing
 *              existing User and Dealer to the constructor.
 *          (2) Call blackjack.play().
 *       d. Once player quits, output results using User.report().
 *    2. Blackjack.play():
 *       a. Deal two Cards each to User and Dealer.
 *       b. Display the Dealer's first Card.
 *       c. Call user.makeMove() to let the play take his/her turn.
 *    3. Blackjack.getUser(): Return the current User.
 *    4. Blackjack.getDealer(): Return the current Dealer.
 *    5. Blackjack.getDeck(): Return the current Deck.
 *    6. Deck.toString(): Return a String representation of the Deck, consisting
 *       of each Card's toString() on a separate line. Add a little whitespace
 *       to make it pretty.
 *    7. Deck.getRandInt(int upper):
 *       a. Generate a random double using Math.random().
 *       b. Multiply by (upper + 1).
 *       c. Return this number cast as int.
 *    8. Deck.draw():
 *       a. Create a new array of Cards with size 1 smaller than current deck.
 *       b. Store first Card in deck as card.
 *       c. Using for loop, copy cards[1] to newArray[0], etc.
 *       d. Save newArray to cards.
 *       e. Return card stored in b.
 *    9. Deck.removeIntFromArray(int[] array, int index):
 *       a. Declare int[] newArray.
 *       b. If input array.length is 0, store array to newArray.
 *       c. Otherwise:
 *          (1) Instantiate newArray as array of int one size smaller than
 *              input array
 *          (2) Use for loop to iterate through int in the input array, up to
 *              the index number (exclusive). Copy values from input array to
 *              same index in newArray.
 *          (3) Use for loop to iterate through ints in the input array, from
 *              the index value (exclusive) through remaining values. Copy
 *              values from input array to the position one lower in newArray.
 *          (4) Return newArray.
 *   10. Card.toString(): Return faceValue + " of " + suit + " (" + pointValue + ")"
 *   11. Card.getFaceValue(): Return the Card's faceValue.
 *   12. Card.getPointValue(): Return the Card's pointValue.
 *   13. Card.getSuit(): Return the Card's suit.
 *   14. Player.clearHand(): Clear the hand, resetting it to Card[0].
 *   15. Player.win(): Increment the wins field.
 *   16. Player.lose(): Increment the losses field.
 *   17. Player.tie(): Increment the ties field.
 *   18. Player.getPoints():
 *       a. Declare and instantiate int score = 0
 *       b. Declare and instantiate int aces = 0
 *       c. Using for loop, iterate through Cards in the hand:
 *          (1) Add the card's pointValue to score (aces counted as 11 at this
 *              point).
 *          (2) If card is an ace, increment aces.
 *       d. Using while loop, adjust score for aces.
 *	         while score > 21 and aces >0:
 *          (1) Subtract 10 from score.
 *          (2) Decrement aces.
 *       e. Return score.
 *   19. Player.hit():
 *       a. Create Card[] newHand, one size larger than current hand.
 *       b. Using for loop, iterate through Cards in hand, assigning each to
 *          the same index in newHand.
 *       c. Populate final position in newHand with Card obtained from
 *          deck.draw().
 *       d. hand = newHand.
 *   20. Player.stay(): Output a message stating that the player stays.
 *   21. Player.makeMove(): Abstract method; determines what move the Player
 *       makes.
 *   22. Player.showHand: Display toString() for each Card in the Hand.  Add a
 *       little whitespace to make it pretty.
 *   23. Dealer.makeMove():
 *       a. Determine minimum score the Dealer must reach.
 *          (1) If User has busted, this is the value of MIN.
 *          (2) If User has not busted, it is the greater of MIN and
 *              User.getPoints().
 *       b. Use while loop.
 *          while(getPoints() < min)
 *             hit()
 *   24. User.makeMove():
 *       a. Display the User's current hand and total points.
 *       b. If player is not at or over 21:
 *          (1) Prompt for next move (hit, stay, quit).
 *          (2) Create boolean validResponse = false as sentinel for while loop.
 *          (3) while(validResponse = false)
 *             (a) Get input using getNextInputToken().
 *             (b) If User chooses to hit:
 *                 1   validResponse = true.
 *                 2   Call Player.hit().
 *                 3   Display the new Card.
 *                 4   If new card puts user over 21, display a message.
 *             (c) Else if User chooses to stay:
 *                 1   validResponse = true.
 *                 2   Call Player.stay().
 *             (d) Else if User chooses to quit:
 *                 1   validResponse = true.
 *                 2   Call Player.quit().
 *             (e) Else assume invalid input. Print message. Loop will repeat.
 *   25. User.playAgain():
 *       a. Create boolean validResponse = false as sentinel for while loop.
 *       b. Create boolean response = false (return value).
 *       c. Use while loop to get user input. while validResponse = false:
 *          (1) Prompt user for input.
 *          (2) Get input using getNextInputToken().
 *          (3) If User chooses "Yes":
 *              (a) response = true;
 *              (b) validResponse = true;
 *          (4) Else if User chooses "No":
 *              (a) response = false;
 *              (b) validResponse = true;
 *          (5) Else assume invalid input. Print message. Loop will repeat.
 *   26. User.report():
 *       a. Create new DecimalFormatter to format output.
 *       b. Calculate total games played by adding wins, losses and ties. Cast
 *          as double.
 *       c. For each (wins, losses, ties), output the value as int and as a
 *          percentage of total games played.
 *   27. User.getNextInputToken():
 *       a. Get a line of input from scanner.
 *       b. Create new Scanner to tokenize input line by whitespace.
 *       c. If first token exists, return first token.
 *       d. If no first token, return empty String.
 *   28. User.quit(): Set the quit field to true.
 *   29. User.getQuit(): Return the value of the quit field.
 *
 * e) Output:
 *       Dealer has: Queen of Diamonds (10)
 *
 *       Player's turn:
 *       Your hand:
 *          King of Spades (10)
 *          Six of Diamonds (6)
 *       16 points
 *       Next move? H = hit, S = stay, Q = quit  h
 *
 *       You draw: Two of Diamonds (2)
 *       Your hand:
 *          King of Spades (10)
 *          Six of Diamonds (6)
 *          Two of Diamonds (2)
 *       18 points
 *       Next move? H = hit, S = stay, Q = quit  s
 *
 *       Player stands.
 *
 *       Dealer takes a card.
 *       Dealer:   Queen of Diamonds (10)
 *          Eight of Hearts (8)
 *          Two of Hearts (2)
 *       20 points
 *
 *       Player:   King of Spades (10)
 *          Six of Diamonds (6)
 *          Two of Diamonds (2)
 *       18 points
 *       Dealer wins!
 *
 *       Play again?  y
 *
 *       Dealer has: Seven of Spades (7)
 *
 *       Player's turn:
 *       Your hand:
 *          Nine of Diamonds (9)
 *          Ten of Spades (10)
 *       19 points
 *       Next move? H = hit, S = stay, Q = quit  r
 *
 *       That is not a valid response.
 *       Please enter H = hit, S = stay, Q = quit  h
 *
 *       You draw: Ace of Hearts (11)
 *       Your hand:
 *          Nine of Diamonds (9)
 *          Ten of Spades (10)
 *          Ace of Hearts (11)
 *       20 points
 *       Next move? H = hit, S = stay, Q = quit  s
 *
 *       Player stands.
 *
 *       Dealer takes a card.
 *       Dealer:   Seven of Spades (7)
 *          Ten of Diamonds (10)
 *          Nine of Spades (9)
 *       26 points
 *       Dealer busts!
 *
 *       Player:   Nine of Diamonds (9)
 *          Ten of Spades (10)
 *          Ace of Hearts (11)
 *       20 points
 *       Player wins!
 *
 *       Play again?  y
 *
 *       Dealer has: Nine of Clubs (9)
 *
 *       Player's turn:
 *       Your hand:
 *          King of Diamonds (10)
 *          Five of Clubs (5)
 *       15 points
 *       Next move? H = hit, S = stay, Q = quit  h
 *
 *       You draw: Four of Hearts (4)
 *       Your hand:
 *          King of Diamonds (10)
 *          Five of Clubs (5)
 *          Four of Hearts (4)
 *       19 points
 *       Next move? H = hit, S = stay, Q = quit  h
 *
 *       You draw: Nine of Hearts (9)
 *       Player busts!
 *
 *       Dealer:   Nine of Clubs (9)
 *          Jack of Clubs (10)
 *       19 points
 *
 *       Player:   King of Diamonds (10)
 *          Five of Clubs (5)
 *          Four of Hearts (4)
 *          Nine of Hearts (9)
 *       28 points
 *       Player busts!
 *       Dealer wins!
 *
 *       Play again?  n
 *       Thanks for playing!
 *       Wins:   1 (33.33%)
 *       Losses: 2 (66.67%)
 *       Ties:   0 (0%)
 */


public class BlackjackDriver {

	/**
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack();
		blackjack.play();
		while(blackjack.getUser().playAgain()) {
			System.out.println();
			blackjack = new Blackjack(blackjack.getUser(), blackjack.getDealer());
			blackjack.play();
		}
		// Output results.
		System.out.println("Thanks for playing!");
		blackjack.getUser().report();
	}

}