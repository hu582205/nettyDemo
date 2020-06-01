package io.test.five;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-31
 * @see ChannelInboundHandlerAdapter
 */
public class FiveNettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		System.out.println("接收到客户端消息：" + msg.text());

		ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器返回：" + LocalDateTime.now()));
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端建立连接" + ctx.channel().id().asLongText());
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端断开连接：" + ctx.channel().id().asLongText());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
