import java.util.Scanner;

public class Display {
  int[] coord = new int[2];
  int MAXROW = 10;
  int MAXCOL = 10;

  // Carrier – 5 squares - shipCode=5;
  // Battleship – 4 squares- shipCode=6;
  // Cruiser – 3 squares- shipCode=7;
  // Submarine – 3 squares- shipCode=8;
  // Destroyer – 2 squares- shipCode=9;

  public void showPlayerBoard(int[][] board) {
    //• Show the tracking view of where the player has tried,
    //symbolizing hits and misses, see idea below.
    //I would like the numbers and letters added to see the lines and columns

// following class is heavily modified from something taken online
// unsure of what variable names go where
// public static void showComputerBoard(int[][] board){
    // displays board
        System.out.println();
        System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");

        // int board[][]={{0,1,2,3,0},{0,1,0,0,0},{0,1,0,0,0},{0,0,0,0,0},{0,0,0,0,0},};
        for(int row=0 ; row < MAXROW ; row++ ){
            System.out.print((row+1)+"   ");
            for(int column=0 ; column < MAXCOL ; column++ ){
                if(board[row][column]==-1){
                    //for off the board?/not hit yet
                    System.out.print("\t"+"0");
                    //for miss
                }else if(board[row][column]==0){
                    if(row==9){
                      System.out.print("|"+"     "+"| ");
                    }
                    else{
                    System.out.print(" "+"|"+"     "+"|");
                  }
                //for if it hits the ship
              }
              else if(board[row][column]==2){
                  System.out.print(" "+"|"+"  O  "+"|");
              }
            else if(board[row][column]==3){
                System.out.print(" "+"|"+"  X  "+"|");
              }
            else{
                System.out.print(" "+"|"+"  S  "+"|");
            }


            }
            System.out.println();
        }
  }

  public void showComputerBoard(int[][] board) {
    //• Show the tracking view of where the player has tried,
    //symbolizing hits and misses, see idea below.
    //I would like the numbers and letters added to see the lines and columns

  // following class is heavily modified from something taken online
  // unsure of what variable names go where
  // public static void showComputerBoard(int[][] board){
    // displays board
        System.out.println();
        System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");

        for(int row=0 ; row < MAXROW ; row++ ){
            System.out.print((row+1)+"   ");
            for(int column=0 ; column < MAXCOL ; column++ ){
                if(board[row][column]==-1){
                    //for off the board?/not hit yet
                    System.out.print("\t"+"0");
                    //for miss
                }
                //for if it hits the ship
                else if(board[row][column]==0){
                  if(row==9){
                    System.out.print("|"+"     "+"| ");
                  }
                  else{
                  System.out.print(" "+"|"+"     "+"|");
                }
                //for if it hits the ship
                }
              else if(board[row][column]==2){
                  System.out.print(" "+"|"+"  O  "+"|");
              }
            else if(board[row][column]==3){
                System.out.print(" "+"|"+"  X  "+"|");
              }
              else{
                  System.out.print(" "+"|"+"  S  "+"|");
              }

            }
            System.out.println();
        }
  }
}
