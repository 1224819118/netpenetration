package server.util;

import server.bean.socketConfig;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 这里使用callable加task来实现，因为我们需要内网中的client返回一个响应并将这个响应传递给proxyserver
 */
public class serverSocketThread implements Callable<ByteBuf> {
    public static List<Thread> threadList = new LinkedList<>();
    public ByteBuf buffer;
    socketConfig socketConfig;

    public serverSocketThread(server.bean.socketConfig socketConfig, ByteBuf buffer) {
        this.buffer=buffer;
        this.socketConfig = socketConfig;
    }

    @Override
    public ByteBuf call() throws Exception {
        while (true){
            try {
                Socket socket = new Socket(socketConfig.getHost(),socketConfig.getPort());
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                int len = buffer.readableBytes();
                byte[] bytes = new byte[len];
                buffer.readBytes(bytes);
                outputStream.write(bytes);
                buffer.clear();
                int code=0;
                while ((code=inputStream.read())!=-1){
                    buffer.writeByte(code);
                }
                //返回buffer，来作为响应
                return buffer;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
