import java.util.Random;

public class Computer {
  int MAXROW = 10;
  int MAXCOL = 10;
  int[][] board = new int[MAXCOL][MAXROW]; //initialized in setComputer, 5 by 5 board
  int[] coord = new int[2]; //used to store x,y coordinate for set up and game play
  char direction; //for setPlayer and setComputer, for direction of ships

  boolean lastGood = false; //Boolean for checking around the shot location and which direction
  boolean trueN = false;
  boolean trueS = false;
  boolean trueE = false;
  boolean trueW = false;

  int[] originalShot = new int[2];


  boolean win = false; //to determine winner in winCheck()
  int playerHitCounter=0;

  public Computer(){
    for(int row=0;row<MAXROW;row++){
      for(int column=0;column<MAXCOL;column++){
        board[row][column]=0;
      }
    }
  }

  //Gets move of Computer
  //Stores move in coord
  public void getMove(Player p1) {
    Random rand = new Random(); // Initalize random seed 
    
    
    if (p1.boardState == 0) { // Weight random
      int[] boardChoice;
      //boardChoice[] = {1,2,2,3,3,3,4,4,5}; //Currently non-dynamic for 5
      boardChoice = new int[] {0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9};
      coord[1] = boardChoice[rand.nextInt(boardChoice.length)]; //X
      coord[0] = boardChoice[rand.nextInt(boardChoice.length)]; //Y
      System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
    } 

    else if (p1.boardState == -1) { //Normal random
      coord[1] = rand.nextInt(MAXROW); //X
      coord[0] = rand.nextInt(MAXCOL); //Y
      System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
    }

    else if (p1.boardState == -2) { // Hit, checking Y-North
    //I am not sure if I need to do this "copy" correctly
    //Temporary copy to ensure not out of bounds
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1];      //X? Unsure
      temp[0] = p1.coordStored[0] + 1;  //Y?
      if (temp [0] < p1.coord.length) {
        coord[1] = p1.coordStored[1];
        coord[0] = p1.coordStored[0] + 1;
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
      }
      else {
      p1.boardState = -3; //Next direction
      getMove(p1);
      }
    }

    else if (p1.boardState == -3) { // Hit, checking X-East
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1] + 1;      //X
      temp[0] = p1.coordStored[0];          //Y
      if (temp [1] < p1.coord.length) {
        coord[1] = p1.coordStored[1] + 1;
        coord[0] = p1.coordStored[0];
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
      }
      else {
      p1.boardState = -4; //
      getMove(p1);
      }
    }

    else if (p1.boardState == -4) { // Hit, checking X-West
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1] - 1;      //X
      temp[0] = p1.coordStored[0];          //Y
//      if (temp [1] < p1.coord.getLength) { 
      if (temp[1] > 0) {
        coord[1] = p1.coordStored[1] + 1;
        coord[0] = p1.coordStored[0];
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
      }
      else {
      p1.boardState = -5; 
      getMove(p1);
      }
    }

    else if (p1.boardState == -5) { // Hit, checking Y-South
      int temp[] = new int[2];
      temp[1] = p1.coordStored[1];          //X
      temp[0] = p1.coordStored[0] - 1;      //Y
//      if (temp [1] < p1.coord.getLength) { 
      if (temp[0] > 0) {
        coord[1] = p1.coordStored[1] + 1;
        coord[0] = p1.coordStored[0];
        System.out.println("Computer fires at: " + coord[1] + ", " + coord[0] + ".");
      }
      else {
      p1.boardState = -2; //Loops back around to North
      getMove(p1);
      }
    }
    else if (p1.boardState == -10) {  //Barrage
      int tempDir = rand.nextInt(4);
      p1.boardState = -(tempDir + 1);
      System.out.println("Direction " + p1.boardState);
      getMove(p1);

    }

    else {
      getMove(p1); //This is a really messy protocol
    }    
      
  }

  public boolean HitorMiss(Player p1) {
    //Takes in computer’s move and see if hit’s player's game pieces
    //uses coord from p1 and computer's board
    //checks if p1 coords hit computer's board
    //alters computer's board accordingly
    if (board[p1.coord[0]][p1.coord[1]] == 1 )
      {
      board[p1.coord[0]][p1.coord[1]] = 3;
      System.out.println("Hit Computer!");
      hitCounter();
      }
    else if (board[p1.coord[0]][p1.coord[1]] != 1 )
      {
        board[p1.coord[0]][p1.coord[1]] = -1;
      }
    else {
      board[p1.coord[0]][p1.coord[1]] = 2;
      System.out.println("Missed Computer");
    }
    boolean oppwin = true;
    for (int i=0; i<MAXCOL; i++) {
      for (int j=0; j<MAXROW; j++) {
        int cCheck = board[j][i]; // Less clutter
        if (cCheck == 5 || cCheck == 6 || cCheck == 7 || cCheck == 8 || cCheck == 9) { //Adjusted
          oppwin = false;
        }
      }
    }
    return oppwin;
  }

  public void hitCounter(){
    playerHitCounter=playerHitCounter+1;
  }


  //adds values to the computer's board
  public int[][] createBoard()
  {
    int[][] board = new int[MAXROW][MAXCOL];
    for(int row=0 ; row < MAXROW ; row++ )
    {
      for(int column=0 ; column < MAXCOL ; column++ )
      {
        board[row][column]= 0;
        System.out.print(" " + board[row][column] + " ");
      }
      System.out.println("");
    }
    return board;
  }

  //for testing purposes
  public void printBoard(int[][] boarda)
  {
    for (int row = 0; row < MAXROW; row++)
    {
      for (int col = 0; col < MAXCOL; col++)
      {
        System.out.print(" " + boarda[row][col] + " ");
      }
      System.out.println("");
    }
  }

  public void placeShips()
  {
    //Carrier, Battleship, Crusier, Submarine, Destroyer
    setShip(5, 5);
    setShip(4, 6);
    setShip(3, 7);
    setShip(3, 8);
    setShip(2, 9);
  }

  //Symbol is for debugging
  public void setShip(int shipLength, int symbol)
  {
    //Chooses a random point on the array to start off.
    Random rand = new Random();
    //Limits starting point based on the ship's length.
    int startingRow = rand.nextInt(MAXROW - shipLength) + 1;
    int startingCol = rand.nextInt(MAXCOL - shipLength) + 1;
    //0 = Vertical, 1 = Horizontal
    int vertOrHor = rand.nextInt(2);

    //Testing
    //System.out.println("StartCol: " + startingCol);
    //System.out.println("StartRow: " + startingRow);
    //System.out.println("Vert?: " + vertOrHor);

    //Checks if the ship will occupy any spaces.
    if (checkComputerSetup(board, startingRow,
    startingCol, shipLength, vertOrHor))
    {
      //Changes row/col of board w/o changing ship's row/col.
      int shipCol = startingCol;
      int shipRow = startingRow;
      for (int sLength = 0; sLength < (shipLength); sLength++)
      {
        //replaces 0 with a number, indicating it is placed.
        if (vertOrHor == 0)
        {
          board[shipCol][startingRow] = symbol;
          shipCol++;
        }
        else
        {
          board[startingCol][shipRow] = symbol;
          shipRow++;
        }
      }
      //Testing
      //printBoard(board);
    }
    else
    //Finds a new startingRow and Col if the space is occupied.
    //Recursive, so repeats until it finds an empty space.
    setShip(shipLength, symbol);
  }

  //Checks if the space the piece is to be placed is empty.
  public boolean checkComputerSetup(int[][] boardToCheck, int row,
  int col, int shipLength, int vertOrHor)
  {
    boolean emptySpace = true;
    //Cycles through the array for the length of shipLength
    for (int sLength = 0; sLength < shipLength; sLength++)
    {
      //0 = space is empty
      if (boardToCheck[col][row] != 0)
      emptySpace = false;
      else
      emptySpace = emptySpace && true;
      //Alters what spaces to check, depending on ship placement.
      if (vertOrHor == 0) //Vertical checking
      col++;
      else //Horizontal checking
      row++;
    }
    return emptySpace;
  }
}
