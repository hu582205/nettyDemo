package io.test.third.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 我的服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-30
 * @see SimpleChannelInboundHandler
 */
public class ThdMyClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + ":::" + msg);
		System.out.println("客户端接收数据：" + msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 异常处理
		cause.printStackTrace();
		ctx.close();
	}
}
