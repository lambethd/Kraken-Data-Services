package lambethd.kraken.application.interfaces;

import domain.orchestration.Job;
import domain.orchestration.JobDetail;

import java.util.List;

public interface IJobService {
    List<Job> getJobs(int runeDay, String username);

    List<JobDetail> getJobDetails();
}
