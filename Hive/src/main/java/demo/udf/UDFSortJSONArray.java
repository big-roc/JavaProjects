package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 类名称:UDFJsonArray
 * 类描述:解析JSONArray并排序
 * 创建人:roc
 * 创建时间:2020/11/10 17:48
 * 版本:v1.0
 */

public class UDFSortJSONArray extends UDF {
    static final Log LOG = LogFactory.getLog(UDFSortJSONArray.class.getName());

    public Map<Integer, String> evaluate(String JSONString) {
        LOG.info("hello");
        //判断JSONString是否为null
        if (JSONString == null) {
            return null;
        }

        //判断JSONString是否为空字符串
        if (JSONString.equals("")) {
            return null;
        }

        try {
            JSONArray extractObj = new JSONArray(JSONString);
            Map<Integer, String> result = new HashMap<>();
            for (int i = 0; i < extractObj.length(); ++i) {
                JSONObject jsonObject = extractObj.getJSONObject(i);
                if (!jsonObject.toString().equals("{}")) {
                    String synonymName = jsonObject.getString("synonymName");
                    Integer rank = i + 1;
                    result.put(rank, synonymName);
                } else {
                    System.out.println(jsonObject);
                }
            }
            return result;
        } catch (JSONException e) {
            System.out.println("JSO符串格式错误！");
            return null;
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("空指针异常：" + e);
            return null;
        }

    }
}
