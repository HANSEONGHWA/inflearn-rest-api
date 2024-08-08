package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
//produces = MediaTypes.HAL_JSON_VALUE 클래스 안의 모든 핸들러들은 이와 같은 컨텐츠 타입으로 응답을 보내게 됨.
@RequestMapping(value = "/api/events",produces = MediaTypes.HAL_JSON_VALUE)
@AllArgsConstructor
public class EventController {

    private final EventRepository eventRepository;



    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event){
        Event newEvent = this.eventRepository.save(event);
        // created를 보낼 때 항상 URI가 있어야 함.
        // HATEOS가 제공하는 linkTo(), methodOn() 사용해서 URI를 생성 후 return.
        //@RequestMapping 하여 methodOn 제거
        URI createdUri = linkTo(EventController.class).slash(event.getId()).toUri();
        //createdUri = http://localhost/api/events/%7Bid%7D
//        event.setId(10);
        return ResponseEntity.created(createdUri).body(event);
    }
}
