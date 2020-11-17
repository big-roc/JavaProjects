package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 类名称:UDFSplit
 * 类描述:多分隔符拆分：“\\*”、“-”、\\~
 * 创建人:roc
 * 创建时间:2020/11/16 16:50
 * 版本:v1.0
 */
public class UDFSplitMoreSep extends UDF {
    public ArrayList<String> evaluate(String s) {

        String sep1 = "“*”";
        String sep2 = "“-”";
        String sep3 = "“~”";

        if (s == null) {
            return null;
        }
        if (s.equals("")) {
            return null;
        }
        try {
            ArrayList<String> result = new ArrayList<>();
            if (s.contains(sep1)) {
                split(s, sep1, result);
            } else if (s.contains(sep2)) {
                split(s, sep2, result);
            } else if (s.contains(sep3)) {
                split(s, sep3, result);
            } else {
                result.add(s);
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private void split(String s, String sep, ArrayList<String> res) {
        String[] arrayString = s.split(sep);
        res.addAll(Arrays.asList(arrayString));
    }
}
