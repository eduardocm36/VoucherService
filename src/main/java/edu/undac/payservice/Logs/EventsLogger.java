package edu.undac.payservice.Logs;

import static edu.undac.payservice.Constants.LogConstant.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class EventsLogger {

    public <T> void printErrorResponse(HttpServletRequest httpServletRequest, T responseBody, Exception exception,
            int statusCode) {

        Map<String, Object> mapMessage = new LinkedHashMap<>();

        mapMessage.put(LOG_TYPE, RESPONSE.toUpperCase());
        mapMessage.put(PROCESS_TIME, this.calculateProcessingTime(httpServletRequest, START_TIME));
        mapMessage.put(RESPONSE, Optional.ofNullable(exception.getMessage()).orElse(Strings.EMPTY));
        log.error(mapMessage);
    }

    private long calculateProcessingTime(HttpServletRequest httpServletRequest, String attribute) {

        if (httpServletRequest.getAttribute(attribute) instanceof Long startTime) {
            return System.currentTimeMillis() - startTime;
        }
        return 0;
    }

}
