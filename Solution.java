import java.util.Scanner;

public class SolutionTripleDouble {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long numOne = scanner.nextLong();
    long numTwo = scanner.nextLong();
    scanner.close();

    boolean result = find_tripleInNumOne_and_doubleInNumTwo(numOne, numTwo);
    System.out.println(result);
  }

  /**
   * Searches for a series of the same digit, occurring in a row, as follows: 
   * at least three times in numOne and at least two times in numTwo.
   * Example: numOne = 1299999, numTwo = 4998. Digit '9' fulfills the criteria.
   *
   * @return 'true', if there is at least one such triple-double pair. Otherwise, 'false'.
   */
  public static boolean find_tripleInNumOne_and_doubleInNumTwo(long numOne, long numTwo) {

    // Check digits of numOne.
    int min_seriesLength = 3;
    boolean[] triple_in_numOne = find_seriesOfSameDigits_forGivenMinLength(min_seriesLength, numOne);

    // Check digits of numTwo.
    min_seriesLength = 2;
    boolean[] double_in_numTwo = find_seriesOfSameDigits_forGivenMinLength(min_seriesLength, numTwo);

    boolean result = check_tripleDoublePair_of_sameDigit(triple_in_numOne, double_in_numTwo);
    return result;
  }

  /**
   * Searches for a series of the same digit, occurring in a row, as per the given min length.
   * Records the results for each digit from 0 to 9 in an array. If the digit occurs at least 
   * min length, its corresponding element in the array is set to 'true'.
   *
   * @return a boolean array, containing the results for each digit.
   */
  private static boolean[] find_seriesOfSameDigits_forGivenMinLength(
      int min_seriesLength, long num) {
    boolean[] storeSeries = new boolean[10];

    while (num > 0) {

      // Get next rightmost digit.
      long digit = num % 10;
      num = num / 10;

      // Check for same digits in a row.
      int total_sameDigits_inSeries = 1;
      while ((num % 10) == digit) {
        total_sameDigits_inSeries++;
        num = num / 10;
      }

      if (total_sameDigits_inSeries >= min_seriesLength) {
        storeSeries[(int) digit] = true;
      }
    }

    return storeSeries;
  }

  /**
   * Compares the results of the boolean arrays for numOne and numTwo.
   *
   * @return 'true', if at least one triple-double pair is found. Otherwise, 'false'.
   */
  private static boolean check_tripleDoublePair_of_sameDigit(
      boolean[] triple_in_numOne, boolean[] double_in_numTwo) {

    for (int i = 0; i < 10; i++) {
      if (triple_in_numOne[i] == true && double_in_numTwo[i] == true) {
        return true;
      }
    }
    return false;
  }
}
