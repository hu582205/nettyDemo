package io.test.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class NioTest7 {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest7.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, fileChannel.size());

        mappedByteBuffer.put(2, (byte) 'b');
        mappedByteBuffer.put(4, (byte) 'd');

        randomAccessFile.close();

    }


}
