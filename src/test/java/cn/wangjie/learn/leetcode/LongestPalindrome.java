package cn.wangjie.learn.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: learn
 * @description: 最长回文子串
 * @author: WangJie
 * @create: 2019-06-19 10:40
 **/
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s==null || "".equals(s)){
            return s;
        }
        int i =1;
        List<Integer> palindromes = new ArrayList<>();
        List<Integer> palindromes2 = new ArrayList<>();
        int mid = 0;
        int len = 0;
        int l = 1;
        int mid2 = 0;
        int len2 = 0;
        while (i<s.length()){
            if (i+1<s.length()&&s.charAt(i-l)==s.charAt(i+l)){
                palindromes.add(i);
                mid=i;
                len=1;
            }
            if (s.charAt(i)==s.charAt(i-1)){
                palindromes2.add(i);
                mid2 = i;
            }
            i++;
        }
        while (!palindromes.isEmpty()){
            l++;
            Iterator<Integer> iterator = palindromes.iterator();
            while (iterator.hasNext()){
                i = iterator.next();
                if (i-l>=0 && i+l<s.length() && s.charAt(i-l)==s.charAt(i+l)){
                    mid = i;
                    len = l;
                }else {
                    iterator.remove();
                }
            }
        }

        l=0;
        while (!palindromes2.isEmpty()){
            l++;
            Iterator<Integer> iterator =palindromes2.iterator();
            while (iterator.hasNext()){
                i = iterator.next();
                if (i-1-l>=0&& i+l<s.length()&&s.charAt(i-1-l)==s.charAt(i+l)){
                    mid2 = i;
                    len2 = l;
                }else {
                    iterator.remove();
                }
            }
        }
        if (mid2==0||1+2*len>=2+2*len2){
            return s.substring(mid-len,mid+len+1);
        }
        return s.substring(mid2-len2-1,mid2+len2+1);
    }

    @Test
    public void test(){
        String s = "a12a21at";
        System.out.println(longestPalindrome2(s));
    }

    public String longestPalindrome2(String s) {
        if (s==null || s.equals("")){
            return s;
        }
        StringBuilder sb = new StringBuilder("#");
        for(char a : s.toCharArray()){
            sb.append(a).append("#");
        }
        int[] p = new int[sb.length()];
        int id = 0;
        int mx = 0;
        int len = 0;
        int start = 0;
        int end = 0;
        for (int i = 0;i<sb.length();i++){
            if (i<mx){
                p[i] = Math.min(p[2*id-i],mx-i);
            }else {
                p[i]=1;
            }
            if (mx<= p[i]+i){
                while(p[i]+i<sb.length()&&i-p[i]>=0&&sb.charAt(p[i]+i)==sb.charAt(i-p[i])){
                    p[i]++;
                }
                mx = i+p[i];
                id = i;
                if (p[i]-1>len){
                    len = p[i]-1;
                    start = i-p[i]+1;
                    end = i+p[i];
                }
            }
        }
        return sb.substring(start,end).replaceAll("#","");
    }
}
