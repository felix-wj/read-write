package cn.wangjie.learn.leetcode;


import org.junit.Test;

import java.util.*;

/**
 * @program: learn
 * @description: 无重复最长子串
 * @author: WangJie
 * @create: 2019-06-05 09:22
 **/
public class LengthOfLongestSubstring {


    @Test
    public void test() {
        String str = "atatb";
        System.out.println(lengthOfLongestSubstring2(str));
    }

    //滑动窗口解法，首先取[i,j)(i=0,j=0),然后向右滑动(j递增)，直到[i,j)出现重复，记下长度最为初始最大长度，
    //然后i++,即左端向右滑动，重复上诉操作
    public int lengthOfLongestSubstring(String s) {
        int i ,j;
        i=j=0;
        Set<Character> set = new HashSet<>();
        int len ,maxLen;
        len = maxLen = 0;
        while (i<s.length()&&j<s.length()){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                len = j-i;
                if (maxLen<len){
                    maxLen=len;
                }
                continue;
            }
            set.remove(s.charAt(i++));
        }
        return maxLen;
    }

    //优化：在左端向右滑动时，其实可以找到[i,j)中重复字符首次出现的位置n,[n+1,j)继续滑动,重复上述操作
    public int lengthOfLongestSubstring2(String s){
        int[] index = new int[128];
        int len  = 0;
        int j=0;
        for(int i=0;i<s.length();i++){
            j = Math.max(index[s.charAt(i)],j);
            len = Math.max(len,i-j+1);
            index[s.charAt(i)]=i+1;
        }
        return len;
    }
}
