package io.test.nio;

import java.nio.ByteBuffer;

public class NioTest5 {

	public static void main(String[] args) {
		try {
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);

			for (int i = 0; i < byteBuffer.capacity(); i++) {
				byteBuffer.put((byte) i);
			}

			byteBuffer.position(2);
			byteBuffer.limit(6);

			ByteBuffer buffer = byteBuffer.slice();
			for (int i = 0; i < buffer.capacity(); i++) {
				byte b = buffer.get(i);
				b *= 2;
				buffer.put(b);
			}

			byteBuffer.position(0);
			byteBuffer.limit(byteBuffer.capacity());

			while (byteBuffer.hasRemaining()) {
				System.out.println(byteBuffer.get());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
