package lambethd.kraken.application.interfaces;

import portfolio.RangeType;

import java.time.LocalDateTime;

public interface IRangeService {
    LocalDateTime getDateFromRange(RangeType range);
}
