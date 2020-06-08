package io.test.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * nio test1
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-07
 */
public class NioTest1 {

	public static void main(String[] args) {
		IntBuffer intBuffer = IntBuffer.allocate(10);

		for (int i = 0; i < 10; i++) {
			int randNumber = new SecureRandom().nextInt(20);
			intBuffer.put(randNumber);
		}
		intBuffer.flip();

		while (intBuffer.hasRemaining()) {
			System.out.println(intBuffer.get());
		}
	}
}
