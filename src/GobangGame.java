import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by zseapeng on 2016/5/8.
 * 游戏类
 */
public class GobangGame {
    private int WIN_COUNT = 5;
    private int posX = 0;
    private int posY = 0;
    private Chessboard chessboard;

    public GobangGame(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public boolean isValid(String inputStr){
        //验证输入是否合法
        String[] posStrArr = inputStr.split(",");
        try {
            posX = Integer.parseInt(posStrArr[0])-1;
            posY = Integer.parseInt(posStrArr[1])-1;

        }catch (NumberFormatException e){
            chessboard.printBoard();
            System.out.println("请以（数字，数字）的方式输入");
            return false;
        }
        if (posX < 0||posX>=Chessboard.BOARD_SIZE||posY<0||posY>=Chessboard.BOARD_SIZE){
            chessboard.printBoard();
            System.out.println("XY的坐标只能大于等于1，或小于等于"+Chessboard.BOARD_SIZE+"请重新输入");
            return false;
        }
        String[][] board = chessboard.getBoard();
        if (board[posX][posY]!="十"){
            chessboard.printBoard();
            System.out.println("此位置有棋子，请重新输入");
            return false;
        }

        return true;
    }
    public void start() throws Exception {
        //开始游戏
        boolean isOver=false;
        chessboard.initBoard();
        chessboard.printBoard();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        while ((inputStr = br.readLine())!=null){
            isOver = false;
            if (!isValid(inputStr)){
                continue;
            }
            String chessman = Chessman.BLACK.getChessman();
            chessboard.setBoard(posX,posY,chessman);
            if (isWon(posX,posY,chessman)){
                isOver = true;
            }else {
                int[] computerArr = computerDo();
                chessman = Chessman.WHITE.getChessman();
                chessboard.setBoard(computerArr[0],computerArr[1],chessman);
                if (isWon(computerArr[0],computerArr[1],chessman)){
                    isOver = true;
                }
            }
            if (isOver) {

                if (isReplay(chessman)) {
                    chessboard.initBoard();
                    chessboard.printBoard();
                    continue;
                }

                break;
            }
            chessboard.printBoard();
            System.out.println("请输入您下棋的坐标，应以x,y的格式输入：");
        }
        }

    public boolean isReplay(String chessman) throws IOException {
        //是否重新开始游戏
        chessboard.printBoard();
        String message = chessman.equals(Chessman.BLACK.getChessman()) ? "恭喜您，您赢了，"
                : "很遗憾，您输了，";
        System.out.println(message + "再下一局？(y/n)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (br.readLine().equals("y")) {
            // 开始新一局
            return true;
        }
        return false;
    }
    public int[] computerDo(){
        //计算机随机下棋
        int posX = (int)(Math.random()*(Chessboard.BOARD_SIZE-1));
        int posY = (int)(Math.random()*(Chessboard.BOARD_SIZE-1));
        String[][] board =chessboard.getBoard();
        while (board[posX][posY]!="十"){
            posX = (int)(Math.random()*(Chessboard.BOARD_SIZE-1));
            posY = (int)(Math.random()*(Chessboard.BOARD_SIZE-1));
        }
        int[] result = {posX,posY};
        return result;
    }
    public boolean isWon(int posX,int posY,String ico){
            //判断输赢
        int startX =0;
        int startY = 0;
        int endX = Chessboard.BOARD_SIZE-1;
        int endY = endX;
        int sameCount = 0;
        int temp =0;
        temp = posX -WIN_COUNT +1;
         startX = temp<0?0:temp;
        temp = posY - WIN_COUNT +1;
        startY = temp<0?0:temp;
        temp = posX +WIN_COUNT -1;
        endX = temp>Chessboard.BOARD_SIZE - 1?Chessboard.BOARD_SIZE-1:temp;
        temp = posY +WIN_COUNT -1;
        endY = temp>Chessboard.BOARD_SIZE -1?Chessboard.BOARD_SIZE-1:temp;
        String[][] board = chessboard.getBoard();
        for (int i =startY;i<endY;i++){
            if (board[posX][i]==ico&&board[posX][i+1]==ico){
                sameCount++;
            }else if (sameCount != WIN_COUNT-1){
                sameCount = 0;
            }
        }
        if (sameCount == 0){
            for (int i=startX;i<endX;i++){
                if (board[i][posY]==ico&&board[i+1][posY] == ico){
                    sameCount++;
                }else if (sameCount != WIN_COUNT -1){
                    sameCount = 0;
                }
            }
        }
        if (sameCount == 0) {

            int j = startY;
            for (int i = startX; i < endX; i++) {
                if (j < endY) {
                    if (board[i][j] == ico && board[i + 1][j + 1] == ico) {
                        sameCount++;
                    } else if (sameCount != WIN_COUNT - 1) {
                        sameCount = 0;
                    }
                    j++;
                }
            }
        }
        return sameCount == WIN_COUNT -1 ? true:false;
    }

    public static void main(String[] args) throws Exception {
        GobangGame gobangGame = new GobangGame(new Chessboard());
        gobangGame.start();
    }
}
