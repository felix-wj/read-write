package cn.wangjie.learn.jvm.unit3;

/**
 * @program: learn
 * @description: finalize方法作用
 * @author: WangJie
 * @create: 2019-09-29 22:35
 **/
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOCK= null;

    public void isAlive(){
        System.out.println("Yes,I'm still alive :)");
    }
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGC.SAVE_HOCK = this;
    }
    public static void main(String[] args) throws InterruptedException {
        SAVE_HOCK = new FinalizeEscapeGC();
        //第一次成功拯救自己
        SAVE_HOCK = null;
        System.gc();
        //finalize()方法优先级低，休眠等待执行
        Thread.sleep(500);
        if (SAVE_HOCK!=null){
            SAVE_HOCK.isAlive();
        }else {
            System.out.println("no,I'm dead :(");
        }

        //第二次拯救自己，失败
        SAVE_HOCK = null;
        System.gc();
        //finalize()方法优先级低，休眠等待执行
        Thread.sleep(500);
        if (SAVE_HOCK!=null){
            SAVE_HOCK.isAlive();
        }else {
            System.out.println("no,I'm dead :(");
        }
    }

}
