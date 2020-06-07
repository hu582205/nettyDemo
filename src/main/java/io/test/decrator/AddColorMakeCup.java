package io.test.decrator;

public class AddColorMakeCup extends Decorator {

	public AddColorMakeCup(MakeCup makeCup) {
		super(makeCup);
	}

	@Override
	public void makeCup() {
		super.makeCup();
		addColor();
	}

	private void addColor() {
		System.out.println("加入颜色");
	}
}
