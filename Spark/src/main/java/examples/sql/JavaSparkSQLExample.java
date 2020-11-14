package examples.sql;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.col;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 类名称:JavaSparkSQLExample
 * 类描述:一句话描述该类的功能
 * 创建人:roc
 * 创建时间:2020-11-05 19:00
 * 版本:v1.0
 */
public class JavaSparkSQLExample {
    public static class Person implements Serializable {
        private String name;

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


    public static void main(String[] args) throws AnalysisException {
        //创建SparkSession
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("JavaSparkSQLExample")
                .getOrCreate();

        //runBasicDataFrameExample(spark);
        //runDatasetCreationExample(spark);
        runInferSchemaExample(spark);
        runProgrammaticSchemaExample(spark);
        spark.stop();
    }

    private static void runBasicDataFrameExample(SparkSession spark) throws AnalysisException {
        //创建DataFrame
        Dataset<Row> df = spark.read().json("./Spark/src/main/data/person.txt");

        df.show();
        df.printSchema();
        df.select("name").show();
        df.select(col("name"), col("age").plus(1)).show();
        df.filter(col("age").gt(21)).show();
        df.groupBy("age").count().show();

        df.createOrReplaceTempView("people");
        Dataset<Row> sqlDF = spark.sql("select * from people");
        sqlDF.show();

        df.createGlobalTempView("people");
        spark.sql("select * from global_temp.people").show();

        spark.newSession().sql("select * from global_temp.people").show();
    }

    private static void runDatasetCreationExample(SparkSession spark) {
        Person person = new Person();
        person.setName("Andy");
        person.setAge(32);

        Encoder<Person> personEncoder = Encoders.bean(Person.class);
        Dataset<Person> javaBeanDS = spark.createDataset(
                Collections.singletonList(person),
                personEncoder
        );
        javaBeanDS.show();

        Encoder<Integer> integerEncoder = Encoders.INT();
        Dataset<Integer> primitiveDS = spark.createDataset(Arrays.asList(1, 2, 3), integerEncoder);
        Dataset<Integer> transformedDS = primitiveDS.map(
                (MapFunction<Integer, Integer>) value -> value + 1,
                integerEncoder);
        transformedDS.collect(); // Returns [2, 3, 4]

        // DataFrames can be converted to a Dataset by providing a class. Mapping based on name
        String path = "./Spark/src/main/data/person.txt";
        Dataset<Person> peopleDS = spark.read().json(path).as(personEncoder);
        peopleDS.show();
    }

    private static void runInferSchemaExample(SparkSession spark) {
        JavaRDD<Person> peopleRDD = spark.read()
                .textFile("./Spark/src/main/data/person.txt")
                .javaRDD()
                .map(line -> {
                    String[] parts = line.split(",");
                    Person person = new Person();
                    person.setName(parts[0]);
                    person.setAge(Integer.parseInt(parts[1].trim()));
                    return person;
                });


        Dataset<Row> peopleDF = spark.createDataFrame(peopleRDD, Person.class);
        peopleDF.createOrReplaceTempView("people");
        Dataset<Row> teenagersDF = spark.sql("select name from people where age between 13 and 19");

        Encoder<String> stringEncoder = Encoders.STRING();
        Dataset<String> teenagerNamesByIndexDF = teenagersDF.map(
                (MapFunction<Row, String>) row -> "Name: " + row.getString(0),
                stringEncoder);
        teenagerNamesByIndexDF.show();

        Dataset<String> teenagerNamesByFieldDF = teenagersDF.map(
                (MapFunction<Row, String>) row -> "Name: " + row.<String>getAs("name"),
                stringEncoder);
        teenagerNamesByFieldDF.show();
    }

    private static void runProgrammaticSchemaExample(SparkSession spark) {
        JavaRDD<String> peopleRDD = spark.sparkContext()
                .textFile("examples/src/main/resources/people.txt", 1)
                .toJavaRDD();

        String schemaString = "name age";
        List<StructField> fields = new ArrayList<>();
        for (String fieldName : schemaString.split(" ")) {
            StructField field = DataTypes.createStructField(fieldName, DataTypes.StringType, true);
            fields.add(field);
        }
        StructType schema = DataTypes.createStructType(fields);

        JavaRDD<Row> rowRDD = peopleRDD.map((Function<String, Row>) record -> {
            String[] attributes = record.split(",");
            return RowFactory.create(attributes[0], attributes[1].trim());
        });

        Dataset<Row> peopleDataFrame = spark.createDataFrame(rowRDD, schema);

        peopleDataFrame.createOrReplaceTempView("people");
        Dataset<Row> results = spark.sql("SELECT name FROM people");

        Dataset<String> namesDS = results.map(
                (MapFunction<Row, String>) row -> "Name: " + row.getString(0),
                Encoders.STRING());
        namesDS.show();
    }
}
