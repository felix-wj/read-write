package cn.wangjie.learn.leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @program: learn
 * @description: 盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49

 * @author: WangJie
 * @create: 2019-07-16 17:07
 **/
public class BiggestContainerForWater {
    public int maxArea(int[] height) {
        int[][] areas = new int[height.length][height.length];
        int maxArea = 0;
        for (int i=0;i<height.length;i++){
            for(int j=0;j<height.length;j++){
                int max = Math.max(i,j);
                int min = Math.min(i,j);
                if (areas[min][max]==0) {
                    areas[min][max] = (max-min)*Math.min(height[i],height[j]);
                }
                maxArea=Math.max(maxArea,areas[min][max]);
            }
        }
        return maxArea;
    }
    @Test
    public void test(){
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
