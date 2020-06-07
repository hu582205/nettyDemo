package io.test.decrator;

/**
 * decrator
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-07
 * @see MakeCup
 */
public class Decorator implements MakeCup {

	private MakeCup makeCup;

	public Decorator(MakeCup makeCup) {
		this.makeCup = makeCup;
	}

	@Override
	public void makeCup() {
		makeCup.makeCup();
	}
}
