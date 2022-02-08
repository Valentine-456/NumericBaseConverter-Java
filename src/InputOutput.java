import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class InputOutput {
    Scanner scanner;
    InputOutput(Scanner scanner) {
        this.scanner = scanner;
    }
    public int askNumericBase(String text) {
        while(true) {
                System.out.println(text);
                int base = scanner.nextInt();
                if(2 <= base && base <= 36){
                    return base;
                } else {
                    System.out.println("Numeric base must be in bonds from 2 to 36 included.");
                }
        }
    }
    public HashMap<String, String> askNumber(String[] allowedSymbols ,int base) {
        outerloop: while(true) {
            System.out.println("Enter the number you want to convert: ");
            String numberString = scanner.next();
            String isNegative = "";
            String hasPoint = "";
            String fractionString = "";
            if(numberString.charAt(0) == '-') {
                isNegative = "-";
                numberString = numberString.substring(1);
            }
            if(numberString.contains(".")) {
                String[] numberStrSplit = numberString.split("\\.");
                System.out.println(numberStrSplit[0]);
                hasPoint = ".";
                numberString = numberStrSplit[0];
                fractionString = numberStrSplit[1];

                for (int i = 0; i < fractionString.length(); i++) {
                    String symbol = String.valueOf(fractionString.charAt(i));
                    int symbolIndex = Arrays.asList(allowedSymbols).indexOf(symbol);
                    if(symbolIndex < 0 || symbolIndex > base) {
                        System.out.println("Entered number contains forbidden symbols in numeric base" + base + ". Try again.");
                        continue outerloop;
                    }
                }
            }
            for (int i = 0; i < numberString.length(); i++) {
                String symbol = String.valueOf(numberString.charAt(i));
                int symbolIndex = Arrays.asList(allowedSymbols).indexOf(symbol);
                if(symbolIndex < 0 || symbolIndex > base) {
                    System.out.println("Entered number contains forbidden symbols in numeric base" + base + ". Try again.");
                    continue outerloop;
                }
            }
            HashMap<String, String> parsedNumber = new HashMap<String, String>();
            parsedNumber.put("isNegative", isNegative);
            parsedNumber.put("integer", numberString);
            parsedNumber.put("hasPoint", hasPoint);
            parsedNumber.put("fraction", fractionString);
            return parsedNumber;
        }
    }
    public void showConvertedNumber(HashMap<String, String> map) {
        String integerPart = map.get("convertedInteger");
        String isNegative = map.get("isNegative");
        String hasPoint = map.get("hasPoint");
        String fractionalPart = map.get("convertedFraction");
        System.out.println("Your converted number is: " + isNegative + integerPart + hasPoint + fractionalPart);
    }
}
