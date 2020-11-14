package examples;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称:JavaSparkPi
 * 类描述:一句话描述该类的功能
 * 创建人:roc
 * 创建时间:2020-11-05 18:00
 * 版本:v1.0
 */
public class JavaSparkPi {
    public static void main(String[] args) throws Exception {
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("JavaSparkPi")
                .getOrCreate();

        JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

        int slices = (args.length == 1) ? Integer.parseInt(args[0]) : 2;
        int n = 100000 * slices;
        List<Integer> l = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            l.add(i);
        }

        JavaRDD<Integer> dataSet = jsc.parallelize(l, slices);

        int count = dataSet.map(integer -> {
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            return (x * x + y * y <= 1) ? 1 : 0;
        }).reduce(Integer::sum);

        System.out.println("Pi is roughly" + 4.0 * count / n);

        spark.stop();
    }
}
