package com.example.demo.event;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;



    @PostMapping
    public ResponseEntity createEvent(@RequestBody EventDto eventDto){
        Event event = modelMapper.map(eventDto, Event.class);
        Event newEvent = this.eventRepository.save(event);
        URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
        return ResponseEntity.created(createdUri).body(event);
    }
}
