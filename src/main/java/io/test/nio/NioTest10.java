package io.test.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class NioTest10 {

	public static void main(String[] args) throws IOException {

		String inputFile = "NioTest8.txt";
		String outFile = "NioTest9.txt";

		RandomAccessFile randomAccessFile = null;
		RandomAccessFile outputFile = null;
		try {
			randomAccessFile = new RandomAccessFile(inputFile, "r");
			outputFile = new RandomAccessFile(outFile, "rw");

			long fileLength = new File(inputFile).length();
			FileChannel inputFileChannel = randomAccessFile.getChannel();
			FileChannel outputFileChannel = outputFile.getChannel();

			MappedByteBuffer mappedByteBuffer = inputFileChannel.map(MapMode.READ_ONLY, 0, fileLength);

			Charset charset = Charset.forName("utf-8");
			CharsetEncoder encoder = charset.newEncoder();
			CharsetDecoder decoder = charset.newDecoder();

			CharBuffer charBuffer1 = decoder.decode(mappedByteBuffer);
			ByteBuffer byteBuffer = encoder.encode(charBuffer1);

			outputFileChannel.write(byteBuffer);

		} finally {

			randomAccessFile.close();
			outputFile.close();

		}

	}
}
