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
public class UDFSplit extends UDF {
    public ArrayList<String> evaluate(String s) {
        if (s == null) {
            return null;
        }

        if (s.equals("")) {
            return null;
        }
        try {
            ArrayList<String> result = new ArrayList<>();
            if (s.contains("“*”")) {
                String[] arrayString = s.split("“*”");
                result.addAll(Arrays.asList(arrayString));
            } else if (s.contains("“-”")) {
                String[] arrayString = s.split("“-”");
                result.addAll(Arrays.asList(arrayString));
            } else if (s.contains("“~”")) {
                String[] arrayString = s.split("“~”");
                result.addAll(Arrays.asList(arrayString));
            } else {
                result.add(s);
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
