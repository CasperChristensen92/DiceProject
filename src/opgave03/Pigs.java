package opgave03;

import java.util.Scanner;

public class Pigs {
    private static int p1Wins = 0;
    private static int p2Wins = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Velkommen til spillet, pigs.");
        printRules();
        System.out.println();

        playPigs(n);

        System.out.println();
        System.out.println("Tak for at spille, pigs.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for pigs");
        System.out.println("Første spiller kaster en terning, indtil han enten kaster 1, eller beslutter sig for at stoppe. Hvis han\n" +
                "slår en 1’er, får han ingen point i denne runde. Hvis han beslutter sig for at stoppe, inden har slår\n" +
                "en 1’er, lægges summen af alle hans kast i denne runde sammen med hans samlede antal point,\n" +
                "og turen går videre til næste spiller. Derefter skiftes spillerne til at kaste. Den første spiller, der\n" +
                "samlet når 100 point, har vundet.");
        System.out.println("=====================================================");
    }

    private static void playPigs(int n){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of player 1");
        String player1 = scanner.nextLine();
        System.out.println("Enter name of player 2");
        String player2 = scanner.nextLine();
        System.out.println("play pigs, yes or no");
        String answer = scanner.nextLine();
        while (!answer.equals("no")){
            int p1Points = 0;
            int p2Points = 0;
            while (p1Points < n || p2Points < n){
                System.out.println("It is now " + player1 + "'s turn to play");
                p1Points += playOneRound();
                displayStatus(p1Points,p2Points,player1,player2);
                System.out.println("It is now " + player2 + "'s turn to play");
                p2Points += playOneRound();
                displayStatus(p1Points,p2Points,player1,player2);
            }
            if (p1Points > p2Points){
                System.out.println("Congratulations " + player1 + "! You won over" + player2 + " with " + p1Points + " against " + p2Points);
                updateStatistics(p1Points,p2Points);
            }
            else {
                System.out.println("Congratulations " + player2 + "! You won over" + player1 + " with " + p2Points + " against " + p1Points);
                updateStatistics(p1Points,p2Points);
            }

            System.out.println("play pigs again? yes/no");
            answer = scanner.nextLine();
        }
    }

    private static void displayStatus(int p1Points, int p2Points, String p1Name, String p2Name){
        System.out.println(p1Name + " has: " + p1Points + " points");
        System.out.println(p2Name + " has: " + p2Points + " points");
        if (p1Points > p2Points) System.out.println(p1Name + " is in the lead. " + (p1Points-p2Points) + " points ahead of " + p2Name);
        else if (p2Points > p1Points) System.out.println(p2Name + " is in the lead. " + (p2Points-p1Points) + " points ahead of " + p1Name);
        else System.out.println(p1Name + " and " + p2Name + " is tied");
    }

    private static int playOneRound(){
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        String answer = "yes";
        while (!answer.equals("no")) {
            int roll = rollDie();
            System.out.println("you rolled a: " + roll);
            if (roll ==1) {
                System.out.println("You rolled a 1 and get no points");
                return 0;
            }
            else sum += roll;
            System.out.println("your new sum is: " + sum);
            System.out.println("Do you want to roll again? yes/no");
            answer = scanner.nextLine();
        }
        return sum;

    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static void updateStatistics(int p1Score, int p2Score){
        if (p1Score > p2Score) p1Wins += 1;
        if (p2Score > p1Score) p2Wins += 1;
    }


}
