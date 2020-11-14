package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名称:UDFFestivalType
 * 类描述:Hive UDF函数
 * 创建人:roc
 * 创建时间:2020-10-29 17:30
 * 版本:v1.0
 */
public class UDFFestivalType extends UDF {

    private HashMap<String, String> festivalMap = new HashMap<>();

    private UDFFestivalType() throws Exception {
        InputStreamReader propFile = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("festival.properties")), "UTF-8");
        Properties prop = new Properties();
        prop.load(propFile);
        for (Object key : prop.keySet()) {
            festivalMap.put(key.toString(), prop.getProperty(key.toString()));
        }
    }

    //解决Double转String的科学计数法问题
    private String double2string(double dou) {
        Double dou_obj = dou;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(dou_obj);
    }

    public String evaluate(double date_dou) {
        String date_str = this.double2string(date_dou);
        return evaluate(date_str);
    }

    private int evaluate(double date_dou, String flag) {
        String date_str = this.double2string(date_dou);
        return evaluate(date_str, flag);
    }

    private String evaluate(String date_str) {
        if (!this.match_date(date_str).equals("null")) {
            date_str = this.match_date(date_str);
            return festivalMap.get(date_str) == null ? "null" : festivalMap.get(date_str);
        } else {
            return "null";
        }
    }

    private int evaluate(String date_str, String flag) {
        if (flag.equals("count") && !this.match_date(date_str).equals("null")) {
            date_str = this.match_date(date_str);
            return festivalMap.get(date_str) == null ? 0 : 1;
        } else {
            return 0;
        }
    }

    private String match_date(String date_str) {
        //匹配20160101这种日期格式
        Pattern pat_common = Pattern.compile("\\d{8}");
        Matcher mat_common = pat_common.matcher(date_str);

        //匹配2016-01-01这种日期格式
        Pattern pat_strike = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher mat_strike = pat_strike.matcher(date_str);

        //匹配 2016-01-01 10:35:46 这种日期格式
        Pattern pat_colon = Pattern.compile("\\d{4}-\\d{2}-\\d{2}(\\s)+\\d{2}:\\d{2}:\\d{2}");
        Matcher mat_colon = pat_colon.matcher(date_str);

        if (mat_colon.find()) {
            return date_str.replace("-", "").substring(0, 8);
        } else if (mat_strike.find()) {
            return date_str.replace("-", "");
        } else if (mat_common.find()) {
            return date_str;
        } else {
            return "null";
        }
    }

    //测试的main方法
    public static void main(String[] args) throws Exception {
        UDFFestivalType fes = new UDFFestivalType();
        String date_str = "20150101";
        System.out.println(fes.evaluate(date_str));

        String date_str1 = "20160101";
        String result = fes.evaluate(date_str1);
        System.out.println("result is:" + result);

        double date_dou = 20160101;
        int result_dou = fes.evaluate(date_dou, "count");
        System.out.println(result_dou);
        System.out.println(date_dou);
    }
}