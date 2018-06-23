package designPatterns.callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static void main(String[] args) {
        Task task = new SimpleTask();
//        CallBack callBack = new CallBack() {
//            @Override
//            public void call() {
//                log.info("jobs down");
//            }
//        };
        CallBack callBack = () -> log.info("jobs down");
        task.excuteWith(callBack);
    }
}
