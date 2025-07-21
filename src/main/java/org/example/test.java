package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.*;

public class test {
    public static void main(String[] args) {
//        ChromeDriver cDriver= new ChromeDriver();
        WebDriver driver=  new ChromeDriver();
        driver.get("https://www.amazon.in/");
        WebElement element=driver.findElement(By.xpath("//a[@href='/fresh?ref_=nav_cs_fresh']/ancestor::*[1]"));
        System.out.println(element.getTagName());



//        Actions actions= new Actions(driver);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//// Execute JavaScript
//        js.executeScript("alert('Hello World');");
//        WebElement ele=driver.findElement(By.xpath(""));
//        Select select=new Select(ele);



    }

}
interface A{
    static void run(){
        System.out.println(" A run ");
    }
}
interface B {
    static void run(){
        System.out.println(" B run ");
    }
}
class largenumbers{
    public static void main(String[] args) {
        String s="3,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000";
        int count=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='0')
                count++;
        }

        String []a=s.split(",");
        System.out.println(a.length);
        System.out.println(count);
    }
}
class C implements A,B{

    public static void main(String[] args) throws newException {


        try {
            throw  new newException("this is a new exception");
        } catch (newException e) {
            e.printStackTrace();
        }

//        TreeMap<Integer,Integer> map;
//        HashMap<Integer,Integer> map2= new HashMap<>();
//        ArrayList<Integer> alist= new ArrayList<>();
//        Integer[] iarray=alist.toArray(new Integer[]{});
//        map2.put(1,2);
//        map2.put(1,3);
//        System.out.println(map2.size());
//        System.out.println(map2.get(1));
//        System.out.println(map2.containsKey(1));
//        Thread.sleep(5000);
//        C c= new C();
//        Runnable method_1 = C::method;
////        method_1.run();
//        Stack<Integer> stack;
//        Set<Integer> set=new HashSet<>();

    }
    static void method(){

        class hello{
            static  int a=10;
            int b=12;

        }
    }


}

class newException extends  Exception{
    newException(String m){
        super(m);
    }
}
class checkthrow extends Throwable {

}
class switch_case{
    int a;
    int b;

    switch_case(int a,int b){
        this.a=a;
        this.b=b;
    }

    public static void main(String[] args) {
        switch_case s1= new switch_case(1,2);
        switch_case s2= new switch_case(3,4);

//        switch (s1){
//            case s1.a==1:
//                System.out.println("s1 a1");
//                break;
//            case s1.a==2:
//                System.out.println("s1 a2");
//                break;
//            default:
//                System.out.println("none");
//        }
        int c=5;
        switch (c) {
            case 1 -> {
                System.out.println("1");
                System.out.println("hello");
            }
            case 2 -> {
                System.out.println("2");
                System.out.println("people");
            }
            default -> {
                System.out.println(c);
                System.out.println("check");
            }
        }
    }
}
class time_execution{
    public static void main(String[] args) {
//       time_to_count();
//count_to_time();
        long n=3_500_000_000L;
        double a=(double)(Long.MAX_VALUE/n)/(3600*24*365);
        System.out.println(a);
//runtime_count_exception();
    }
    static void time_to_count(){
        int m = 50;
        int time = 1;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("time : " + 1 + " seconds");

        for (int n = 1; n < m; n++) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += count(executor, time);
            }
            System.out.println("(" + n + ") " + sum / n);
        }
        executor.shutdown();
    }

    static long count(ExecutorService executor, int time) {
        final long[] i = {0};
        Runnable task = () -> {
            long start = System.nanoTime();
            while (System.nanoTime() - start < TimeUnit.SECONDS.toNanos(time)) {
                i[0]++;
            }
        };
        Future<?> future = executor.submit(task);
        try {
            future.get(time + 1, TimeUnit.SECONDS); // Add buffer to avoid premature timeout
        } catch (Exception e) {
            future.cancel(true);
        }
        return i[0];
    }
    static void count_to_time(){
//        long start=System.nanoTime();
//        System.out.println(start);


        long n=3_500_000_000L;
        int m=10;
        long sum=0L;
        long i=0;
        while (i != n)
            i++;
        for(int j=0;j<m;j++) {
            i=0;
            long start1 = System.currentTimeMillis();
            while (i != n)
                i++;
            long k= System.currentTimeMillis() - start1;
            sum+=k;
            System.out.println(k);
        }
        System.out.println("avg : "+(double)(sum/m)/1000);
    }
    private static volatile long counter = 0;
    static void runtime_count_exception(){
        long start=System.currentTimeMillis();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutdown hook executed!");
            System.out.println("Final count: " + counter);
            System.out.println("Cleanup completed");
            System.out.println(System.currentTimeMillis()-start);
        }));

        System.out.println("Program running... Press Ctrl+C to interrupt");

        try {
            while (true) {
                counter++;
                // Optional: add small delay to make counting visible

            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
    }
