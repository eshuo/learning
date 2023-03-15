package com.eshuo.deferredresultdemo.web;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-03-14 13:48
 * @Version V1.0
 */
@RestController
@RequestMapping("/v1")
public class HelloController {


    // 维护队列
    private ConcurrentLinkedDeque<DeferredResult<String>> deferredResults =
            new ConcurrentLinkedDeque<DeferredResult<String>>();

    // 处理线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(10,
            new ThreadFactoryBuilder().setNameFormat("custom-pool-%d").build());


    @GetMapping("/hello")
    public ResponseEntity<?> handleReqSync(Model model) {

        return ResponseEntity.ok("ok");
    }


    @GetMapping("/async")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult(Model model) {
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
            }
            output.setResult(ResponseEntity.ok("ok"));
        });
        return output;
    }


    /**
     * 长轮询
     *
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public DeferredResult<String> getDeferredResultController() {
        final String requestId = UUID.randomUUID().toString();
        printStr("收到请求\t" + requestId);
        final String message = "defaultValue" + requestId;
        // 设置 5秒就会超时
        final DeferredResult<String> stringDeferredResult = new DeferredResult<String>(36000L);
        // 也可以直接设置默认值
//        final DeferredResult<String> stringDeferredResult1 = new DeferredResult<String>(3000L, message);

        //将请求加入到队列中
        deferredResults.add(stringDeferredResult);

        // 正常处理
        executorService.submit(() -> {
            try {
                final int nextInt = new Random().nextInt(100);
                int time = nextInt * 1000;
                printStr("休眠: " + nextInt + "秒\t" + requestId);
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //业务处理
            printStr("业务处理完成\t" + requestId);
            stringDeferredResult.setResult(message);
        });
        // setResult完毕之后，调用该方法
        stringDeferredResult.onCompletion(() -> {
            printStr("异步调用完成\t" + requestId);
            //响应完毕之后，将请求从队列中去除掉
            deferredResults.remove(stringDeferredResult);
        });
        stringDeferredResult.onTimeout(() -> {
            printStr("业务处理超时\t" + requestId);
            stringDeferredResult.setResult("error:timeOut\t" + requestId);
        });

        return stringDeferredResult;
    }

    private void printStr(String str) {
        System.out.println(System.currentTimeMillis() + "--------------------------\tThreadName :" + Thread.currentThread().getName() + "\t" + str);
    }

}
