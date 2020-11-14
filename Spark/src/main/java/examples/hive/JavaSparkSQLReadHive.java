package examples.hive;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 类名称:JavaSparkSQLReadHive
 * 类描述:SparkSQL读取hive数据源
 * 创建人:roc
 * 创建时间:2020/11/12 17:58
 * 版本:v1.0
 */
public class JavaSparkSQLReadHive {

    public static void main(String[] args) {
        //创建SparkSession
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("JavaSparkSQLReadHive")
                .getOrCreate();

        //读取hive表
        Dataset<Row> df = spark.sql("select * from tmp").toDF();

        spark.stop();
    }
}
