package io.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest8 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(addr);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];
        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long r = socketChannel.read(byteBuffers);
                byteRead += r;
                System.out.println("byteRead: "+ byteRead);

                Arrays.asList(byteBuffers).forEach(byteBuffer -> {
                    System.out.println("limit:" + byteBuffer.limit() +"===========|||position"+byteBuffer.position());
                });
            }
            Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);

            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long r = socketChannel.write(byteBuffers);
                byteWrite += r;
            }

            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);


        }

    }
}
