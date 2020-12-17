package lambethd.kraken.application.service;

import domain.DashboardDto;
import lambethd.kraken.application.interfaces.IDashboardService;
import lambethd.kraken.data.mongo.repository.IDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService implements IDashboardService {
    @Autowired
    private IDashboardRepository dashboardRepository;

    @Override
    public DashboardDto getDashboard() {
        return dashboardRepository.findAll().get(0);
    }
}
