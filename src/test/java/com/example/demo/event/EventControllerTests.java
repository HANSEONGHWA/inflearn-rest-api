package com.example.demo.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // web 관련 Bean 모두 등록됨.
public class EventControllerTests {

    // MockMvc 주입받아 사용, 가짜 요청을 만들어서 dispatcherServlet 에서 보내 응답을 확인할 수 있음.
    // 컨트롤러 테스트용으로 많이 사용됨.
    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEvent() throws Exception {
        mockMvc.perform(post("api/events/")
                        .contentType(MediaType.APPLICATION_JSON) // request contentType
                        .accept(MediaTypes.HAL_JSON))// response Type
                .andExpect(status().isCreated());
    }
}
