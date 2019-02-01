package cn.wangjie.learn;

/**
 * @program: read-write
 * @description:
 * @author: WangJie
 * @create: 2019-02-01 16:17
 **/
public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    void doSomething(Event e) {
    }


    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}