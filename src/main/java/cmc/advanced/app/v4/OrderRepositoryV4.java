package cmc.advanced.app.v4;

import cmc.advanced.trace.TraceStatus;
import cmc.advanced.trace.logtrace.LogTrace;
import cmc.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {
    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
            @Override
            protected Void call() {
                //저장 로직
                if (itemId.equals("ex")) {
                    try {
                        throw new IllegalAccessException("예외 발생!");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save()");
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
