package cht.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenhantao
 * @since 2018/10/26
 */
public class Test {
    public static void main(String[] args){
        String str = "[\\w.]*\\.txt";
        boolean temp = Pattern.matches(str, "123.txt");
        System.out.println(temp);
    }
}
