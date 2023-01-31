package vn.edu.fpt.utils;

import java.util.Scanner;

public class ConsoleUtils {

    private static Scanner scanner = new Scanner(System.in);
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final static Boolean promYesNo() {
        System.out.print("\nDo you want to continue (Y/N) ? : ");
        while (true) {
            String line = scanner.nextLine();
            if (line.toUpperCase().equals("Y")) {
                System.out.println();
                return Boolean.TRUE;
            } else if (line.toUpperCase().equals("N")) {
                System.out.println();
                return Boolean.FALSE;
            } else {
                System.out.print("\nPlease choose either Y or N. Please choose again: ");
                continue;
            }
        }
    }

    public final static Integer promChoose(Integer noOfOptions) {
        System.out.print("\nPlease choose (1-" + noOfOptions + "): ");
        while (true) {
            if (scanner.hasNextLine()) {
                try {
                    int nextInt = Integer.parseInt(scanner.nextLine());
                    if (nextInt < 1 || nextInt > noOfOptions) {
                        System.out.print("\nThis option is not available. Please choose again: ");
                    } else {
                        System.out.println();
                        return nextInt;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("\nInput invalid. Please retry: ");
                }
            }
        }
    }

    public final static String promInput() {
        return scanner.nextLine();
    }

    public final static String promNumber() {
        while (true) {
            if (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (nextLine.contains(".")) {
                    try {
                        Double.parseDouble(nextLine);
                        return nextLine;
                    } catch (NumberFormatException e) {
                        System.out.print("\nInput invalid. Please retry: ");
                    }
                } else {
                    try {
                        Integer.parseInt(nextLine);
                        return nextLine;
                    } catch (NumberFormatException e) {
                        System.out.print("\nInput invalid. Please retry: ");
                    }
                }
            }
        }
    }
}
