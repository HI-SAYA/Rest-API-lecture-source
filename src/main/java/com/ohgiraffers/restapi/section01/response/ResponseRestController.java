package com.ohgiraffers.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController // 1. bean scan 2. @RequestMapping 3. @ResponseBody 역할을 한다.
@RequestMapping("/response")
public class ResponseRestController {

    /* 문자열 응답 */
    @GetMapping("/hello")
    public String helloWorld() { return "Hello World"; }

    /* 2. 기본 자료형 응답 */
    @GetMapping("/random")
    public int getRandomNumber() { return (int)(Math.random() * 10) + 1; }

    /* 3. Object 응답 */
    @GetMapping("/message")
    public Message getMessage() { return new Message(200, "메세지를 응답합니다."); }

    /* 4. List 응답 */
    @GetMapping("/list")
    public List<String> getList() { return List.of(new String[]{"사과", "바나나", "복숭아"}); }
    // 문자열 배열을 List 형식으로 바꿔서 반환

    /* Map 응답 */
    @GetMapping("/map")
    public Map<Integer, String> getMap() {

        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200, "정상 응답"));
        messageList.add(new Message(404, "페이지를 찾을 수 없습니다."));
        messageList.add(new Message(500, "개발자의 잘못입니다."));

        return messageList.stream().collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));
        // toMap(key, value)
        // key값은 기본적으로 json에서 문자열로 감싸져서 온다.
    }

    /* 6. ImageFile 응답 */
    @GetMapping(value="/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage() throws IOException {
        return getClass().getResourceAsStream("/images/R.png").readAllBytes();
        // 경로로부터 리소스를 스트림으로 꺼내오겠다. 그 스트림으로 데이터를 바이트로 읽겠다. -> 배열 byte[]에 담긴다.
    }

    /* 7. ResponseEntity 응답 */
    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity() {
        return ResponseEntity.ok(new Message(123, "hello world!"));
        // body, header, status
        // status만 전달하는 케이스도 있고, body, header만 전달하는 케이스도 있고.. 다양하게 많다.
        // 응답의 구성 요소 3가지를 세팅해서 응답할 수 있다.
    }
}
