package multiThreading.producerConsumer;

public class ProducerConsumer {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread(()->{
            try{
                while(--SharedResource.counter>0){
                    //Thread.sleep(5000);
                    sharedResource.produce(1000-SharedResource.counter);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        Thread consumer = new Thread(()->{
            try{
                while(--SharedResource.counter>0){
                    //Thread.sleep(2000);
                    sharedResource.consume();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        producer.start();
        consumer.start();
    }
}
