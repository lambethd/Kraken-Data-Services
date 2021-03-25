package lambethd.kraken.application.controller;

import domain.orchestration.IJob;
import domain.orchestration.JobDetail;
import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IJobService;
import lambethd.kraken.application.interfaces.IRuneDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController extends BaseController {

    @Autowired
    private IJobService jobService;
    @Autowired
    private IAuthService authService;
    @Autowired
    private IRuneDayService runeDayService;

    @RequestMapping("/{runeDay}")
    public ResponseEntity<List<IJob>> getJobs(@PathVariable int runeDay) {
        try {
            LocalDateTime date = LocalDateTime.parse("" + runeDay, DateTimeFormatter.ofPattern("yyyyMMdd"));
            return buildResponseEntity(jobService.getJobs(runeDayService.transform(date), authService.getCurrentUser()));
        } catch (Exception e) {
            return buildResponseEntity(jobService.getJobs(runeDay, authService.getCurrentUser()));
        }
    }

    @RequestMapping("/job-details")
    public ResponseEntity<List<JobDetail>> getJobDetails() {
        return buildResponseEntity(jobService.getJobDetails());
    }
}
