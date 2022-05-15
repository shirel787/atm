package com.example.ATM.config;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import javax.servlet.http.HttpServletRequest;

@Component
public class RestTemplateConfig {

    @Bean
    @RequestScope
    public RestTemplate getRestTemplate(HttpServletRequest inReq) {
        final RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add(
                    (outReq, bytes, clientHttpReqExec) -> {
                        return clientHttpReqExec.execute(outReq, bytes);
                    });
        return restTemplate;
    }
}

