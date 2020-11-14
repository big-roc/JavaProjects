package exmaples.rdd

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * 类名称:RDD01
 * 类描述:RDD的Value类型算子
 * 创建人:roc
 * 创建时间:2020-11-06 10:00
 * 版本:v1.0
 *
 */
object RDD02 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[8]").setAppName("WC")
    val sc = new SparkContext(conf)

    //map
    val rdd1_1 = sc.makeRDD(1 to 10).collect()
    val rdd1_2 = rdd1_1.map(_ * 2)
    //rdd1_2.foreach(println)

    //mapPartitions
    val rdd2_1 = sc.parallelize(Array(1, 2, 3, 4))
    val rdd2_2 = rdd2_1.mapPartitions(x => x.map(_ * 2)).collect()
    //rdd2_2.foreach(println)

    //mapPartitionsWithIndex
    val rdd3_1 = sc.makeRDD(Array(1, 2, 3, 4))
    val rdd3_2 = rdd3_1.mapPartitionsWithIndex((x, y) => y.map((x, _))).collect()
    //rdd3_2.foreach(println)

    //flatMap
    val rdd4_1 = sc.makeRDD(1 to 5)
    val rdd4_2 = rdd4_1.flatMap(1 to _).collect()
    //rdd4_2.foreach(println)

    //glom
    val rdd5_1 = sc.makeRDD(1 to 16, 4)
    val rdd5_2 = rdd5_1.glom().collect()
    //rdd5_2.foreach(println)

    //groupBy
    val rdd6_1 = sc.makeRDD(1 to 4)
    val rdd6_2 = rdd6_1.groupBy(_ % 2).collect()
    rdd6_2.foreach(println)
  }
}
