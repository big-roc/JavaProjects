package cn.jiguang.fileprocess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputToTxtDemo {
    public static final String FILE_NAME = "Output.txt";
    public static final String FILE_PATH = "D:/data/Java/fileProcess";

    public static void createFile(String filePath, String fileName) {
        File folder = new File(filePath);
        //文件夹
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("文件夹路径不存在，创建路径: " + filePath);
            folder.mkdirs();
        } else {
            System.out.println("文件夹路径存在: " + filePath);
        }

        //文件
        File file = new File(filePath + "/" + fileName);
        if (!file.exists()) {
            System.out.println("文件不存在，创建文件: " + filePath + "/" + fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已存在，文件为: " + filePath + "/" + fileName);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream outFile = null;

        try {
            createFile(FILE_PATH, FILE_NAME);
            outFile = new FileOutputStream(FILE_PATH + "/" + FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
