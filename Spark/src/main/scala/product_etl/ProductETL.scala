package product_etl

import org.apache.spark.sql.SparkSession

/**
 *
 * 类名称:ProductETL
 * 类描述:产品数据清洗，本地读取远程hive数据
 * 创建人:roc
 * 创建时间:2020/11/17 13:40
 * 版本:v1.0
 *
 */
object ProductETL {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Demo")
      .master("local[*]")
      .enableHiveSupport()
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    //读取贸泽爬虫表
    val df = spark.sql("select * from dp_test.test limit 10")
    df.show()

    // 关闭spark
    spark.stop()
  }
}
