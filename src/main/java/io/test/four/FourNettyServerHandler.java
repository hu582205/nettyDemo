package io.test.four;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 网状的服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-31
 * @see ChannelInboundHandlerAdapter
 */
public class FourNettyServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			String eventType;
			switch (idleStateEvent.state()) {
			case READER_IDLE:
				eventType = "读空闲";
				break;
			case WRITER_IDLE:
				eventType = "写空闲";
				break;
			case ALL_IDLE:
				eventType = "读写空闲";
				break;
			default:
				eventType = "无事件";
			}
			System.out.println(ctx.channel().remoteAddress() + "触发响应事件： " + eventType);
			ctx.channel().close();
		}
	}
}
