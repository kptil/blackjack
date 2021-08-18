import java.util.Scanner;

/**
 * Program implements a simplified version of the game Blackjack. A card is dealt to the
 * player (the user) automatically. The player then chooses to get another card or hold
 * his/her hand. If the player chooses to get another card, another card is dealt and added to
 * the player's hand. If the player's hand equals 21, the player wins. If the player's hand exceeds
 * 21, the player loses. If neither is true, play continues. If the user chooses to hold his/her
 * hand, the dealer is dealt the equivalent of two cards at once. The dealer's hand is then
 * evaluated for equalling or exceeding 21. If neither is true, both hands are below 21, and
 * the highest hand wins.
 *
 * Citations:
 * Line 59: I looked up a way to convert an integer to a string and used it in the printCard() method.
 * Java Point, "Java Convert int to String," Accessed 4 February 2021, https://www.javatpoint.com/java-int-to-string
 * Line 105: I looked up a way to cast and int to a double and used it in the printStatistics() method.
 * StackOverflow, "How to remove decimal values from a value of type 'double' in Java," Answered by C-Otto, 27 September 2013.
 * https://stackoverflow.com/questions/19060406/how-to-remove-decimal-values-from-a-value-of-type-double-in-java/19060439
 *
 * @author kptil
 * @version 1.0
 *
 */

public class Blackjack {

    /**
     * This method takes the card dealt to the player and the player's current hand and adds the
     * new card to the player's hand. If the new card is a face card, numbered 11-13, 10 is added
     * to the player's hand rather than the actual value. The program returns the updated hand.
     *
     * @param playerCard a random number between 1 and 13, card dealt to the player
     * @param playerHand the sum of all cards dealt to the player
     * @return an int representing the updated playerHand
     */
    public static int calculateHand(int playerCard, int playerHand) {
        if (playerCard < 11) {
            playerHand = playerHand + playerCard;
        } else {
            playerHand = playerHand + 10;
        }

        return playerHand;
    }

    /**
     * This method takes the card dealt to the player, an integer, and assigns the proper name to
     * the card if appropriate. It then prints the name of the card for the player. Please see the
     * citation above for line 63 (Integer.toString()).
     *
     * @param playerCard a random number between 1 and 13, card dealt to the player
     */
    public static void printCard(int playerCard) {
        String cardName = "";

        if (playerCard == 1) {
            cardName = "ACE";
        } else if ((playerCard > 1) && (playerCard < 11)) {
            cardName = Integer.toString(playerCard);
        } else if (playerCard == 11) {
            cardName = "JACK";
        } else if (playerCard == 12) {
            cardName = "QUEEN";
        } else if (playerCard == 13) {
            cardName = "KING";
        } else {
            System.out.println("Oops! Random number greater than 13?");
        }

        System.out.println("Your card is a " + cardName + "!");
    }

    /**
     * This method prints out the menu of possible choices for the player and their respective
     * integer values.
     */
    public static void printMenu() {
        System.out.println("1. Get another card");
        System.out.println("2. Hold hand");
        System.out.println("3. Print statistics");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Choose an option: ");
    }

    /**
     * This method prints a list of game statistics based on the number of player wins, the
     * number of dealer wins, and the number of tied games. The method calculates the total
     * number of games from these values, rather than taking the gameNumber variable in main
     * as an argument. In line 110, casting is used to ensure totalGames prints as an integer
     * but can be used as a double when calculating the percentage of player wins. Please see
     * above for a citation.
     *
     * @param numPlayerWins int incremented by 1 for each game player wins
     * @param numDealerWins int incremented by 1 for each game dealer wins
     * @param numTieGames int incremented by 1 for each game that's tied
     */
    public static void printStatistics(int numPlayerWins, int numDealerWins, int numTieGames) {
        System.out.println("Number of Player wins: " + numPlayerWins);
        System.out.println("Number of Dealer wins: " + numDealerWins);
        System.out.println("Number of tie games: " + numTieGames);

        int totalGames = numPlayerWins + numDealerWins + numTieGames;
        System.out.println("Total # of games played is: " + totalGames);
        double totalGamesDouble = (double) totalGames;
        double percentPlayerWins = (numPlayerWins / totalGamesDouble) * 100;
        System.out.println("Percentage of Player wins: " + percentPlayerWins + "%");
        System.out.println();
    }

    /**
     * In the main method, a while loop continually starts new games of Blackjack until the user
     * inputs 4 to exit. The program uses P1Random class to assign a random number as the player's
     * first card automatically. It then prints the menu, gets user input, and executes a series
     * of If statements which execute based on the user input and the value of the player's hand.
     *
     * @param args
     */
    public static void main(String[] args) {
        int gameNumber = 0;
        int playerInput = 0;
        int playerHand;
        int playerCard;
        int dealerCard;
        int numPlayerWins = 0;
        int numDealerWins = 0;
        int numTieGames = 0;

        P1Random rng = new P1Random();
        Scanner scnr = new Scanner(System.in);

        /*
            Initiates a while loop that keeps starting a new game of Blackjack until the user
            enters 4 to exit. gameNumber is incremented with each iteration.
         */
        while (playerInput != 4) {
            playerHand = 0;
            gameNumber++;
            System.out.println("START GAME #" + gameNumber);
            System.out.println();

            /*  Assigns a random number between 1 and 13 to playerCard, calls the
                printCard() method, and updates playerHand.
             */
            playerCard = rng.nextInt(13) + 1;
            printCard(playerCard);

            playerHand = calculateHand(playerCard, playerHand);
            System.out.println("Your hand is: " + playerHand);
            System.out.println();

            /*
                Prints the menu and gets user input once, and then continues to print the menu as
                long as user input does not equal 2 (Hold Hand), which ends the game. An input of
                4 will result in a break statement, and then the while loop will exit.
             */
            do {
                printMenu();
                playerInput = scnr.nextInt();
                System.out.println();

                /*
                    Deals the player a new card and updates the player's hand. If the player's
                    hand is equal to or above 21, the game ends and the appropriate counter,
                    numPlayerWins or numDealerWins, is incremented.
                 */
                if (playerInput == 1) {
                    playerCard = rng.nextInt(13) + 1;
                    printCard(playerCard);
                    playerHand = calculateHand(playerCard, playerHand);
                    System.out.println("Your hand is: " + playerHand);
                    System.out.println();

                    if (playerHand > 21) {
                        System.out.println("You exceeded 21! You lose.");
                        System.out.println();
                        numDealerWins++;
                        break;
                    }
                    if (playerHand == 21) {
                        System.out.println("BLACKJACK! You win!");
                        System.out.println();
                        numPlayerWins++;
                        break;
                    }
                }
                /*
                    Deals a random number between 16 and 26 to the dealer and compares the
                    player's hand to the dealer's card to establish who wins and increment the
                    appropriate counter. If the hands are equal, the game is a tie. We know the
                    player's hand is less than 21, because if it wasn't the above if statements
                    in (1) would have ended the game.
                 */
                else if (playerInput == 2) {
                    dealerCard = rng.nextInt(11) + 16;
                    System.out.println("Dealer's hand: " + dealerCard);
                    System.out.println("Your hand is: " + playerHand);
                    System.out.println();

                    //Nested If statements evaluating the game winner
                    if ((dealerCard == 21)) {
                        System.out.println("Dealer wins!");
                        System.out.println();
                        numDealerWins++;
                    }
                    else if (dealerCard > 21) {
                        System.out.println("You win!");
                        System.out.println();
                        numPlayerWins++;
                    }
                    /*
                        Compares dealerCard and playerHand values if both are less than 21.
                        The highest value wins.
                     */
                    else {
                        if (dealerCard > playerHand) {
                            System.out.println("Dealer wins!");
                            System.out.println();
                            numDealerWins++;
                        }
                        else if (dealerCard < playerHand) {
                            System.out.println("You win!");
                            System.out.println();
                            numPlayerWins++;
                        }
                        else {
                            System.out.println("It's a tie! No one wins!");
                            System.out.println();
                            numTieGames++;
                        }
                    }
                }
                //Calls the printStatistics method if user input is 3
                else if (playerInput == 3) {
                    printStatistics(numPlayerWins, numDealerWins, numTieGames);
                }
                //Ends the program if user input is 4
                else if (playerInput == 4) {
                    break;
                }
                //Prints an error message if the user enters a number outside the range 1-4
                else {
                    System.out.println("Invalid input!");
                    System.out.println("Please enter an integer value between 1 and 4.");
                    System.out.println();
                }
            } while ((playerInput != 2));

        }
    }
}
