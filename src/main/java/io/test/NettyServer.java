package io.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 网状的服务器
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-29
 */
public class NettyServer {

	public static void main(String[] args) {
		// 处理链接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 具体的业务
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 服务器
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new NettyServerInitializer());

			// 绑定端口
			ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
			// 关闭通道
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

}
