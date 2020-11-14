package demo.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称:SplitString
 * 类描述:拆分字符串，并且转换为多行
 * 创建人:roc
 * 创建时间:2020-10-28
 * 版本:v1.0
 */
public class SplitStringUDTF extends GenericUDTF {

    private ArrayList<String> outList = new ArrayList<>();

    public StructObjectInspector initialize(StructObjectInspector[] args) throws UDFArgumentException {
        //1.定义输出数据的列名和类型
        List<String> fieldNames = new ArrayList<>();
        List<ObjectInspector> fieldType = new ArrayList<>();

        //2.添加输出数据的列名和类型
        fieldNames.add("words");
        fieldType.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldType);
    }

    @Override
    public void process(Object[] args) throws HiveException {

        //1.获取原始数据
        String data = args[0].toString();

        //2.获取传入的第二个参数，此处为分隔符
        String splitSymbol = args[1].toString();

        //3.将原始数据按照传入的分隔符进行切分
        String[] fields = data.split(splitSymbol);

        //4.遍历切分后的结果，并写出
        for (String field : fields) {
            //重置集合
            outList.clear();

            //将每个单词添加至集合
            outList.add(field);

            //将集合内容写出
            forward(outList);
        }

    }

    @Override
    public void close() throws HiveException {

    }
}
