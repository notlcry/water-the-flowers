package barry;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Handler implementation for the echo server.
 */
@Sharable
@Component
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private ChannelHandlerContext ctx;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        System.out.println(msg);
        logger.info("Rev: " + msg);
//        ctx.write("OK");
        ctx.flush();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        super.channelActive(ctx);
    }

    public void sendStart() {
        System.out.println("Start Watering");
        if (ctx != null) {
            ctx.write("START");
            ctx.flush();
        }else{
            System.out.println("CTX is Null");
        }
    }

    public void sendCheck() {
        System.out.println("Check Status");
        if (ctx != null) {
            ctx.write("CHECK");
            ctx.flush();
        }else{
            System.out.println("CTX is Null");
        }
    }

    public void sendStop() {
        System.out.println("Stop Watering");
        if (ctx != null) {
            ctx.write("STOP");
            ctx.flush();
        }else{
            System.out.println("CTX is Null");
        }
    }

}