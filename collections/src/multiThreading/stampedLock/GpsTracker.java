package multiThreading.stampedLock;

import java.util.concurrent.locks.StampedLock;

public class GpsTracker {

    private double x,y;
    static int readCounter = 10000;
    static int writeCounter = 100;
    StampedLock stampedLock = new StampedLock();

    public double[] readPosition(){
        long stamp = stampedLock.tryOptimisticRead();
        //Now read the values
        double currX = x,currY = y;
        System.out.println("Reading with tryOptimisticRead, x: "+currX+", y: "+currY);

        //Now check if value of positions were not updated while reading by some other write operation.
        if(!stampedLock.validate(stamp)){
            //Now we will use readLock to stop any concurrent writes while reading
            stamp = stampedLock.readLock();
            try {
                currX = x;
                currY = y;
                System.out.println("Stamp validation failed so readLock acquired, x: "+currX+", y: "+currY);
            }catch (Exception e){
                System.out.println("Exception in read: "+e.getMessage());
            }finally {
                System.out.println("Read lock removed");
                stampedLock.unlockRead(stamp);
            }
        }

        return new double[]{currX,currY};
    }

    public void updatePosition(double x, double y){
        long stamp = stampedLock.writeLock();
        try{
            this.x = x;
            this.y = y;
            System.out.println("Stamp writeLock acquired and value updated, x: "+x+", y: "+y);
        } catch (Exception e) {
            System.out.println("Exception in write: "+e.getMessage());
        }finally {
            System.out.println("Write lock removed");
            stampedLock.unlockWrite(stamp);
        }
    }
}
