package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 类名称:UDFJsonArray
 * 类描述:解析JSONArray并排序
 * 创建人:roc
 * 创建时间:2020/11/10 17:48
 * 版本:v1.0
 */
public class UDFSortJSONArray extends UDF {
    public HashMap<Integer, String> evaluate(String JSONString) {
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
            HashMap<Integer, String> result = new HashMap<>();
            for (int i = 0; i < extractObj.length(); ++i) {
                JSONObject jsonObject = extractObj.getJSONObject(i);
                String synonymName = jsonObject.getString("synonymName");
                Integer rank = i + 1;
                result.put(rank, synonymName);
            }
            return result;
        } catch (JSONException e) {
            System.out.println("JSO符串格式错误！");
            return null;
        } catch (NumberFormatException e) {
            return null;
        }

    }
}
