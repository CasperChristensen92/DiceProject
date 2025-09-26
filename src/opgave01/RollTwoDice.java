package opgave01;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int sum = 0;
    private static int sameEyes = 0;
    private static int highestRoll = 0;
    private static int[] rolledDice = new int[6];

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul to terninger.");
        printRules();
        System.out.println();

        playTwoDice();

        System.out.println();
        System.out.println("Tak for at spille, rul to terninger.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul to terninger");
        System.out.println("Spilleren ruller to terninger, så længde man lyster.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul to terninger? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = rollDice(2);
            System.out.println("Du rullede: ");
            printNumbers(faces);

            System.out.println();

            updateStatistics(faces);

            System.out.print("Rul to terninger? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    private static void printNumbers(int[] integerArray) {
        for (int number : integerArray) {
            System.out.print(number + " ");
        }
        System.out.println(" ");
    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static int[] rollDice(int x) { //rollDice tager en parameter x der angiver hvor mange terninger vi gerne vil rulle
        int[] rolledDice = new int[x];
        for (int i = 0; i < rolledDice.length; i++) {
            rolledDice[i] = rollDie();
        }
        return rolledDice;
    }

    private static void updateStatistics(int[] faces) {
        int temporarySum = 0;
        rollCount++;
        for (int x : faces) {
            temporarySum += x;
        }
        if (faces[0] == faces[1]) sameEyes += 1; //ikke lavet til at kunne udvides til flere terningekast end to
        sum = temporarySum;
        if (temporarySum > highestRoll) highestRoll = temporarySum;
        for (int x : faces) rolledDice[x - 1] += 1;
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%4s %4d\n", "Antal rul:", rollCount);
        System.out.printf("%4s %4d\n", "Summen af terningekast:", sum);
        System.out.printf("%4s %4d\n", "Antal gange terningerne har vist ens øjne:", sameEyes);
        System.out.printf("%4s \n", "Gange hver terning er blevet slået");
        printRolledDice(rolledDice);
    }

    private static void printRolledDice(int[] integerArray) {
        for (int i = 0; i < 6; i++) {
            System.out.print(i + 1 + ": " + integerArray[i] + " | ");
        }
        System.out.println(" ");
    }
}