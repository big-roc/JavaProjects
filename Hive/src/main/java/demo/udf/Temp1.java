package demo.udf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类名称:Temp
 * 类描述:一句话描述该类的功能
 * 创建人:roc
 * 创建时间:2020/11/16 17:33
 * 版本:v1.0
 */
public class Temp1 {
    public static void main(String[] args) {
        try {
            String str = "[{\"synonymName\":\"尺寸\"},{\"synonymName\":\"大小/尺寸\"},{\"synonymName\":\"螺钉尺寸\"},{\"synonymName\":\"螺丝尺寸\"},{\"synonymName\":\"螺纹尺寸\"},{\"synonymName\":\"铆钉直径“*”铆钉长度\"},{\"synonymName\":\"安装\"},{\"synonymName\":\"端接类型\"},{\"synonymName\":\"安装风格\"},{\"synonymName\":\"安装类型\"},{\"synonymName\":\"安装孔大小\"},{\"synonymName\":\"大小\"},{\"synonymName\":\"封装\"},{\"synonymName\":\"螺丝/螺纹大小\"},{\"synonymName\":\"安装方法\"},{\"synonymName\":\"直径“*”长度\"},{}]";
            JSONArray obj = new JSONArray(str);
            for (int i = 0; i < obj.length(); ++i) {
                JSONObject jsonObject = obj.getJSONObject(i);
                if (jsonObject != null && !jsonObject.toString().equals("{}")) {
                    System.out.println(jsonObject);
                    String synonymName = jsonObject.getString("synonymName");
                }
            }
        } catch (JSONException er) {
            System.out.println(er);
        }
    }
}
