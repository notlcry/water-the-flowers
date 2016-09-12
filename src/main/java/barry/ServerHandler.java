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

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ChannelHandlerContext ctx = null;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("Rev: " + msg);
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

        if (ctx != null) {
            logger.warn("receive a new connection, but the last connection is online, disconnect it.");
            ctx.disconnect();
        }
        this.ctx = ctx;
        super.channelActive(ctx);
    }

    public void sendStart() {
        logger.info("receive start from web, Start Watering");
        if (ctx != null) {
            ctx.write("START");
            ctx.flush();
        }else{
            logger.warn("CTX is Null, no connection.");
        }
    }

    public void sendCheck() {
        logger.info("receive check from web, Check Status");
        if (ctx != null) {
            ctx.write("CHECK");
            ctx.flush();
        }else{
            logger.warn("CTX is Null, no connection.");
        }
    }

    public void sendStop() {
        logger.info("receive stop from web, Stop Watering");
        if (ctx != null) {
            ctx.write("STOP");
            ctx.flush();
        } else {
            logger.warn("CTX is Null, no connection.");
        }
    }

}