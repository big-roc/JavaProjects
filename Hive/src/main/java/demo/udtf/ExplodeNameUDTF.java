package demo.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.*;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;

/**
 * 类名称:ExplodeNameUDTF
 * 类描述:按照空格拆分，一行变多行
 * 创建人:roc
 * 创建时间:2020-10-28 15:00
 * 版本:v1.0
 */
public class ExplodeNameUDTF extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(ObjectInspector[] argOIs)
            throws UDFArgumentException {

        if (argOIs.length != 1) {
            throw new UDFArgumentException("ExplodeStringUDTF takes exactly one argument.");
        }
        if (argOIs[0].getCategory() != ObjectInspector.Category.PRIMITIVE
                && ((PrimitiveObjectInspector) argOIs[0]).getPrimitiveCategory() != PrimitiveObjectInspector.PrimitiveCategory.STRING) {
            throw new UDFArgumentTypeException(0, "ExplodeStringUDTF takes a string as a parameter.");
        }

        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldNames.add("name");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldNames.add("surname");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        // TODO Auto-generated method stub
        String input = args[0].toString();
        String[] name = input.split(" ");
        forward(name);
    }

    @Override
    public void close() throws HiveException {
        // TODO Auto-generated method stub

    }

}