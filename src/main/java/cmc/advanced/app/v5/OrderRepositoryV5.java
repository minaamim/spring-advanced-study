package cmc.advanced.app.v5;

import cmc.advanced.trace.callback.TraceTemplate;
import cmc.advanced.trace.logtrace.LogTrace;
import cmc.advanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template =new TraceTemplate(trace);
    }

    public void save(String itemId) {

        template.execute("OrderRepository.save()", () -> {
            if (itemId.equals("ex")) {
                try {
                    throw new IllegalAccessException("예외 발생!");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            sleep(1000);
            return null;
        });
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

}
