package com.example.demo.event;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EventTest {

    //빌터 패턴 테스트
    //빌더 패선을 사용하여 Event 객체를 생성할 떄 객체가 제대로 생성되는지 확인.
    @Test
    public void builder() {
        Event event = Event.builder()
                .name("Inflearn String Rest Api")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    //자바빈즈 규약을 테스트
    //기본생성자와 setter 메서드가 잘 작동하는지 확인
    //기본생성자를 사용해서 Event 객체 생성, setter 사용하여 객체의 필드 값 설정, 필드 값이 올바르게 설정됐는지 확인.
    @Test
    public void javaBean() {
        //given
        String name = "Event";
        String description = "Spring";

        //when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);

        //then
        assertThat(event.getName()).isEqualTo(name);
        assertThat(event.getDescription()).isEqualTo(description);
    }
}