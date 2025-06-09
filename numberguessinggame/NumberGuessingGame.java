package numberguessinggame;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(NumberGuessingGame.class.getName());
        logger.info("🎮 Welcome to the Number Guessing Game!");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsPlayed = 0;
        int roundsWon = 0;

        boolean playAgain = true;
        while (playAgain) {
            boolean hasGuessedCorrectly = playRound(scanner, random, logger);
            roundsPlayed++;
            if (hasGuessedCorrectly) {
                roundsWon++;
            }
            playAgain = askToPlayAgain(scanner, logger);
        }

        displayGameSummary(logger, roundsPlayed, roundsWon);
        scanner.close();
    }

    private static boolean playRound(Scanner scanner, Random random, Logger logger) {
        int maxAttempts = 7;
        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int attemptsLeft = maxAttempts;

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info(String.format("%n🔢 I'm thinking of a number between %d and %d.", lowerBound, upperBound));
        }
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info(String.format("💡 You have %d attempts to guess it.", maxAttempts));
        }

        while (attemptsLeft > 0) {
            int guess = getUserGuess(scanner, logger);
            attemptsLeft--;

            if (guess == targetNumber) {
                logger.info("🎉 Correct! You guessed the number.");
                return true;
            } else if (guess < targetNumber) {
                if (logger.isLoggable(java.util.logging.Level.INFO)) {
                    logger.info(String.format("⬆️ Too low! Attempts left: %d", attemptsLeft));
                }
            } else {
                if (logger.isLoggable(java.util.logging.Level.INFO)) {
                    logger.info(String.format("⬇️ Too high! Attempts left: %d", attemptsLeft));
                }
            }
        }

        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info(String.format("❌ You've used all attempts! The number was: %d", targetNumber));
        }
        return false;
    }

    private static int getUserGuess(Scanner scanner, Logger logger) {
        logger.info("👉 Enter your guess: ");
        while (!scanner.hasNextInt()) {
            logger.warning("❌ Invalid input. Please enter a number.");
            scanner.next(); // consume invalid input
        }
        return scanner.nextInt();
    }

    private static boolean askToPlayAgain(Scanner scanner, Logger logger) {
        logger.info("\n🔁 Do you want to play another round? (yes/no): ");
        String response = scanner.next().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    private static void displayGameSummary(Logger logger, int roundsPlayed, int roundsWon) {
        logger.info("\n🏁 Game Over!");
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info(String.format("📊 Rounds Played: %d", roundsPlayed));
        }
        if (logger.isLoggable(java.util.logging.Level.INFO)) {
            logger.info(String.format("🏆 Rounds Won: %d", roundsWon));
        }
        logger.info("🧠 Thanks for playing!");
    }
}

