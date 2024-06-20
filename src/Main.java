import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        map.put('I', 1); //����, ��������
        map.put('V', 5);
        map.put('X', 10);

        Scanner scanner = new Scanner(System.in);
        String[] stringMassive = scanner.nextLine().split(" ");
        if (stringMassive.length != 3) {
            throw new IOException("���������� ��� ��������.");
        }
        String numberOne = stringMassive[0];
        String symbol = stringMassive[1];
        String numberTwo = stringMassive[2];
        System.out.println(calc(numberOne, symbol, numberTwo));
    }
    public static String calc(String numberOne, String symbol, String numberTwo) throws IOException {

        boolean isFirstNumArab = isArab(numberOne);// 123 -> true :: IV -> false
        boolean isSecondNumArab = isArab(numberTwo);
        int arab1;
        int arab2;

        String[] romanRightNum = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        boolean isRomanRightNum1 = false;
        boolean isRomanRightNum2 = false;
        for (int i = 0; i < romanRightNum.length; i++) {
            if (numberOne.equals(romanRightNum[i])) {
                isRomanRightNum1 = true;
            }
            if (numberTwo.equals(romanRightNum[i])) {
                isRomanRightNum2 = true;
            }
        }
        //boolean isRomanRightNum1 = Arrays.asList(romanRightNum).contains(stringMassive[0]); ������������� ������� � ���� ������
        //boolean isRomanRightNum2 = Arrays.asList(romanRightNum).contains(stringMassive[2]);

        if (!isFirstNumArab & isRomanRightNum1) {
            arab1 = romaToArab(numberOne);
        } else {
            arab1 = Integer.parseInt(numberOne);
        }

        if (!isSecondNumArab & isRomanRightNum2) {
            arab2 = romaToArab(numberTwo);
        } else {
            arab2 = Integer.parseInt(numberTwo);
        }

        if (!isFirstNumArab && isSecondNumArab) {
            throw new IOException("�.�. ������������ ������������ ������ ������� ���������.");

        } else if (isFirstNumArab && !isSecondNumArab) {
            throw new IOException("�.�. ������������ ������������ ������ ������� ���������.");
        }

        if (isOk(arab1) & isOk(arab2)) { // isOk - ������ true, ���� ����� ����������� (1;10);
            int result;
            switch (symbol) {
                case "-":
                    result = (arab1 - arab2);
                    break;
                case "+":
                    result = (arab1 + arab2);
                    break;
                case "*":
                    result = (arab1 * arab2);
                    break;
                case "/":
                    result = (arab1 / arab2);
                    break;
                default:
                    throw new IOException("�.�. ������ �������������� �������� �� ������������� �������" +
                            "( ������ +, -, /, *).");
            }

            boolean b = arab1 >= arab2;

            if (!isFirstNumArab & !isSecondNumArab && !b && symbol.equals("-")) {
                throw new IOException("�.�. � ������� ������� ��� ������������� �����.");
            }

            if (!isFirstNumArab & !isSecondNumArab) {
                return convertNumToRoman(result);
            } else {
                return  String.valueOf(result);
            }

        } else {
            throw new IOException("�.�. ������ �������������� �������� �� ������������� �������.");
        }
    }

    private static int romaToArab(String s) { // s = "VII";
        int result = 0;
        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) { //������� ������� ����� � �����, ������ ��-� i=2;
            char ch = s.charAt(i); // ����� ������ ��� ��������� i �� ������ string;
            int curr = map.get(ch); //����� �������� �� ���� MAP �� ����� ch (I - 1);
            if (curr < prev) {  // ���� ������� ������ �����������, (prev = 0), ��
                result -= curr; // result = result - curr(�������);
            } else {            // (� ������ ������� �� ������), �����
                result += curr; // result = result + curr(�������) (��� ��� 1);
            }
            prev = curr; // ������������, ������ ��� ���� �������� (prev)���������� ��� = 1, � �� 0;
        } // ����� ���� �� ������ i = 0;
        return result;
    }

    private static boolean isArab(String number) {
        boolean isOnlyDigits = true;
        for (int i = 0; i < number.length() && isOnlyDigits; i++) {
            if (!Character.isDigit(number.charAt(i))) {
                isOnlyDigits = false;
            }
        }
        return isOnlyDigits;
    }

    private static String convertNumToRoman(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV",
                "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
                "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII",
                "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI",
                "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV",
                "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
                "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[numArabian];
    }

    private static boolean isOk(int i) {
        return i > 0 && i <= 10;
    }
}