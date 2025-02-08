package com.example.redisdemo.config.thread;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-01-20 14:11
 * @Version V1.0
 */
public class MyCallerRunsPolicy implements RejectedExecutionHandler {


    private final static Logger logger = LoggerFactory.getLogger(MyCallerRunsPolicy.class);

    private final TaskExecutor taskExecutor;

    private final String threadNamePrefix;


    public MyCallerRunsPolicy(TaskExecutor taskExecutor, String threadNamePrefix) {
        this.taskExecutor = taskExecutor;
        this.threadNamePrefix = threadNamePrefix;
    }

    /**
     * Method that may be invoked by a {@link ThreadPoolExecutor} when {@link ThreadPoolExecutor#execute execute} cannot accept a task.  This may occur when no more threads or queue slots are
     * available because their bounds would be exceeded, or upon shutdown of the Executor.
     *
     * <p>In the absence of other alternatives, the method may throw
     * an unchecked {@link RejectedExecutionException}, which will be propagated to the caller of {@code execute}.
     *
     * @param r        the runnable task requested to be executed
     * @param executor the executor attempting to execute this task
     * @throws RejectedExecutionException if there is no remedy
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.error("MyCallerRunsPolicy name:{} toString:{} Task:{}", threadNamePrefix, executor.toString(), r.toString());
        if (null != taskExecutor) {
            taskExecutor.execute(r);
        } else {
//            throw new RejectedExecutionException("Task " + r.toString() +
//                    " rejected from " +
//                    executor.toString());
        }
    }

}
