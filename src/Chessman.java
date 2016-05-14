/**
 * Created by zseapeng on 2016/5/8.
 * 枚举类
 */
public enum  Chessman {
    BLACK("●"),WHITE("○");
    private String chessman;

   private Chessman(String chessman) {
        this.chessman = chessman;
    }

    public String getChessman(){
        //返回String类型的棋子实例
        return this.chessman;
    }

}
