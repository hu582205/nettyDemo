package io.test.third.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 网状的客户
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-05-30
 */
public class ThdNettyClient {

	public static void main(String[] args) {

		EventLoopGroup clientGroup = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(clientGroup).channel(NioSocketChannel.class).handler(new ThdNettyClientInitializer());

			Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			clientGroup.shutdownGracefully();
		}

	}
}
