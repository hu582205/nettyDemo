package io.test.second.client;

import java.util.Date;

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
public class SedMyClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + ":::" + msg);
		System.out.println("客户端接收数据：" + msg);
		ctx.channel().writeAndFlush("客户端返回数据： 客户端时间" + new Date());
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().writeAndFlush("触发启动" + new Date());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 异常处理
		cause.printStackTrace();
		ctx.close();
	}
}
