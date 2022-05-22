package com.futao.fund.spider.spiders.tiantian;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author futaosmile@gmail.com
 * @date 2022/5/22
 */
@Slf4j
public class FjEmo extends RecursiveTask<Integer> {

    private int start;
    private int end;
    /**
     * 超过则进行分组
     */
    private int threshold;

    public FjEmo(int start, int end, int threshold) {
        this.start = start;
        this.end = end;
        this.threshold = threshold;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> result = forkJoinPool.submit(new FjEmo(1, 100, 10));
        try {
            log.info("result:{}", result.get());
        } catch (Exception e) {
            log.error("exception", e);
        }
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 小于等于阈值，直接计算（因为任务足够小）
        if (end - start <= threshold) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，则拆分成更小的2个任务
            int middleValue = (end + start) / 2;
            FjEmo fjEmo1 = new FjEmo(start, middleValue, threshold);
            FjEmo fjEmo2 = new FjEmo(middleValue + 1, end, threshold);

            // 当前线程分配完工作后，也去执行任务，而不是等待
            invokeAll(fjEmo1, fjEmo2);

            // 这种方式会导致当前线程不工作，变成监工
            // fjEmo1.fork();
            // fjEmo2.fork();

            sum = fjEmo1.join() + fjEmo2.join();
        }
        return sum;
    }
}
