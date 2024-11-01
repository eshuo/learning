package com.wyci.iodemo.demos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

/**
 * @Description BIO （Blocking I/O）：同步阻塞 I/O 模式。
 * @Author wangshuo
 * @Date 2024-10-12 17:26
 * @Version V1.0
 */
public class BIODemo {


    public static void main(String[] args) throws IOException {
        BIODemo demo = new BIODemo();
        ClassPathResource classPathResource = new ClassPathResource("write.txt");
        String path = classPathResource.getPath();
        demo.writeFile(path);
        demo.readFile(path);


    }

    // 使用 BIO 写入文件
    public void writeFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("demo");
            bufferedWriter.newLine();
            System.out.println("写入完成");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 使用 BIO 读取文件
    public void readFile(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("读取的内容: " + line);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
