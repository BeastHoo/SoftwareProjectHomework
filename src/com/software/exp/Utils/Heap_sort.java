package com.software.exp.Utils;


//大根堆排序
public class Heap_sort {
    int HeapSize;
    public void HeapSort(int []A)
    {
        Build_Max_Heap(A);
//        HeapSize=A.length;
        for(int i= A.length-1;i>0;i--)
        {
            int temp=A[0];
            A[0]=A[i];
            A[i]=temp;
            HeapSize-=1;
            Max_Heapify(A,0);
        }
    }

    private void Build_Max_Heap(int []A)   //通过循环调用Max_Heapify()来保证从最后一个叶子结点的父节点开始，每一个分支都符合大根堆的性质
    {
        HeapSize=A.length-1;
        for(int i=(A.length-1)/2;i>=0;i--)
        {
            Max_Heapify(A,i);
        }
    }

    public void Max_Heapify(int []A,int i)//维护大根堆的性质
    {
        int l=Left(i);
        int r=Right(i);
        int largest=0;
        if(l<=HeapSize && A[l]>A[i])
        {
            largest=l;
        }else largest=i;

        if(r<=HeapSize && A[r]>A[largest])
        {
            largest=r;
        }
        if(largest!=i)
        {
            int temp=A[i];
            A[i]=A[largest];
            A[largest]=temp;
            i=largest;          //这一步！！很重要，一定要更新i，不然递归是无效的，不能够将起始的A[i]逐层往下对比
            Max_Heapify(A,i);
        }
    }

    private int Left(int i){
        return 2*i+1;
    }
    private int Right(int i){
        return 2*i+2;
    }
    private int Parent(int i)
    {
        return i/2;
    }
}
