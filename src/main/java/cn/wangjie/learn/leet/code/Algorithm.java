package cn.wangjie.learn.leet.code;

/**
 * @program: learn
 * @description: 算法
 * @author: WangJie
 * @create: 2020-01-30 22:46
 **/
public class Algorithm {

    /**
     * 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int i = 0;
        for (int num : nums) {
            i ^=num;
        }
        return i;
    }

    /**
     * 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int i , j;
        i = 0;
        j = nums[0];
        for (int num : nums) {
            if (j== num){
                i++;
            }else {
                if (i==0){
                    j = num;
                    i = 1;
                }else {
                    i--;
                }
            }
        }
        return j;
    }
}
