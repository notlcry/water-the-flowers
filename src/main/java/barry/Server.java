package barry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Future;

/**
 * Echoes back any received data from a client.
 */

@Service
public class Server {

    @Autowired
    public ServerInitializer serverInitializer;
    private ChannelFuture serverChFuture;

//    @PostConstruct
//    public void init(){
//        try {
//            start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Async
    public Future start() throws Exception {
        {
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                ServerBootstrap b = new ServerBootstrap();
                b.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(serverInitializer);


                serverChFuture = b.bind(8007).sync();
                Thread.sleep(600*1000L);
//                b.bind(8007).sync().channel().closeFuture().sync();
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }
        return null;
    }


//    @PreDestroy
//    public void stop(){
//        try {
//            serverChFuture.channel().closeFuture().sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}