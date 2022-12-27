package cmc.advanced.trace.logtrace;

import cmc.advanced.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus status);

    void exception(TraceStatus status, Exception e);
}
