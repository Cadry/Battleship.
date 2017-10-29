import java.util.Scanner;

public class Battleship {

  public static void main(String args[]) {
    GamePlay game = new GamePlay();
    Display screen = new Display();

    Player p1 = game.setPlayer();
    screen.showPlayerBoard(p1.board);
    Computer c1 = game.setComputer();
    screen.showComputerBoard(c1.board);

    while (!p1.endGame) {
        p1.getMove();

       //Gets player’s move
      p1.win = c1.HitorMiss(p1); //Check if hit or a miss, updates c1 board and p1.win
      System.out.println("P1: ");
      screen.showComputerBoard(c1.board);
      // p1.printBoard(p1.board);

      c1.getMove(p1); //Gets computer’s move
      c1.win = p1.HitorMiss(c1); //Check if hit or miss, updates p1 board and c1.win
      // System.out.println("COMP");
      // c1.printBoard(c1.board);
      //Screen.displayGameProgress(c1,p1); //Display turns, player’s health etc.

      p1.endGame = game.winCheck(c1,p1); // Check if game is over
    }

    //display game result messages
    if (p1.win && !c1.win) {
      System.out.println("You win!");
    } else if (!p1.win && !c1.win) {
      System.out.println("Computer wins! You lose!");
    } else if (p1.win && c1.win) {
      System.out.println("It's a tie!");
    }

    System.out.println("GAME OVER");

  }



}
