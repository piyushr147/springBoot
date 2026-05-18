package multiThreading.future;

import java.util.concurrent.*;

public class CallableExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPool  = new ThreadPoolExecutor(5, 10, 2000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        }, new ThreadPoolExecutor.DiscardOldestPolicy());

        for(int i=0;i<1000;i++){
            threadPool.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //the lambda expression that return a value is a callable
        Future<Integer> futureValue = executorService.submit(()->{
            System.out.println("fetching value of student age from DB");
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            return 20;
        });

        Integer value = futureValue.get();
        System.out.println(value);
    }
}
