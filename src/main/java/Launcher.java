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

        print("Printing create");
        create();

        print("Printing filtering");
        filtering();
    }

    private static void filtering() {

        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("One");
            emitter.onNext("Two");
            emitter.onNext("Three");
            emitter.onNext("Four");
            emitter.onNext("Five");
            emitter.onNext("Six");
            emitter.onNext("Seven");
        });

      /*  Observable<Integer> lengths = source.map(String::length);
        Observable<Integer> filtered = lengths.filter(integer -> integer >= 5);
        filtered.subscribe(System.out::println);*/

        source.map(String::length)
                .filter(integer -> integer >= 5)
                .subscribe(System.out::println);
    }

    private static void create() {

        Observable<Integer> source = Observable.create(emitter -> {
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onNext(5);
            try {
                emitter.onNext(10 / 0);
            } catch (Throwable error) {
                print("Printing error");
                emitter.onError(error);
            }
            emitter.onComplete();
        });

        source.subscribe(System.out::println, Throwable::printStackTrace);
    }

    private static void interval() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);

        interval.subscribe(System.out::println);

        sleep();
    }

    private static void just() {
        Observable<String> myString = Observable.just("One", "Two", "Three");

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
