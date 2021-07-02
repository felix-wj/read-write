package cn.wangjie.learn.spi;

public class Cat implements IShout{
	@Override
	public void shout() {
		System.out.println("cat shout:喵喵喵");
	}
}
