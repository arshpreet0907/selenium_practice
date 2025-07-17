package org.example;

public class monkey_puzzel {
    public static void main(String[] args) {
//        monkey_puzzel();
        recursion(1);
        }
static void monkey_puzzel(){
    boolean doors[]=new boolean[10000];
    int monkeys=10000;

    for(int i=0;i<monkeys;i++){
        int j=i;
        while (j<monkeys){
            doors[j]=!doors[j];
            j+=i+1;
        }
    }
    print_array(doors);

}
    static void print_array(boolean[] arr){
        for(int i=0;i<arr.length;i++){
            if (arr[i])
                System.out.print((i+1)+" ");
        }
        System.out.println();
    }
    static void recursion(int n){
        System.out.println(n);
        recursion(n+1);
    }
}
