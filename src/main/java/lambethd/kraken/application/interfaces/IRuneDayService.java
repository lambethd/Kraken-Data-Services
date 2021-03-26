package lambethd.kraken.application.interfaces;

import java.time.LocalDateTime;

public interface IRuneDayService {
    public int transform(LocalDateTime date);
    public LocalDateTime transform(int runeDay);
}
