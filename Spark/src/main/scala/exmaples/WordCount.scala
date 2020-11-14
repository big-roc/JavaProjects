package exmaples

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * 项目名称: spark
 * 包: demo
 * 类名称: WordCount
 * 类描述: Scala的WordCount
 * 创建人: roc
 * 创建时间: 2020-11-05 09:30
 * 修改人: roc
 * 修改时间: 2020-11-05 09:30
 * 修改备注: 无
 * 版本:v1.0
 *
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf = new SparkConf().setMaster("local[*]").setAppName("WC")

    //2.创建SparkContext对象，该对象是提交Spark App的入口
    val sc = new SparkContext(conf)

    //3.使用sc创建RDD并执行相应的 transformation 和 action
    sc.textFile("./Spark/src/main/data/word_count.txt").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _).saveAsTextFile("./Spark/src/main/data/word_count_result")

    //4.关闭连接
    sc.stop()
  }
}
