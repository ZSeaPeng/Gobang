/**
 * Created by zseapeng on 2016/5/8.
 * 棋盘类
 */
public class Chessboard {
    private String[][] board;
    public static final int  BOARD_SIZE = 22;
    public void initBoard(){
        //初始化棋盘
        board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i=0;i<BOARD_SIZE;i++){
            for (int j=0;j<BOARD_SIZE;j++){
                board[i][j] = "十";
            }
        }

    }
    public void printBoard(){
        //在控制台输出棋盘
        for (int i=0;i<BOARD_SIZE;i++){
            for (int j=0;j<BOARD_SIZE;j++){
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
    public void setBoard(int posX,int posY,String chessman){
        this.board[posX][posY] = chessman;
    }
    public String[][] getBoard(){
        //返回棋盘
        return this.board;
    }

}
