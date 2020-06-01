package io.test.oneofproto.client;

import java.util.Random;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.test.oneofproto.OneOfMessage;

/**
 * 我的服务器处理程序
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-30
 * @see SimpleChannelInboundHandler
 */
public class ProtoClientHandler extends SimpleChannelInboundHandler<OneOfMessage.MyMessage> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, OneOfMessage.MyMessage msg) throws Exception {
		System.out.println("客户端接收数据：" + msg);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		int type = new Random().nextInt(3);
		OneOfMessage.MyMessage msg;

		if (type == 0) {
			msg = OneOfMessage.MyMessage.newBuilder().setDataType(OneOfMessage.MyMessage.MessageType.StudentType)
					.setStudent(OneOfMessage.Student.newBuilder().setName("我是一个学生").setAddress("中国").setAge(20).build())
					.build();
		} else if (type == 1) {
			msg = OneOfMessage.MyMessage.newBuilder().setDataType(OneOfMessage.MyMessage.MessageType.SchoolType)
					.setSchool(OneOfMessage.School.newBuilder().setName("我是学校").build()).build();
		} else {
			msg = OneOfMessage.MyMessage.newBuilder().setDataType(OneOfMessage.MyMessage.MessageType.TeacherType)
					.setTeacher(OneOfMessage.Teacher.newBuilder().setName("我是老师").setAge(30).build()).build();
		}

		ctx.channel().writeAndFlush(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 异常处理
		cause.printStackTrace();
		ctx.close();
	}
}
