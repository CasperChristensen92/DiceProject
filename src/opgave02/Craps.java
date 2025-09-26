package opgave02;

import java.util.Scanner;

public class Craps {
    private static int wins;
    private static int loses;

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, crabs.");
        printRules();
        System.out.println();

        playCrabs();

        System.out.println();
        System.out.println("Tak for at spille, crabs.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for crabs");
        System.out.println("Spillet består af en række kast med to terninger. Udfaldet af et kast er summen af de to terningers");
        System.out.println("Det første kast kaldes ‘come out roll’. Spilleren vinder med det samme, hvis det første kast er 7\n" +
                "eller 11, og taber med det samme, hvis det første kast er 2, 3 eller 12.");
        System.out.println("Hvis spillerens første kast er 4, 5, 6, 8, 9 eller 10, etableres dette tal som spillerens ‘point’. Spilleren bliver derefter ved med at\n" +
                "kaste, indtil han enten kaster sit ‘point’ igen eller kaster 7.");
        System.out.println("Kaster han 7, har han tabt. Kaster han sit ’point’, har han vundet.");
        System.out.println("=====================================================");
    }

    private static void playCrabs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("spil Crabs, ja eller nej");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = rollDice(2);
            System.out.println("Du rullede:");
            printNumbers(faces);
            int point = 0;
            for (int x : faces) {
                point += x;
            }
            if (point == 7 || point == 11) {
                System.out.println("Congratulations! You win.");
                updateStatistics(true);
            } else if (point == 2 || point == 3 || point == 12) {
                updateStatistics(false);
                System.out.println("You lose");
            } else {
                boolean win = rollForPoints(point);
                if (win){
                    System.out.println("Congratulations! You win in crabs");
                    updateStatistics(true);
                } else {
                    System.out.println("You lose");
                    updateStatistics(false);
                }
            }

            System.out.println("Spil Crabs igen? ja/nej");
            answer = scanner.nextLine();
        }
        printStatistics();
        scanner.close();
    }

    private static boolean rollForPoints(int point) {
        int newPoint = 0;
        while (newPoint !=point || newPoint != 7) {
            newPoint = 0;
            int[] newRoll = rollDice(2);
            System.out.println("You new roll is: ");
            printNumbers(newRoll);
            for (int x : newRoll) {
                newPoint +=x;
            }
            if (newPoint==point) return true;
            if (newPoint==7) return false;

        }
        return false;

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

    private static void updateStatistics(boolean b) {
        if (b){
            wins += 1;
        }
        else{
            loses +=1;
        }
    }
    private static void printStatistics(){
        System.out.println("You have: " + wins + " wins.");
        System.out.println("And you have: " + loses + " loses.");
    }

    private static void printNumbers(int[] integerArray) {
        for (int number : integerArray) {
            System.out.print(number + " ");
        }
        System.out.println(" ");
    }

}
