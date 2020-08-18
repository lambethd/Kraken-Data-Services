package lambethd.kraken.application.service;

import lambethd.kraken.application.interfaces.IRangeService;
import org.springframework.stereotype.Service;
import portfolio.RangeType;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class RangeService implements IRangeService {
    @Override
    public LocalDateTime getDateFromRange(RangeType rangeType) {
        LocalDateTime startDate;
        switch (rangeType) {
            case ALL_TIME:
                startDate = LocalDateTime.of(2000,1,1,0,0);
                break;
            case YEAR:
                startDate = LocalDateTime.now(ZoneOffset.UTC).minusYears(1);
                break;
            case QUARTER:
                startDate = LocalDateTime.now(ZoneOffset.UTC).minusMonths(3);
                break;
            case MONTH:
                startDate = LocalDateTime.now(ZoneOffset.UTC).minusMonths(1);
                break;
            case WEEK:
            default:
                startDate = LocalDateTime.now(ZoneOffset.UTC).minusDays(7);
        }
        return startDate.withHour(0).withMinute(0).withSecond(0);
    }
}
