import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


public class Launcher {

    private static String TAG = Launcher.class.getSimpleName();

    public static void main(String[] args) {

        System.out.println(TAG);

        print("Printing just method");
        just();

        print("Printing interval");
        interval();
    }

    private static void interval() {

        Observable<Long> secondInterval = Observable.interval(1, TimeUnit.SECONDS);

        secondInterval.subscribe(System.out::println);

        sleep();

    }

    private static void just() {
      Observable<String> myString = Observable.just("One", "Two", "Three", "Four", "Five");

      myString
              .map(String::length)
              .subscribe(System.out::println);
    }

    //region general methods
    private static void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void print(String string) {
        System.out.println(string);
    }
    //endregion

}
