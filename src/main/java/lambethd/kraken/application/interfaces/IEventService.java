package lambethd.kraken.application.interfaces;

import org.springframework.stereotype.Service;
import portfolio.RangeType;
import runescape.Event;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface IEventService {
    List<Event> getEvents(RangeType rangeType);
    Event createEvent(Event event);
}
