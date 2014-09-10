import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

/***********************************
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
 ***********************************/

class User extends Player {

	/**
	 * Scanner to get user input.
	 */
	private Scanner scanner = new Scanner(System.in);

	/**
	 * Flag for player quitting game.
	 */
	private boolean quit = false;

	/**
	 * Constructor.
	 * @param blackjack The parent Blackjack.
	 */
	public User(Blackjack blackjack) {
		super(blackjack);
		this.scanner = new Scanner(System.in);
	}

	/**
	 * Gets and processes user input to make a move.
	 */
	@Override
	public void makeMove() {
		String input;
		System.out.println("Your hand:");
		showHand();
		System.out.println(getPoints() + " points");
		if(getPoints() < MAX) {
			System.out.print("Next move? H = hit, S = stay, Q = quit  ");
			boolean validResponse = false;
			while(!validResponse) {
				input = getNextInputToken();
				System.out.println();
				if(input.equalsIgnoreCase("h") || input.equalsIgnoreCase("hit")) {
					validResponse = true;
					hit();
					System.out.println("You draw: " + hand[hand.length - 1]);
					if(getPoints() > MAX) {
						System.out.println("Player busts!");
					}
					else {
						makeMove();
					}
				}
				else if(input.equalsIgnoreCase("s") || input.equalsIgnoreCase("stay")) {
					validResponse = true;
					stay();
				}
				else if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
					validResponse = true;
					quit();
				}
				// Invalid input.
				else {
					System.out.println("That is not a valid response.");
					System.out.print("Please enter H = hit, S = stay, Q = quit  ");
				}
			}
		}
	}

	/**
	 * Asks the User whether they want to play again, and returns their response as a boolean.
	 * @return The User's response as a boolean.
	 */
	public boolean playAgain() {
		// Flag to track whether a valid response has been received.
		boolean validResponse = false;
		// Return value.
		boolean response = false;
		while(!validResponse && !quit) {
			System.out.print("Play again?  ");
			String input = getNextInputToken();
			if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
				response = true;
				validResponse = true;
			}
			else if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
				response = false;
				validResponse = true;
			}
			else {
				System.out.println("Sorry, that is not a valid response.\n");
			}
		}
		return response;
	}

	/**
	 * Prints a win/loss report.
	 */
	public void report() {
		DecimalFormat formatter = new DecimalFormat("0.##");
		double total = (double)(wins + losses + ties);
		System.out.println("Wins:   " + wins + " (" + formatter.format(100 * wins / total) + "%)");
		System.out.println("Losses: " + losses + " (" + formatter.format(100 * losses / total) + "%)");
		System.out.println("Ties:   " + ties + " (" + formatter.format(100 * ties / total) + "%)");
	}

	/**
	 * Returns the first token from a line of user input, tokenized by whitespace.
	 * @return The first token from a line of user input.
	 */
	private String getNextInputToken() {
		String input = scanner.nextLine();
		Scanner tokenizer = new Scanner(input);
		tokenizer.useDelimiter("\\s");
		String nextToken;
		try {
			nextToken = tokenizer.next();
		}
		catch(NoSuchElementException nsee) {
			nextToken = "";
		}
		return nextToken;
	}

	/**
	 * Quits the game. This is counted as a loss.
	 */
	private void quit() {
		quit = true;
	}

	/**
	 * Returns the quit value.
	 * @return The quit value.
	 */
	public boolean getQuit() {
		return quit;
	}
}