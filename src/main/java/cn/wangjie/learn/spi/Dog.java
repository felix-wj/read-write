package cn.wangjie.learn.spi;

public class Dog implements IShout{
	@Override
	public void shout() {
		System.out.println("dog shout:汪汪汪");
	}
}
