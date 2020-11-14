package demo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 类名称:UDFFindJSONArray
 * 类描述:自定义函数从JSONArray中查找需要的值
 * 创建人:roc
 * 创建时间:2020/11/11 9:29
 * 版本:v1.0
 */
public class UDFFindFromJSONArray extends UDF {
    public String evaluate(String JSONString, String parameter) {
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
            String result = "";
            for (int i = 0; i < extractObj.length(); ++i) {
                JSONObject jsonObject = extractObj.getJSONObject(i);
                String parameters = jsonObject.getString("parameters");
                String property_value = jsonObject.getString("property_value");
                if (parameters.equals(parameter)) {
                    result = property_value;
                    break;
                }
            }
            return result;
        } catch (JSONException e) {
            System.out.println("JSON字符串格式错误！");
            return null;
        } catch (NumberFormatException e) {
            return null;
        }

    }
}
