package com.example.demo.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest // web 관련 Bean 모두 등록됨. Repository Bean 등록 안해줘 주입 받아야함.
public class EventControllerTests {

    // MockMvc 주입받아 사용, 가짜 요청을 만들어서 dispatcherServlet 에서 보내 응답을 확인할 수 있음.
    // 컨트롤러 테스트용으로 많이 사용됨.
    @Autowired
    MockMvc mockMvc;

    //객체를 JSON으로 바꿔줌.
    @Autowired
    ObjectMapper objectMapper;

    //mock객체이기 때문에 sava 된 값이 null 임. 따라서 Mockito 작성
    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {

        Event event = Event.builder()
                .name("Spring")
                .description("REST API development with Spring")
                .beginEnrollmentDateTime(LocalDateTime.of(2018, 11, 23, 14, 21))
                .closeEnrollmentDateTime(LocalDateTime.of(2018, 11, 24, 14, 21))
                .beginEventDateTime(LocalDateTime.of(2018, 11, 25, 14, 21))
                .endEventDateTime(LocalDateTime.of(2018, 11, 26, 14, 21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 스터디 카페")
                .build();
        event.setId(10);
        // eventRepository.save(event)가 실행되면 event객체를 return.
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON) // request contentType
                        .accept(MediaTypes.HAL_JSON)// response Type
                        .content(objectMapper.writeValueAsString(event))) // event 객체를 JSON 문자열로 바꿔 요청 본문에 넣어줌.
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE));
    }
}
