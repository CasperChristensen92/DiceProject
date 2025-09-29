package opgave03;

import java.util.Scanner;

public class Pigs {
    private static int p1Wins = 0;
    private static int p2Wins = 0;
    private static int p1Rounds = 0;
    private static int p2Rounds = 0;
    private static int p1Turns = 0;
    private static int p2Turns = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game, \"pigs\"!");
        printRules();
        System.out.println();
        System.out.println("Type in the number you want to play to");
        int n = scanner.nextInt();
        System.out.println("Type in if you want to use one or two die (you can play with more, but the rules are not updated for more than 2");
        int d = scanner.nextInt();
        playPigs(n, d);

        System.out.println();
        System.out.println("Thank you for playing pigs!");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Rules of the game in danish");
        System.out.println("Første spiller kaster en terning, indtil han enten kaster 1, eller beslutter sig for at stoppe. Hvis han\n" +
                "slår en 1’er, får han ingen point i denne runde. Hvis han beslutter sig for at stoppe, inden har slår\n" +
                "en 1’er, lægges summen af alle hans kast i denne runde sammen med hans samlede antal point,\n" +
                "og turen går videre til næste spiller. Derefter skiftes spillerne til at kaste. Den første spiller, der\n" +
                "samlet når 100 eller andet antal point, har vundet.\n" +
                "Ved to terninger mister man alle point opnået gennem hele spillet hvis man slår 2 1'ere og får 0 point i runden\n" +
                "hvis man slår én 1'er. Ellers får man summes af de to terninger");
        System.out.println("=====================================================");
    }

    private static void playPigs(int n, int d) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of player 1");
        String player1 = scanner.nextLine();
        System.out.println("Enter name of player 2");
        String player2 = scanner.nextLine();
        System.out.println("play pigs, yes or no");
        String answer = scanner.nextLine();
        while (!answer.equals("no")) {

            int p1Points = 0;
            int p2Points = 0;
            while (p1Points < n && p2Points < n) {
                System.out.println("It is now " + player1 + "'s turn to play");
                int[] turn = playOneTurn(d); //makes an array of length 2 if d=1 and length 3 if d > 1. First index is the sum of the turn. Second index is have many rounds the turn has taken, and if there is a third index it indicates if the player has only rolled 1's.
                if (d != 1 && turn[2] == 1) p1Points =0; // there is only an index 2 if we play with more than one dice. And if that points to a value of 1 it means we have rolled only 1's and the players points need to be reset
                else p1Points += turn[0];
                p1Rounds += turn[1];
                p1Turns += 1;
                displayStatus(p1Points, p2Points, player1, player2);
                System.out.println("It is now " + player2 + "'s turn to play");
                turn = playOneTurn(d);
                if (d != 1 && turn[2] == 1) p2Points =0;
                else p2Points += turn[0];
                p2Rounds += turn[1];
                p2Turns += 1;
                displayStatus(p1Points, p2Points, player1, player2);
            }
            if (p1Points > p2Points) {
                System.out.println("Congratulations " + player1 + "! You won over" + player2 + " with " + p1Points + " against " + p2Points);
                updateStatistics(p1Points, p2Points);
            } else {
                System.out.println("Congratulations " + player2 + "! You won over" + player1 + " with " + p2Points + " against " + p1Points);
                updateStatistics(p1Points, p2Points);
            }

            System.out.println("play pigs again? yes/no");
            answer = scanner.nextLine();
        }
        printStatistics(player1, player2);
        scanner.close();
    }

    private static int[] playOneTurnOneDie() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int rounds = 0;
        int[] playArray = {0, 0};
        String answer = "yes";
        while (!answer.equals("no")) {
            int roll = rollDie();
            System.out.println("you rolled a: " + roll);
            playArray[1] += 1;
            if (roll == 1) {
                System.out.println("You rolled a 1 and get no points");
                playArray[0] = 0;
                return playArray;
            } else sum += roll;
            System.out.println("your new sum is: " + sum);
            System.out.println("Do you want to roll again? yes/no");
            answer = scanner.nextLine();
        }
        playArray[0] = sum;
        return playArray;

    }

    private static int[] playOneTurn(int d) {
        if (d==1) return playOneTurnOneDie();
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int rounds = 0;
        int twoOnes = 0; // 0 is that you have not rolled two ones and your points will not be reset. A "1" implicates that you rolled two 0's and your points will be reset.
        int[] playArray = {0, 0, 0};
        String answer = "yes";
        while (!answer.equals("no")) {
            int[] roll = rollDice(d);
            System.out.println("you rolled the following dice: ");
            printNumbers(roll);
            playArray[1] += 1;
            if (checkOnes(roll)) {
                playArray[2] = 1;
                return playArray;
            }
            if (checkOne(roll)) {
                System.out.println("You rolled a 1 and get no points");
                playArray[0] = 0;
                return playArray;
            } else
                for (int x : roll) {
                    sum += x;
                }
            System.out.println("your new sum is: " + sum);
            System.out.println("Do you want to roll again? yes/no");
            answer = scanner.nextLine();
        }
        playArray[0] = sum;
        return playArray;

    }

    //The following returns true if all dice are 1's and the points need to be reset
    private static boolean checkOnes(int[] playArray) {
        for (int x : playArray) {
            if (x != 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkOne(int[] playArray) {
        for (int x : playArray) {
            if (x == 1) {
                return true;
            }
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

    private static void displayStatus(int p1Points, int p2Points, String p1Name, String p2Name) {
        System.out.println(p1Name + " has: " + p1Points + " points");
        System.out.println(p2Name + " has: " + p2Points + " points");
        if (p1Points > p2Points)
            System.out.println(p1Name + " is in the lead. " + (p1Points - p2Points) + " points ahead of " + p2Name);
        else if (p2Points > p1Points)
            System.out.println(p2Name + " is in the lead. " + (p2Points - p1Points) + " points ahead of " + p1Name);
        else System.out.println(p1Name + " and " + p2Name + " is tied");
    }

    private static void printNumbers(int[] integerArray) {
        for (int number : integerArray) {
            System.out.print(number + " ");
        }
        System.out.println(" ");
    }

    private static void updateStatistics(int p1Score, int p2Score) {
        if (p1Score > p2Score) p1Wins += 1;
        if (p2Score > p1Score) p2Wins += 1;

    }

    private static void printStatistics(String p1, String p2) {
        System.out.println(p1 + " has won " + p1Wins + " rounds");
        System.out.println(p2 + " has won " + p2Wins + " rounds");
        System.out.println(p1 + " has used " + p1Rounds / p1Turns + " runds per turn");
        System.out.println(p2 + " has used " + p2Rounds / p2Turns + " runds per turn");
    }


}
