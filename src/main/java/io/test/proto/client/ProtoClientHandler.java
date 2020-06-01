package io.test.proto.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.test.proto.DataInfo;

/**
 * 我的服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-30
 * @see SimpleChannelInboundHandler
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
		System.out.println("客户端接收数据：" + msg);
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		DataInfo.Student student = DataInfo.Student.newBuilder().setName("李四").setAddress("中国天津").setAge(1).build();
		ctx.channel().writeAndFlush(student);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 异常处理
		cause.printStackTrace();
		ctx.close();
	}
}
