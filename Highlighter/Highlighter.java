/* Highlighter.java
 * Given a text file and a word, all lines containing that word are written to the terminal,
 * and all instances of the word are highlighted.
 * Only matches words exactly, not including instances that lie within larger words
 * (the "fast" in "breakfast" should not be highlighted), but we can choose to allow some delimiters (such as punctuation).
 * If the `-inv` flag is enabled, then all lines which do *not* contain the word are written instead.
 */

package Highlighter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Highlighter {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    // Add more here if you want more.

    public static void main(String[] args) {
        final String VALID_DELIMITERS = ".!,;:`()[]{}<>_~@#$%^&*\\/|\"'=+ \n\t";

        String fileName = "";
        String wordToSearch = "";

        int fileIndex = Arrays.asList(args).indexOf("-f");
        if (fileIndex >= 0 && fileIndex + 1 < args.length) {
            fileName = args[fileIndex + 1];
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-inv")) {
                continue;
            }

            if (i == fileIndex) {
                i++;
            } else {
                wordToSearch = args[i];
            }
        }

        if (fileName.isEmpty() || wordToSearch.isEmpty()) {
            Scanner input = new Scanner(System.in);

            if (fileName.isEmpty()) {
                do {
                    System.out.print("Enter a file name: ");
                    fileName = input.nextLine();
                } while (fileName.isEmpty());
            }

            if (wordToSearch.isEmpty()) {
                do {
                    System.out.print("Enter a word to search for: ");
                    wordToSearch = input.nextLine();
                } while (wordToSearch.isEmpty());
            }

            input.close();
        }

        try (Scanner fileScan = new Scanner(new File(fileName))) {
            boolean inverseCommand = Arrays.asList(args).contains("-inv");

            while (fileScan.hasNextLine()) {
                String highlightedLine = highlightString(fileScan.nextLine(), wordToSearch, VALID_DELIMITERS,
                        ANSI_GREEN + ANSI_UNDERLINE);

                boolean writeLine = highlightedLine.contains(ANSI_GREEN);

                if (writeLine ^ inverseCommand) {
                    System.out.println(highlightedLine.toString());
                }
            }

            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String highlightString(String input, String word, final String VALID_DELIMITERS, String style) {
        StringBuilder highlightedString = new StringBuilder();

        int i = 0;

        while (i < input.length()) {
            int startIndex = input.indexOf(word, i); // Inclusing
            int endIndex = startIndex + word.length(); // Exclusive

            if (startIndex < 0) {
                highlightedString.append(input.substring(i));
                break;
            }

            boolean isPrefixOkay = (startIndex == 0
                    || VALID_DELIMITERS.indexOf(input.charAt(startIndex - 1)) >= 0
                    || VALID_DELIMITERS.isEmpty());

            boolean isSuffixOkay = (startIndex + word.length() == input.length()
                    || VALID_DELIMITERS.indexOf(input.charAt(endIndex)) >= 0
                    || VALID_DELIMITERS.isEmpty());

            if (isPrefixOkay && isSuffixOkay) {
                highlightedString.append(input.substring(i, startIndex))
                        .append(style)
                        .append(word)
                        .append(ANSI_RESET);
            } else {
                highlightedString.append(input.substring(i, endIndex));
            }

            i = endIndex;
        }

        return highlightedString.toString();
    }

    public static String highlightString(String input, String word, String style) {
        return highlightString(input, word, "", style);
    }

    public static String highlightString(String input, String word) {
        return highlightString(input, word, "", "");
    }
}
