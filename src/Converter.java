import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.lang.Math;

public class Converter {
    static String[] allowedSymbols = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        InputOutput inputOutput = new InputOutput(userInput);
        while(true) {
            int fromBase = inputOutput.askNumericBase("Enter first numeric base to convert from: ");
            HashMap<String, String> parsedNumber = inputOutput.askNumber(allowedSymbols, fromBase);
            int toBase = inputOutput.askNumericBase("Enter second numeric base to convert to: ");

            String integerPart = parsedNumber.get("integer");
            String fractionalPart = parsedNumber.get("fraction");

            long integerPartNumber = covertIntegralToDecNumber(integerPart, fromBase);
            String convertedIntegerPart = covertIntegralDecToString(integerPartNumber, toBase);
            String convertedFractionalPart = "";
            if(fractionalPart.length() != 0) {
                double fractionalPartNumber = covertFractionalToDecNumber(fractionalPart, fromBase);
                convertedFractionalPart = covertFractionalDecToString(fractionalPartNumber, toBase);
            }

            parsedNumber.put("convertedInteger", convertedIntegerPart);
            parsedNumber.put("convertedFraction", convertedFractionalPart);
            inputOutput.showConvertedNumber(parsedNumber);
        }
    }
    public static long covertIntegralToDecNumber( String integer, int base) {
        long integerPart = 0;
        List<String> list = Arrays.asList(allowedSymbols);

        for (int i = integer.length(); i > 0; i--) {
            int symbolValue = list.indexOf(String.valueOf(integer.charAt(i-1)));
            integerPart += symbolValue * (Math.pow(base,integer.length() - i));
        }
//        System.out.println(integerPart);
        return integerPart;
    }
    public static double covertFractionalToDecNumber( String fraction, int base) {
        double fractionalPart = 0;
        List<String> list = Arrays.asList(allowedSymbols);
//        int precision = Math.min(fraction.length(), 3);

        for (int i = 0; i < fraction.length(); i++) {
            int symbolValue = list.indexOf(String.valueOf(fraction.charAt(i)));
            fractionalPart += symbolValue * (Math.pow(base,-i - 1));
        }
//        System.out.println(fractionalPart);
        return fractionalPart;
    }
    public static String covertIntegralDecToString( long integer, int base) {
        String integerConverted = "";
        while(true){
            int r = (int) integer % base;
            integer /= base;
            integerConverted = allowedSymbols[r] + integerConverted;
            if(integer == 0 ) break;
        }
//        System.out.println(integerConverted);
        return integerConverted;
    }
    public static String covertFractionalDecToString( double fraction, int base) {
        String fractionConverted = "";
        int counter = 0;
        while(counter < 3){
            fraction = fraction * base;
            double fractionalPart = fraction % 1;
            int integralPart = (int) (fraction - fractionalPart);
            fractionConverted = fractionConverted + allowedSymbols[integralPart];

            fraction = fractionalPart;
            counter++;
//            int r = (int) integer % base;
//            integer /= base;
//            integerConverted = allowedSymbols[r] + integerConverted;
//            if(integer == 0 ) break;
        }
//        System.out.println(integerConverted);
        return fractionConverted;
    }
}
