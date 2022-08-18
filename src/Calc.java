import java.io.IOException;
import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Expression expr = new Expression();

        Scanner scan = new Scanner(System.in);
        expr.keyIn = scan.nextLine();
        scan.close();
        expr.keyInGood = expr.keyIn.replaceAll("\\s{2,}"," ");
        expr.stringsArray = expr.keyInGood.trim().split(" ");
        if (expr.stringsArray.length < 3) {
                System.out.println("Строка не является математической операцией");
            return;
        }
        if (expr.stringsArray.length > 3) {
            System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return;
        }

        expr.strOp1 = expr.stringsArray[0];
        expr.operation = expr.stringsArray[1];
        expr.strOp2 = expr.stringsArray[2];

        expr.opStrIs(expr.strOp1);
        expr.str1IsLetter = expr.str2IsLetter;
        expr.str1IsDigit = expr.str2IsDigit;
        expr.opStrIs(expr.strOp2);

        if (!(expr.str1IsLetter & expr.str2IsLetter) & !(expr.str1IsDigit & expr.str2IsDigit)) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Калькулятор может работать одновременно только с натуральными целыми арабскими или только с римскими цифрами.");
            }
        }
        if (expr.str1IsLetter & expr.str2IsLetter) {
            expr.op1 = expr.checkRom(expr.strOp1);
            expr.op2 = expr.checkRom(expr.strOp2);
            if (expr.op1 > 0 & expr.op2 > 0) {
                expr.result = expr.calculate(expr.op1, expr.op2);
                if (expr.result == -11) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Введите допустимый знак арифметического действия. " +
                                "Калькулятор умеет выполнять следующие операции с двумя числами: " +
                                "сложение (a + b), вычитание (a - b), умножение (a * b), деление (a / b).");
                    }
                } else if ((expr.result < 0)) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Результатом работы калькулятора с римскими числами может быть только положительное число.");
                    }
                } else if (expr.result > 0) {
                    System.out.println(expr.RomanCharSet[expr.result]);
                }
            }
        } else if (expr.str1IsDigit & expr.str2IsDigit) {
            expr.op1 = expr.checkArab(expr.strOp1);
            expr.op2 = expr.checkArab(expr.strOp2);
            if (expr.op1 > 0 & expr.op2 > 0) {
                expr.result = expr.calculate(expr.op1, expr.op2);
                if (expr.result == -11) {
                    try {
                        throw new IOException();
                    } catch (IOException e) {
                        System.out.println("Введите допустимый знак арифметического действия. " +
                                "Калькулятор умеет выполнять следующие операции с двумя числами: " +
                                "сложение (a + b), вычитание (a - b), умножение (a * b), деление (a / b).");
                    }
                } else { System.out.println(expr.result); }
            }
        }
        if (expr.op1 < 0 | expr.op2 < 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Введите допустимое число. Калькулятор принимает целые арабские числа, либо римские, от 1 до 10 включительно.");
            }
        }
    }

    static class Expression {
        String keyIn;
        String keyInGood;
        String strOp1;
        String strOp2;
        boolean charIsLetter;
        boolean str2IsLetter;
        boolean str1IsLetter;
        boolean charIsDigit;
        boolean str2IsDigit;
        boolean str1IsDigit;
        String operation;
        int op1;
        int op2;
        int result;
        String[] stringsArray;

        void opStrIs(String s) {
            str2IsLetter = true;
            str2IsDigit = true;
            for (int i = 0; i < s.length(); i++) {
                charIsLetter = Character.isLetter(s.charAt(i));
                charIsDigit = Character.isDigit(s.charAt(i));
                str2IsLetter = str2IsLetter & charIsLetter;
                str2IsDigit = str2IsDigit & charIsDigit;
            }
        }

        int checkRom(String op) {
            for (int i = 1; i <= 10; i++)
                if (op.equalsIgnoreCase(RomanCharSet[i])) return i;
            return -1;
        }

        int checkArab(String op) {
            int pars = Integer.parseInt(op);
            for (int i = 1; i <= 10; i++) {
                if (i == pars) {
                    return pars;
                }
            }
            return -1;
        }

        int calculate(int a, int b) {
            switch (operation) {
                case ("+") -> result = a + b;
                case ("-") -> result = a - b;
                case ("*") -> result = a * b;
                case ("/") -> result = a / b;
                default -> result = -11;
            }
            return result;
        }

        final String[] RomanCharSet = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
                "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV",
                "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI",
                "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
                "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
    }
}