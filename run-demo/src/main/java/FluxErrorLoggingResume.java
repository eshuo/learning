import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-05-22 10:17
 * @Version V1.0
 */
public class FluxErrorLoggingResume {

    private static final Logger log = LoggerFactory.getLogger(FluxErrorLoggingResume.class);

    public static void main(String[] args) {
        Flux.range(1, 5)
            .map(i -> {
                if (i == 3) {
                    throw new RuntimeException("Simulated error for item " + i);
                }
                return "Item " + i;
            })
            .onErrorResume(throwable -> {
               System.out.println("Caught error in stream: {}. Providing fallback."+throwable.getMessage());
                // 提供一个包含单个错误消息的备用流
                return Flux.just("Fallback Error Item");
            })
            .doOnNext(data -> System.out.println("Processed: {}"+ data))
            .subscribe(
                data -> {}, // onNext
                error -> System.out.println("Unhandled error in the stream: {}"+ error.getMessage()), // onError (如果 onErrorResume 没有完全处理，可能会被调用)
                () -> System.out.println("Flux completed") // onComplete
            );
    }
}
