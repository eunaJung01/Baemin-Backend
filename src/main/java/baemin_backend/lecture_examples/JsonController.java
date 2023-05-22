package baemin_backend.lecture_examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@Controller
public class JsonController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // TEXT 반환
    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        UserData userData = objectMapper.readValue(messageBody, UserData.class);
        log.info("nickname={}, age={}", userData.getNickname(), userData.getAge());

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-json-v2")
    public void requestBodyJsonV2(@RequestBody UserData userData, HttpServletResponse response) throws IOException {
        log.info("nickname={}, age={}", userData.getNickname(), userData.getAge());
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody UserData userData) {
        log.info("nickname={}, age={}", userData.getNickname(), userData.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<UserData> httpEntity) {
        log.info("nickname={}, age={}", Objects.requireNonNull(httpEntity.getBody()).getNickname(), httpEntity.getBody().getAge());
        return "ok";
    }

    // JSON 반환
    @ResponseBody
    @PostMapping("/response-body-json")
    public UserData responseBodyJson(@RequestBody UserData userData) {
        log.info("nickname={}, age={}", userData.getNickname(), userData.getAge());
        return userData;
    }

}
