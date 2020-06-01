package io.test.proto;

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
public class ProtoNettyServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());
        System.out.println(msg.getName());
    }
}
