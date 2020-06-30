package server.server;

import server.bean.socketConfig;
import server.config.mainConfig;
import server.util.serverSocketThread;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.FutureTask;

public class handler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        Integer id = mainConfig.configMap.get("/test");
        socketConfig socketConfig = mainConfig.socketMap.get(id);
        FutureTask<ByteBuf> task = new FutureTask<ByteBuf>(new serverSocketThread(socketConfig,msg));
        Thread thread = new Thread(task);
        thread.start();
        ByteBuf byteBuf = task.get();
        thread.join();
        ctx.writeAndFlush(byteBuf);
        ctx.channel().close().sync();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
