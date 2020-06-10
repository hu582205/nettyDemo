package io.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest9 {

	public static void main(String[] args) throws IOException {
		int[] ports = new int[5];
		ports[0] = 5001;
		ports[1] = 5002;
		ports[2] = 5003;
		ports[3] = 5004;
		ports[4] = 5005;

		Selector selector = Selector.open();

		for (int i = 0; i < ports.length; i++) {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			ServerSocket socket = serverSocketChannel.socket();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(ports[i]);
			socket.bind(inetSocketAddress);

			// 注册Selector
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			System.out.println("监听端口：" + ports[i]);
		}

		while (true) {
			int numbers = selector.select();
			System.out.println("numbers : " + numbers);
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			System.out.println("selectKeys : " + selectionKeys);

			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();

				if (key.isAcceptable()) {
					// 可读事件
					ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					// 在Selector中注册新的可读事件
					socketChannel.register(selector, SelectionKey.OP_READ);
					// 接收到可读事件，删除Selector Set中的接收等待事件，
					iterator.remove();
					System.out.println("获得客户端连接" + socketChannel);
				} else if (key.isReadable()) {
					SocketChannel socketChannel = (SocketChannel) key.channel();

					while (true) {
						ByteBuffer byteBuffer = ByteBuffer.allocate(512);
						int byteRead = socketChannel.read(byteBuffer);
						if (byteRead <= 0) {
							break;
						}
						byteBuffer.flip();
						socketChannel.write(byteBuffer);
					}
					System.out.println("读取数据来自：" + socketChannel);
					iterator.remove();
				}
			}

		}

	}
}
