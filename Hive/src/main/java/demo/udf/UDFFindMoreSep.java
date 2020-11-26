package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 类名称:UDFSplit
 * 类描述:多分隔符拆分：“\\*”、“-”、\\~
 * 创建人:roc
 * 创建时间:2020/11/16 16:50
 * 版本:v1.0
 */
public class UDFFindMoreSep extends UDF {
    public String evaluate(String s) {
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
            String result = "";
            if (s.contains(sep1)) {
                result = "*";
            } else if (s.contains(sep2)) {
                result = "-";
            } else if (s.contains(sep3)) {
                result = "~";
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
