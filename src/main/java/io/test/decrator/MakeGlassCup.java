package io.test.decrator;

/**
 * 使玻璃杯子
 *
 * @author hxh
 * @version 1.0.0
 * @date 2020-06-07
 * @see MakeCup
 */
public class MakeGlassCup implements MakeCup {
    @Override
    public void makeCup() {
        System.out.println("生成一个玻璃杯");
    }
}
