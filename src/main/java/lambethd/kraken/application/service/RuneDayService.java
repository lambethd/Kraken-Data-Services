package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IRuneDayService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class RuneDayService implements IRuneDayService {
    @Override
    public int transform(LocalDateTime date) {
        LocalDateTime runeDayBeginningOfTime = LocalDateTime.of(2002, 2, 27, 0, 0);

        return (int) runeDayBeginningOfTime.until(LocalDateTime.now(), ChronoUnit.DAYS);
    }

    @Override
    public LocalDateTime transform(int runeDay) {
        LocalDateTime runeDayBeginningOfTime = LocalDateTime.of(2002, 2, 27, 0, 0);

        return runeDayBeginningOfTime.plusDays(runeDay);
    }
}
