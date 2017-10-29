import java.util.Scanner;

public class GamePlay {
  int MAXROW = 10;
  int MAXCOL = 10;
  int shipSize;
  // Carrier – 5 squares - shipCode=5;
  // Battleship – 4 squares- shipCode=6;
  // Cruiser – 3 squares- shipCode=7;
  // Submarine – 3 squares- shipCode=8;
  // Destroyer – 2 squares- shipCode=9;


  public Player setPlayer() {
    //gets user input to pick positions on the board
    //checks that none of the ships overlap
    //return player
    Player p = new Player();
    int maxship = 5;
    int boardTotal=25;
    int shipCode=5;

    for (int i = 0; i < maxship; i++) {
      System.out.println("This is your " + (i+1) + " boat out of " + maxship + ".");
      p.board=p.placeShips(boardTotal,shipCode);
      shipCode++;
      boardTotal=boardTotal+(shipCode*p.getShipSize(shipCode));

    }
    return p;
  }

  public int getShipSize(int shipCode){
    if(shipCode==5){
      shipSize=5;
    }
    else if(shipCode==6){
      shipSize=4;
    }
    else if(shipCode==7){
      shipSize=3;
    }
    else if(shipCode==8){
      shipSize=3;
    }
    else if(shipCode==9){
      shipSize=2;
    }
    return shipSize;
  }
  public int Char2Int(char h) {
    int num = 0;
    if (h == 'A') {
      num = 0;
    } else if (h == 'B') {
      num = 1;
    } else if (h == 'C') {
      num = 2;
    } else if (h == 'D') {
      num = 3;
    } else if (h == 'E') {
      num = 4;
    } else if (h == 'F') {
      num = 5;
    } else if (h == 'G') {
      num = 6;
    } else if (h == 'H') {
      num = 7;
    } else if (h == 'I') {
      num = 8;
    } else if (h == 'J') {
      num = 9;
    }
    return num;
  }


  public Computer setComputer()
  {
    //should return computer;
    //setComputer method calls:
    //Idea- it picks a starting spot for each ship, then directions randomly.
    //o checkComputerSetup
    //• checks that none of the positions overlap, if so, resets smaller ship

    //return computer
    //Computer temp;
    //return temp;

    Computer c = new Computer();
    // c.board = createBoard();
      c.placeShips();

    return c;
  }

  public boolean winCheck(Computer c1, Player p1) {
      boolean temp = false;
    //if c1.win or win (of player) is true
      if(p1.computerHitCounter==17){
        c1.win=true;
      }
      else if(c1.playerHitCounter==17){
        p1.win=true;
      }
    if(p1.win||c1.win){
      temp=true;
    }
      //return true --> p1.endgame = true
    //else
      //return false

    return temp;
  }


}
