import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long numOne = scanner.nextLong();
    long numTwo = scanner.nextLong();
    scanner.close();

    boolean result = find_tripleInNumOne_and_doubleInNumTwo(numOne, numTwo);
    System.out.println(result);
  }

  /**
  * Search for a series of the same digit, occurring in a row as follows:
  * at least three times in numOne and at least two times in numTwo.
  *
  * @return true if there is at least one such pair. Otherwise, it returns false.
  */
  public static boolean find_tripleInNumOne_and_doubleInNumTwo(long numOne, long numTwo) {

    // Check digits of numOne.
    int min_seriesLength = 3;
    boolean[] triple_in_numOne =
        find_seriesOfSameDigits_forGivenMinLength(min_seriesLength, numOne);

    // Check digits of numTwo.
    min_seriesLength = 2;
    boolean[] double_in_numTwo =
        find_seriesOfSameDigits_forGivenMinLength(min_seriesLength, numTwo);

    boolean result = check_tripleDoublePair_of_sameDigit(triple_in_numOne, double_in_numTwo);
    return result;
  }

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
