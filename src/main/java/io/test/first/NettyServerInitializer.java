package io.test.first;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 网状的服务器初始化
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-29
 * @see ChannelInitializer
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline channelPipeline = ch.pipeline();

		channelPipeline.addLast("httpServerCodec", new HttpServerCodec());
		channelPipeline.addLast("nettyServerHandler", new NettyServerHandler());
	}
}
