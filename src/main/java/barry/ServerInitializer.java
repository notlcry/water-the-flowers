/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package barry;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Creates a newly configured {@link ChannelPipeline} for a server-side channel.
 */

@Component
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

	@Autowired
	public ServerHandler serverHandler;

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast("logging", new LoggingHandler());

		// 解码成消息对象
//		pipelinene.addLast("decoder", new MsgDecoder());
		// 根据消息对象编码成byte
//		pipeline.addLast("encoder", new MsgEncoder());

        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());

		// 超时关闭连接
		pipeline.addLast(new IdleStateHandler(0, 0, 90, TimeUnit.SECONDS));

		// 业务逻辑
		pipeline.addLast("handler", serverHandler);
	}
}
