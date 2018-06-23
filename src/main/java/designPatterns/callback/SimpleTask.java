package designPatterns.callback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTask extends Task {

    @Override
    public void excuteWith(CallBack callBack) {
        callBack.call();
    }
}
