package io.test.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {

	public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("NioTest04.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("NioTest05.txt");


        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true) {
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if (read <= 0) {
                break;
            }
            byteBuffer.flip();
            outChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
        
    }
}
