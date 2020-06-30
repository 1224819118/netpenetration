package client.util;

import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

public class UDPUtil {
    public static void sendUDPPacket(Object obj){
        NioDatagramChannel channel = new NioDatagramChannel();
        channel.connect(new InetSocketAddress("127.0.0.1",9010));
        System.out.println(channel.isConnected());
        channel.writeAndFlush(obj);
    }
}
