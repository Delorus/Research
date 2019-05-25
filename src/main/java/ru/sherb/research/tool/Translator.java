package ru.sherb.research.tool;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Translator {

    private static final int NUM_LETTERS = 33;
    private static final String START_TEXT = "> ";
    private static final char SPLIT_WORD = ' ';

    private static char[] cyrillic = {
            'й', 'ц', 'у', 'к', 'е', 'н', 'г', 'ш', 'щ', 'з', 'х', 'ъ', 'ф', 'ы', 'в', 'а', 'п',
            'р', 'о', 'л', 'д', 'ж', 'э', 'я', 'ч', 'с', 'м', 'и', 'т', 'ь', 'б', 'ю', '.', SPLIT_WORD
    };

    private static char[] latin = {
            'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', 'a', 's', 'd', 'f', 'g',
            'h', 'j', 'k', 'l', ';', '\'', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/', SPLIT_WORD
    };

    private static String latinToCyrillic(String latin) {
        final StringBuilder result = new StringBuilder(latin.length());

        for (int i = 0; i < latin.length(); i++) {
            for (int j = 0; j < Translator.latin.length; j++) {
                if (latin.charAt(i) == Translator.latin[j]) {
                    result.append(cyrillic[j]);
                    break;
                }
            }
        }

        return result.toString();
    }

    private static String cyrillicToLatin(String cyrillic) {
        final StringBuilder result = new StringBuilder(cyrillic.length());

        for (int i = 0; i < cyrillic.length(); i++) {
            for (int j = 0; j < Translator.cyrillic.length; j++) {
                if (cyrillic.charAt(i) == Translator.cyrillic[j]) {
                    result.append(latin[j]);
                    break;
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        String encoding = System.getProperty("sun.stdout.encoding");
        if (encoding == null || !Charset.isSupported(encoding)) {
                encoding = System.getProperty("file.encoding");
                if (encoding == null || !Charset.isSupported(encoding)) {
                    System.out.println("You charset is not supported. Current charset set default \"UTF-8\"");
                    encoding = "UTF-8";
                }
        }

        System.setOut(new PrintStream(System.out, true, encoding));

        String text;
        try (Scanner scanner = new Scanner(System.in, encoding)) {

            System.out.println("Enter your text to translate here:\n");
            System.out.print(START_TEXT);
            text = scanner.nextLine();
        }


        boolean isLatin = false;
        boolean isCyrillic = false;

        for (int i = 0; i < NUM_LETTERS; i++) {
            if (Character.toLowerCase(text.charAt(0)) == latin[i]) {
                isLatin = true;
                break;
            }

            if (Character.toLowerCase(text.charAt(0)) == cyrillic[i]) {
                isCyrillic = true;
                break;
            }
        }


        System.out.print(START_TEXT);
        if (isLatin) {
            System.out.println(latinToCyrillic(text));
        } else if (isCyrillic) {
            System.out.println(cyrillicToLatin(text));
        } else {
            System.out.println("it's text is not a latin or cyrillic");
        }
    }
}
