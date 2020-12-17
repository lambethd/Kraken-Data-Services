package lambethd.kraken.application.controller;

import domain.DashboardDto;
import lambethd.kraken.application.interfaces.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private IDashboardService dashboardService;

    @RequestMapping
    public ResponseEntity<DashboardDto> getDashboard() {
        return buildResponseEntity(dashboardService.getDashboard());
    }
}
