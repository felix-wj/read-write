package cn.wangjie.learn.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @program: learn
 * @description: 找出两个有序数组的中位数
 * @author: WangJie
 * @create: 2019-06-13 17:34
 **/
@Slf4j
public class FindMedianInTwoSortedArrays {
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        if (nums1.length == 0) {
            if (nums2.length % 2 == 1) {
                return nums2[nums2.length / 2];
            } else {
                return (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0D;
            }
        }
        int i,j,half = (nums1.length+nums2.length+1)/2;
        int iMax = nums1.length,iMin = 0;
        while(true){
            i = (iMin+iMax)/2;
            j=half-i;
            if (i>0&&nums1[i-1]>nums2[j]){
                iMax--;
                continue;
            }
            if (i<nums1.length&&nums2[j-1]>nums1[i]){
                iMin++;
                continue;
            }
            break;
        }
        int left, right;
        if (i == 0) {
            left = nums2[j - 1];
        } else if (j == 0) {
            left = nums1[i - 1];
        } else {
            left = Math.max(nums1[i - 1], nums2[j - 1]);
        }
        if ((nums1.length + nums2.length) % 2 == 1) {
            return left;
        }
        if (i == nums1.length) {
            right = nums2[j];
        } else if (j == nums2.length) {
            right = nums1[i];
        } else {
            right = Math.min(nums1[i], nums2[j]);
        }
        return (left + right) / 2.0D;
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        if (nums1.length==0){
            if (nums2.length%2==1){
                return nums2[nums2.length/2];
            }else {
                return (nums2[nums2.length/2]+nums2[nums2.length/2-1])/2.0D;
            }
        }
        int i = index(0, nums1.length, nums1, nums2);
        int j = (nums1.length + nums2.length + 1) / 2 - i;
        int left, right;
        if (i == 0) {
            left = nums2[j - 1];
        } else if (j == 0) {
            left = nums1[i - 1];
        } else {
            left = Math.max(nums1[i - 1], nums2[j - 1]);
        }
        if ((nums1.length + nums2.length) % 2 == 1) {
            return left;
        }
        if (i == nums1.length) {
            right = nums2[j];
        } else if (j == nums2.length) {
            right = nums1[i];
        } else {
            right = Math.min(nums1[i], nums2[j]);
        }
        return (left + right) / 2.0D;
    }

    private int index(int start, int end, int[] nums1, int[] nums2) {
        System.out.println("start:" + start + ",end:" + end);
        int i = (start + end) / 2;
        int j = (nums1.length + nums2.length + 1) / 2 - i;
        if (i < nums1.length && (nums2[j - 1] > nums1[i])) {
            return index(i + 1, end, nums1, nums2);
        }
        if (i > 0 && (nums1[i - 1] > nums2[j])) {
            return index(start, i - 1, nums1, nums2);
        }
        return i;
    }

    @Test
    public void test() {
        int[] num1 = {1, 2, 3};
        int[] num2 = {2, 5, 7, 9};
        System.out.println(findMedianSortedArrays2(num1, num2));
        int[] num3 = {1,2};
        int[] num4 = {3,4};
        System.out.println(findMedianSortedArrays2(num3, num4));
        int[] num5 = {};
        System.out.println(findMedianSortedArrays2(num5, num4));
    }
}
