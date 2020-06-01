package io.test.oneofproto.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 网状的客户
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-01
 */
public class ProtoNettyClient {

    public static void main(String[] args) {

        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(clientGroup).channel(NioSocketChannel.class).handler(new ProtoNettyClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientGroup.shutdownGracefully();
        }

    }
}
