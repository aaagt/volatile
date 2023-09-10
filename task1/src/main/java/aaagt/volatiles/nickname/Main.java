package aaagt.volatiles.nickname;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final AtomicInteger nice3Count = new AtomicInteger(0);
    private static final AtomicInteger nice4Count = new AtomicInteger(0);
    private static final AtomicInteger nice5Count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        var texts = generateTexts();
        Thread polyndromeTread = new Thread(() -> {
            for (var text : texts) {
                if (isPolyndrome(text)) {
                    switch (text.length()) {
                        case 3 -> nice3Count.incrementAndGet();
                        case 4 -> nice4Count.incrementAndGet();
                        case 5 -> nice5Count.incrementAndGet();
                    }
                }
            }
        });
        polyndromeTread.start();

        Thread letterTread = new Thread(() -> {
            for (var text : texts) {
                if (isOnlyOneLatter(text)) {
                    switch (text.length()) {
                        case 3 -> nice3Count.incrementAndGet();
                        case 4 -> nice4Count.incrementAndGet();
                        case 5 -> nice5Count.incrementAndGet();
                    }
                }
            }
        });
        letterTread.start();

        Thread alphabetTread = new Thread(() -> {
            for (var text : texts) {
                if (isAlphabetOrder(text)) {
                    switch (text.length()) {
                        case 3 -> nice3Count.incrementAndGet();
                        case 4 -> nice4Count.incrementAndGet();
                        case 5 -> nice5Count.incrementAndGet();
                    }
                }
            }
        });
        alphabetTread.start();

        polyndromeTread.join();
        letterTread.join();
        alphabetTread.join();

        System.out.printf("""
                Красивых слов с длиной 3: %s шт
                Красивых слов с длиной 4: %s шт
                Красивых слов с длиной 5: %s шт
                """, nice3Count, nice4Count, nice5Count);
    }

    private static String[] generateTexts() {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        return texts;
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPolyndrome(String word) {
        int half = word.length() / 2;
        for (int i = 0; i < half; i++) {
            var fromBeginning = word.charAt(i);
            var fromEnd = word.charAt(word.length() - i - 1);
            if (fromBeginning != fromEnd) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOnlyOneLatter(String word) {
        char prev = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            char current = word.charAt(i);
            if (prev != current) {
                return false;
            }
            prev = current;
        }
        return true;
    }

    public static boolean isAlphabetOrder(String word) {
        char prev = word.charAt(0);
        for (int i = 1; i < word.length(); i++) {
            char current = word.charAt(i);
            if (prev > current) {
                return false;
            }
            prev = current;
        }
        return true;
    }
}
