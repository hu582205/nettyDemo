package io.test.decrator;

/**
 * 测试decator
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-07
 */
public class TestDecoator {

    public static void main(String[] args) {
        MakeCup makeCup = new AddCatMakeCup(new AddColorMakeCup(new MakeGlassCup()));
        makeCup.makeCup();

    }
}
