package exmaples.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * 类名称:RDD01
 * 类描述:创建RDD的3种方式
 * 创建人:roc
 * 创建时间:2020-11-06 10:00
 * 版本:v1.0
 *
 */
object RDD01 {
  def main(args: Array[String]): Unit = {
    //1.创建conf并设置App名称
    val conf = new SparkConf().setMaster("local[1]").setAppName("WC")

    //2.创建SparkContext对象，该对象是提交Spark App的入口
    val sc = new SparkContext(conf)

    //使用parallelize创建
    val rdd = sc.parallelize(Array(1, 2, 3, 4, 5))
    rdd.foreach(println)

    //使用makeRDD创建
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    rdd1.foreach(println)

    //从外部存储系统的数据集创建
    val rdd2 = sc.textFile("hdfs://hadoop102:9000")
    rdd2.foreach(println)
  }
}
