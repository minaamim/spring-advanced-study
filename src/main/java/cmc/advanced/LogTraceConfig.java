package cmc.advanced;

import cmc.advanced.trace.logtrace.LogTrace;
import cmc.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        //싱글톤으로 등록
        return new ThreadLocalLogTrace();
    }
}
