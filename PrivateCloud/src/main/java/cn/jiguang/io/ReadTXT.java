package cn.jiguang.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadTXT {
    public static void main(String[] args) throws IOException {
        int num = 0;
        char[] buf = new char[1024];
        try {
            // 打开文件
            FileReader fileReader = new FileReader("D:/data/Java/io/a.txt");
            // 取出字符存到buf数组中
            while ((num = fileReader.read(buf)) != -1) {
                System.out.println(new String(buf, 0, num));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(buf[i] + " ");
        }
    }
}
