package com.wyci.iodemo.demos;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description AIO （Asynchronous I/O）：异步非阻塞 I/O 模型。
 * @Author wangshuo
 * @Date 2024-10-12 17:26
 * @Version V1.0
 */
public class AIODemo {

    public static void main(String[] args) {
        String data = "aio";
        Path path = Paths.get("testAio.txt");
        ByteBuffer buffer = ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8));

        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
            Future<Integer> writeResult = channel.write(buffer, 0);

            while (!writeResult.isDone()) {
                // 等待写入完成
            }

            int bytesWritten = writeResult.get();
            System.out.println("Bytes written: " + bytesWritten);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
