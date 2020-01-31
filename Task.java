import java.math.BigDecimal;
import java.util.Scanner;

public class Task {
  private static final String[] ones = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
  private static final String[] tensSeries = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
  private static final String[] tens = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
  private static final String hundred = "hundred";
  private static final String[] groups = {"thousand", "million", "billion"};

  public static String digitsToWordsConverter(BigDecimal inputValue) {
    String amount = inputValue.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
    String[] amounts = amount.split("\\.");

    String decimalAmount = amounts[1];
    StringBuilder words = new StringBuilder();

    if(amounts[0].length() > 0 && Integer.parseInt(amounts[0]) > 0) {
      String dollarAmount = "";
      switch(amounts[0].length() % 3) {
        case 0:
          dollarAmount = amounts[0];
          break;
        case 1:
          dollarAmount = "00" + amounts[0];
          break;
        case 2:
          dollarAmount = "0" + amounts[0];
          break;
      }

      for(int i = 0; i < dollarAmount.length(); i = i + 3) {
        int groupValue = Integer.parseInt(dollarAmount.substring(i, i + 3));
        if(groupValue > 0) {
          //if string builder is not empty
          if(words.length() > 0) {
            words.append(", ");
          }
          String tensOnesPlace = dollarAmount.substring(i + 1, i + 3);
          String hundredthPlace = dollarAmount.substring(i, i + 1);
          int valueAtHundredthPlace = Integer.parseInt(hundredthPlace);

          if(valueAtHundredthPlace != 0) {
            words.append(ones[valueAtHundredthPlace - 1]).append(" ").append(hundred).append(" ");
          }
          words.append(getWords(tensOnesPlace));

          int groupNumber = (dollarAmount.length() - 1 - i) / 3;
          if(groupNumber != 0) {
            words.append(groups[groupNumber - 1]);
          }
        }
      }
      if(words.length()>0 && words.charAt(words.length()-1)!=' ') {
        words.append(" ");
      }
      words.append("DOLLAR");
      if(Integer.parseInt(dollarAmount) > 1) {
        words.append("S");
      }
    }

    if(decimalAmount.length() > 0 && Integer.parseInt(decimalAmount) > 0) {
      //if string builder is not empty
      if(words.length() > 0) {
        words.append(" AND ");
      }

//      if(decimalAmount.length() == 1) {
//        words.append(tens[Integer.parseInt(decimalAmount)-1]).append("");
//      } else {
//        words.append(getWords(decimalAmount));
//      }

      if(decimalAmount.length() == 1) {
        decimalAmount = decimalAmount + "0";
      }
      words.append(getWords(decimalAmount));
      words.append("CENT");
      if(Integer.parseInt(decimalAmount) > 1) {
        words.append("S");
      }
    } else {
      if(words.length() > 0) {
        words.append(" ");
      }
      words.append("EVEN");
    }
    return words.toString();

  }

  private static int getDigit(String str, int position) {
    return Character.getNumericValue(str.charAt(position));
  }

  private static StringBuilder getWords(String str) {
    StringBuilder word = new StringBuilder();
    if(str.length() == 2) {
      if(getDigit(str, 0) != 1) {
        if(getDigit(str, 0) != 0) {
          word.append(tens[getDigit(str, 0) - 1]).append(" ");
        }
        if(getDigit(str, 1) != 0) {
          word.append(ones[getDigit(str, 1) - 1]).append(" ");
        }
      } else {
        word.append(tensSeries[getDigit(str, 1)]).append(" ");
      }
    } else if(str.length() == 1 && Integer.parseInt(str) != 0) {
      word.append(ones[Integer.parseInt(str) - 1]).append(" ");
    }
    return word;
  }

  public static void main(String[] arg) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter amount : ");

    String value = scanner.nextLine();
    try {
      System.out.println(digitsToWordsConverter(new BigDecimal(value)));
    } catch(NumberFormatException e) {
      System.out.println("Invalid input");
    }
  }
}
