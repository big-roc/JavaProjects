package demo.udf;

import java.util.ArrayList;

/**
 * 类名称:Temp
 * 类描述:一句话描述该类的功能
 * 创建人:roc
 * 创建时间:2020/11/16 17:33
 * 版本:v1.0
 */
public class Temp {
    public static void main(String[] args) {
        String s = "长度“*”宽度“*”引线间隔";
        String[] res = s.split("“\\*”");
        ArrayList<String> result = new ArrayList<>();
        for (String ss : res) {
            result.add(ss);
            result.add("*");
        }
        if (result.get(result.size() - 1).equals("*")) {
            result.remove(result.size() - 1);
        }
        for (String aa : result) {
            System.out.println(aa);
        }
    }
}
