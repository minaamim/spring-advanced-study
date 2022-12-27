package cmc.advanced.app.v2;

import cmc.advanced.trace.TraceId;
import cmc.advanced.trace.TraceStatus;
import cmc.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId, "OrderRepository.save()");
            //저장 로직
            if(itemId.equals("ex")) {
                try {
                    throw new IllegalAccessException("예외 발생!");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

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
