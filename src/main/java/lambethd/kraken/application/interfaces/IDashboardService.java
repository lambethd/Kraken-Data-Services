package lambethd.kraken.application.interfaces;

import domain.DashboardDto;
import org.springframework.stereotype.Service;

@Service
public interface IDashboardService {
    DashboardDto getDashboard();
}
