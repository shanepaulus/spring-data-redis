package com.shanepaulus.config;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderConfig {

  private static final int THREAD_COUNT = 10;

  @Bean
  public ForkJoinPool dataLoaderThreadPool() {
    ForkJoinPool.ForkJoinWorkerThreadFactory workerThreadFactory = new DataLoaderForkJoinWorkerThreadFactory();
    return new ForkJoinPool(THREAD_COUNT, workerThreadFactory, null, false);
  }

  private static class DataLoaderForkJoinWorkerThreadFactory implements
      ForkJoinPool.ForkJoinWorkerThreadFactory {

    private static final String THREAD_NAME_PREFIX = "dataloader-thread-";
    private final AtomicLong threadIndex = new AtomicLong(1);

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
      return new DataLoaderForkJoinWorkerThread(pool);
    }

    private class DataLoaderForkJoinWorkerThread extends ForkJoinWorkerThread {

      protected DataLoaderForkJoinWorkerThread(ForkJoinPool pool) {
        super(pool);
        setName(THREAD_NAME_PREFIX + threadIndex.getAndIncrement());
      }
    }
  }
}
