package io.test.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 聊天服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-30
 * @see SimpleChannelInboundHandler
 */
public class ThdMyChatServerHandler extends SimpleChannelInboundHandler<String> {

	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.forEach(ch -> {
			if (channel != ch) {
				ch.writeAndFlush(channel.remoteAddress() + "发送消息：" + msg + "\n");
			} else {
				channel.writeAndFlush("【自己】发送:" + msg + "\n");
			}
		});

	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("【服务器】用户IP：" + channel.remoteAddress() + "已上线\n");
		channelGroup.add(channel);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		channelGroup.writeAndFlush("【服务器】用户IP：" + channel.remoteAddress() + "已下线\n");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println("用户IP：" + channel.remoteAddress() + "已上线\n");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Channel channel = ctx.channel();
		System.out.println("用户IP：" + channel.remoteAddress() + "已下线\n");
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
