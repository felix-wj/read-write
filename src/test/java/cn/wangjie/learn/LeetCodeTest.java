package cn.wangjie.learn;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-02-26 20:23
 **/
public class LeetCodeTest {

    //两数之和

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
