package io.test.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioChatClientDemo {

	public static void main(String[] args)  {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);

			Selector selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8899);
			socketChannel.connect(socketAddress);
			while (true) {
				System.out.println("事件总数开始" );
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				System.out.println("事件总数" + selectionKeys.size());

				for (SelectionKey selectionKey : selectionKeys) {
					if (selectionKey.isConnectable()) {
						SocketChannel client = (SocketChannel) selectionKey.channel();

						if (client.isConnectionPending()) {
							client.finishConnect();

							// 与服务器通讯
							ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
							byteBuffer.put((LocalDateTime.now() + "连接成功！").getBytes());
							byteBuffer.flip();
							client.write(byteBuffer);

							// 启用一个线程，等待用户输入数据与服务器通讯
							ExecutorService executorService = Executors
									.newSingleThreadExecutor(Executors.defaultThreadFactory());
							executorService.submit(() -> {
								while (true) {
									byteBuffer.clear();
									InputStreamReader inputStreamReader = new InputStreamReader(System.in);
									try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
										String message = bufferedReader.readLine();

										byteBuffer.put(message.getBytes());
										byteBuffer.flip();
										client.write(byteBuffer);
									}

								}
							});
						}
						client.register(selector, SelectionKey.OP_READ);
					} else if (selectionKey.isReadable()) {
						SocketChannel client = (SocketChannel) selectionKey.channel();

						ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
						int read = client.read(byteBuffer);
						if (read > 0) {
							String messages = new String(byteBuffer.array(), 0, read);
							System.out.println(messages);
						}
					}
				}
				selectionKeys.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
