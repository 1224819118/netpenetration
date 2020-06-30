package server.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ProxyServer {
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ServerBootstrap bootstrap;
    private ChannelFuture future;

    public void start(int port){
        try {
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new channelInitializer());
            future = bootstrap.bind(port).sync();
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
