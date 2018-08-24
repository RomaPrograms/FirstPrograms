import java.util.concurrent.atomic.AtomicInteger;

public class AtomarVariables {

    int d;
    Integer I;//эти переменные не атомарные
    double v;

    //static int i;//заменяем на атомарную переменную
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        for(int j = 0; j< 10_000; ++j){
            new MyThread().start();
        }
        Thread.sleep(2_000);
        System.out.println(atomicInteger.get());
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            atomicInteger.incrementAndGet();
        }
    }
}

//когда мы выполняем операцию i++, то она выполняется в 2-ва слоя те
// int k = i+1;
// i = k; It's mean that when we use it in multithread programs it can be crash after first line, that's why we need to use atomic integers.
