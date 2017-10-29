import java.util.Scanner;
import java.util.*;

public class Player {
  int MAXROW = 10;
  int MAXCOL = 10;
  int[][] board= new int[10][10]; //initialized in setPlayer, 5 by 5 board
  int[] coord = new int[2]; //used to store x,y coordinate for set up and game play
  char direction; //for setPlayer and setComputer, for direction of ships

  boolean win = true; //to determine winner in winCheck()
  boolean endGame = false; //to determine when to end game in winCheck()
  public int[][] oldBoard=new int[10][10];
  boolean boardOK;
  int shipCode;
  int shipSize;
  int computerHitCounter=0;

  int boardState = 0;
  int[] coordStored = new int[2];

  // Carrier – 5 squares - shipCode=5;
  // Battleship – 4 squares- shipCode=6;
  // Cruiser – 3 squares- shipCode=7;
  // Submarine – 3 squares- shipCode=8;
  // Destroyer – 2 squares- shipCode=9;


  public Player(){
    for(int row=0;row<MAXROW;row++){
      for(int column=0;column<MAXCOL;column++){
        board[row][column]=0;
      }
    }
  }

  public int[] getMove() {
    //Gets user input
    //stores move in coord
    System.out.println("Enter your next move:");

    Scanner keyboard = new Scanner(System.in);
    System.out.println(" Horizontal (A-J):");
    coord[1] = Char2Int(keyboard.next().charAt(0));


    Scanner keyboard2 = new Scanner(System.in);
    System.out.println(" Vertical (1-10):");
    coord[0] = keyboard2.nextInt()-1;
    if(coord[1]>9 || coord[0]>9){
      System.out.println("Please enter a valid move.");
      getMove();
    }
    else{
      System.out.println("Your next move is: " + coord[1] + ", " + coord[0] + ".");
    }
    return coord;
  }

  public int Char2Int(char h) {
    int num = 0;
    if (h == 'A' || h == 'a') {
      num = 0;
    } else if (h == 'B' || h == 'b') {
      num = 1;
    } else if (h == 'C' || h == 'c') {
      num = 2;
    } else if (h == 'D' || h == 'd') {
      num = 3;
    } else if (h == 'E' || h == 'e') {
      num = 4;
    } else if (h == 'F' || h == 'f') {
      num = 5;
    } else if (h == 'G' || h == 'g') {
      num = 6;
    } else if (h == 'H' || h == 'h') {
      num = 7;
    } else if (h == 'I' || h == 'i') {
      num = 8;
    } else if (h == 'J' || h == 'j') {
      num = 9;
    }
    return num;
  }


  public boolean HitorMiss(Computer c1) {

      int tempC = board[c1.coord[0]][c1.coord[1]]; //To reduce clutter
//    if ( board[c1.coord[0]][c1.coord[1]] == (5||6||7||8||9) ) {
      if (tempC == 5 || tempC == 6 || tempC == 7 || tempC == 8 || tempC == 9) {
      board[c1.coord[0]][c1.coord[1]] = 3;
      boardState = -10; //Changing firing state
      c1.lastGood = true;
      coordStored[0] = c1.coord[0]; //Storing the shots
      coordStored[1] = c1.coord[1];
      System.out.println("!!!"); //Computer Hit!
      hitCounter();
    }
    else {
      board[c1.coord[0]][c1.coord[1]] = 2;
      boardState = 0;
      System.out.println(":C"); //Computer Miss...
    }
    boolean oppwin = true;
    for (int i=0; i<MAXCOL; i++) {
      for (int j=0; j<MAXROW; j++) {
        int pCheck = board[j][i]; // Less clutter
        if (pCheck == 5 || pCheck == 6 || pCheck == 7 || pCheck == 8 || pCheck == 9) { //Adjusted
          oppwin = false;
        }
      }
    }
    return oppwin;
  }

  public void hitCounter(){
    computerHitCounter=computerHitCounter+1;
  }

  public int[][] placeShips(int boardTotal, int shipCode){
    coord=getCoord();
    if (checkDirection(getShipSize(shipCode)))
    {
      setBattleship(shipCode);
      boardOK=checkBoard(boardTotal);
      if(!boardOK){
        System.out.println("Please select a valid position on the board. Note that you cannot place a ship ontop of another.");
        System.out.println();
        resetBoard();
        placeShips(boardTotal,shipCode);
      }
      copyBoard();
    }
    else
    {
      System.out.println("Direction is out of bounds.");
      System.out.println("Please select again.");
      resetBoard();
      placeShips(boardTotal, shipCode);
    }
    return board;
  }

  public int[] getCoord(){
    System.out.println("Enter your coordinates...");

    Scanner keyboard = new Scanner(System.in);
    System.out.println(" Horizontal (A-J):");
    coord[1] = Char2Int(keyboard.next().charAt(0));

    //Exception for if Vertical is not a number,
    //or if Direction is not a letter.
    try
    {
      System.out.println(" Vertical (1-10):");
      coord[0] = keyboard.nextInt() - 1;

      System.out.println(" Direction of ship (N,S,E,W):");
      direction = keyboard.next().charAt(0);
    }
    //Calls method again to try again if input is wrong.
    catch(InputMismatchException e)
    {
      System.out.println("Invalid input - try again.");
      getCoord();
    }
    return coord;
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

  public void setBattleship(int shipCode) {
    shipSize=getShipSize(shipCode);
    for (int i = 0; i < shipSize; i++) {
      if (direction == 'N' || direction == 'n') {
        board[coord[0]-i][coord[1]] = shipCode;
      }
      if (direction == 'S' || direction == 's') {
        board[coord[0]+i][coord[1]] = shipCode;
      }
      if (direction == 'E' || direction == 'e') {
        board[coord[0]][coord[1]+i] = shipCode;
      }
      if (direction == 'W' || direction == 'w') {
        board[coord[0]][coord[1]-i] = shipCode;
      }
    }
  }

  public boolean checkBoard(int currentBoardSum){
    int sumBoard=0;
    for (int i = 0; i < board[0].length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        sumBoard += board[i][j];
      }
    }
    if(sumBoard==currentBoardSum){
      boardOK=true;
    }
    else{
      boardOK=false;
    }
    return boardOK;
  }

  public void resetBoard(){
    for(int i=0; i<board.length; i++)
    for(int j=0; j<board[i].length; j++)
    board[i][j]=oldBoard[i][j];
  }

  public void copyBoard(){
    for(int i=0; i<board.length; i++)
    for(int j=0; j<board[i].length; j++)
    oldBoard[i][j]=board[i][j];
  }

  //Checks the direction of the ship placement.
  public boolean checkDirection(int shipSize)
  {
    boolean validDirection = true;
    //Checks if the ship would go out of bounds.
    if (direction == 'N' || direction == 'n')
    {
      if ((coord[0] - shipSize) < 0)
      validDirection = false;
    }
    else if (direction == 'S' || direction == 's')
    {
      if ((coord[0] + shipSize) > MAXCOL)
      validDirection = false;
    }
    else if (direction == 'E' || direction == 'e')
    {
      if ((coord[1] + shipSize) > MAXROW)
      validDirection = false;
    }
    else if (direction == 'W' || direction == 'w')
    {
      if ((coord[1] - shipSize) < 0)
      validDirection = false;
    }
    else
    {
      //If N, S, E, or W is not entered as a direction.
      System.out.println("Invalid direction selected.");
      System.out.println("Enter N, S, E, or W.");
      validDirection = false;
    }
    return validDirection;
  }
}
