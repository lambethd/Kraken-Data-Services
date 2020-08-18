package lambethd.kraken.application.controller;

import lambethd.kraken.application.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.RangeType;
import runescape.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController extends BaseController {

    @Autowired
    private IEventService eventService;

    @RequestMapping("/{range}")
    public ResponseEntity<List<Event>> getEvents(@PathVariable RangeType range) {
        return buildResponseEntity(eventService.getEvents(range));
    }

    @RequestMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        return buildResponseEntity(eventService.createEvent(event));
    }
}
