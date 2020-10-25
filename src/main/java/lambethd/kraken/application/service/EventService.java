package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IEventService;
import lambethd.kraken.application.interfaces.IRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.RangeType;
import runescape.Event;

import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private IEventRepository eventRepository;
    @Autowired
    private IRangeService rangeService;

    @Override
    public List<Event> getEvents(RangeType rangeType) {
        return eventRepository.getEventByDateGreaterThan(rangeService.getDateFromRange(rangeType));
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.insert(event);
    }
}
