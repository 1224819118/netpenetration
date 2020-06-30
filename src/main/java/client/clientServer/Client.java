package client.clientServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Client {
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ServerBootstrap bootstrap;
    private ChannelFuture future;

    public void start(){
        try {
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new channelInitializer());
            future = bootstrap.bind(9010).sync();
            future.channel().closeFuture();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void stop(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
