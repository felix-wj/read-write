package cn.wangjie.learn.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @program: spring-learn
 * @description:
 * @author: WangJie
 * @create: 2020-04-23 21:35
 **/
@Slf4j
public class ReadAndWriteThread extends Thread {
    private String name;
    private InputStream in ;
    private OutputStream out ;
    private static final int SIZE=1024*1024;

    public ReadAndWriteThread(String name ,InputStream in, OutputStream out) {
        this.name = name;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        byte[] b = new byte[SIZE];
        try {
            while (in.read(b)>1){
                System.out.print(name);
                System.out.println(b.toString());
                out.write(b);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
