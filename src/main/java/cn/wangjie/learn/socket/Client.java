package cn.wangjie.learn.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * @program: learn
 * @description:
 * @author: WangJie
 * @create: 2020-04-24 11:33
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setReuseAddress(true);
        socket.connect(new InetSocketAddress("localhost", 12345));
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello".getBytes());
        outputStream.flush();
        new Thread(() -> {
            byte[] bytes = new byte[1024];
            try {
                while (inputStream.read(bytes) > 0) {
                    System.out.println("客户端收到消息，写回");
                    outputStream.write(bytes);
                    outputStream.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
