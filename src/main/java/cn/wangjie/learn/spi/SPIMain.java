package cn.wangjie.learn.spi;

import java.util.*;

public class SPIMain {
	public static void main(String[] args) {
		ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
		for (IShout shout : shouts) {
			shout.shout();
		}
	}
}
