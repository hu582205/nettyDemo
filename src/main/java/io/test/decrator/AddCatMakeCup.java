package io.test.decrator;

public class AddCatMakeCup extends Decorator {

	public AddCatMakeCup(MakeCup makeCup) {
		super(makeCup);
	}

	@Override
	public void makeCup() {
		super.makeCup();
		addCat();
	}

	private void addCat() {
		System.out.println("加入花猫图片");
	}
}
