package com.example.demo.event;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder   //builder 추가 시 기본생성자가 생성 안됨. 그리고  디폴트 생성자임. 그래서 아래의 어노테이션을 추가해야함.
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id") // 연관관계가 있을 떄 스택오버플로우가 발생할 수 있음. 따라서 id값으로 비교. 필드를 추가할 수도 있음. @EqualsAndHashCode(of = {"id", "name"}) 연관관계에 해당하는 것을 넣으면 안됨.
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name; //이름
    private String description; //설명
    private LocalDateTime beginEnrollmentDateTime; //이벤트 등록 시작시간
    private LocalDateTime closeEnrollmentDateTime; //종료 일시
    private LocalDateTime beginEventDateTime; // 이벤트 시작일시
    private LocalDateTime endEventDateTime; //이벤트 종료 일시
    private String location; // 위치(optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment; //참가 수 제한
    private boolean offline;
    private boolean free; //유료 무료 여부
    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus = EventStatus.DRAFT;
}
