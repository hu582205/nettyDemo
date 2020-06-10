package io.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * nio聊天服务器演示
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-09
 */
public class NioChatServerDemo {

	private static Map<String, SocketChannel> channelMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(8899));

		Selector selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			selector.select();
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			selectionKeys.forEach(selectionKey -> {

				if (selectionKey.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
					try {
						SocketChannel client = server.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);

						String key = "client:" + UUID.randomUUID();
						channelMap.put(key, client);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else if (selectionKey.isReadable()) {
					SocketChannel cleint = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

					try {
						int read = cleint.read(byteBuffer);
						if (read > 0) {
							byteBuffer.flip();

							Charset charset = Charset.forName("utf-8");
							String message = String.valueOf(charset.decode(byteBuffer).array());
							System.out.println(cleint + "|:|" + message);

							String senderKey = null;
							for (Map.Entry<String, SocketChannel> channelEntry : channelMap.entrySet()) {
								if (channelEntry.getValue() == cleint) {
									senderKey = channelEntry.getKey();
									break;
								}
							}

							for (Map.Entry<String, SocketChannel> channelEntry : channelMap.entrySet()) {
								SocketChannel socketChannel = channelEntry.getValue();
								ByteBuffer buffer = ByteBuffer.allocate(1024);
								buffer.put((senderKey + ":" + message).getBytes());
								buffer.flip();
								socketChannel.write(buffer);
							}

						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			selectionKeys.clear();
		}

	}
}
