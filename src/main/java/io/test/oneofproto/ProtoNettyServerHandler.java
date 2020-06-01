package io.test.oneofproto;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 原型网状的服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-01
 * @see SimpleChannelInboundHandler
 */
public class ProtoNettyServerHandler extends SimpleChannelInboundHandler<OneOfMessage.MyMessage> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, OneOfMessage.MyMessage msg) throws Exception {
		if (msg.getDataType() == OneOfMessage.MyMessage.MessageType.StudentType) {
			System.out.println(msg.getStudent());
		} else if (msg.getDataType() == OneOfMessage.MyMessage.MessageType.SchoolType) {
			System.out.println(msg.getSchool());
		} else {
			System.out.println(msg.getTeacher());
		}

	}
}
