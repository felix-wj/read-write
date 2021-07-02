package cn.wangjie.learn;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {

    /**
     * @Author WangJie
     * @Description 只包含数字和英文逗号
     * @param      
     * @Date  2020/6/3 13:33
     */
    @Test
    public void testNumber(){
        String str = "xxx";
        String phoneString = "2 + 1 * x";
        // 提取数字
        // 1
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?( )*\\+( )*\\d+(\\.\\d+)?( )*\\*( )*(x|X)( )*$");
     //   Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(phoneString);
        while (matcher.find()){
            System.out.println(matcher.group());
        }


    }
    @Test
    public void splitNumber(){
        String str = "11+22*(x-33)";

    }
    @Test
    public void testNumberList(){
        System.out.println("zp-123".matches("([a-zA-Z]+-[0-9]+,)*[a-zA-Z]+-[0-9]+"));
        System.out.println("zp-123,3".matches("([a-zA-Z]+-[0-9]+,)*[a-zA-Z]+-[0-9]+"));
        System.out.println("zp-123,1a".matches("([a-zA-Z]+-[0-9]+,)*[a-zA-Z]+-[0-9]+"));
    }
    /**
     * @Author WangJie
     * @Description 取两者之间的字符，不包含边界
     * @param
     * @Date  2020/6/5 11:31
     */
    @Test
    public void testContain(){
        String pattern = "(?<=:).*?(?=/)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher("git@git.yupaopao.com:terminal/android/tangdou/tangguo.git");
        String splitStr = "terminal";
        while (m.find()){
            System.out.println(m.group());
        }


    }
}
