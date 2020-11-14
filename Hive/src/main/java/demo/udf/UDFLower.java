package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 类名称:Lower
 * 类描述:Hive UDF函数
 * 创建人:roc
 * 创建时间:2020-10-28 10:00
 * 版本:v1.0
 */
public class UDFLower extends UDF {
    public String evaluate(String s) {
        if (s == null) {
            return null;
        }
        return s.toLowerCase();
    }
}
