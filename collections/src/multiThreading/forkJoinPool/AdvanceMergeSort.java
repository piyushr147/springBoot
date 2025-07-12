package multiThreading.forkJoinPool;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class AdvanceMergeSort extends RecursiveAction {

    private int[] arr;
    int start,end;

    public AdvanceMergeSort(int[] arr,int start,int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(start>=end) {
            return;
        }
        else {
            int mid = (start+end)/2;
            AdvanceMergeSort left = new AdvanceMergeSort(arr,start,mid);
            AdvanceMergeSort right = new AdvanceMergeSort(arr,mid+1,end);

            //adding task to the work-stealing queue of current thread
            left.fork();
            //using this current thread to solve the right part
            right.compute();
            //join the left part
            left.join();

            merge(start,mid,end);
        }
    }

    public void merge(int start,int mid,int end) {
        int[] temp = new int[end - start + 1];
        int i = start, j = mid, k = 0;

        while (i <= mid && j <= end) {
            temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        }

        while (i < mid) temp[k++] = arr[i++];
        while (j < end) temp[k++] = arr[j++];
        System.arraycopy(temp, 0, arr, start, temp.length);
    }
}
