package lambethd.kraken.application.interfaces;

import domain.orchestration.IJob;
import domain.orchestration.JobDetail;

import java.util.List;

public interface IJobService {
    List<IJob> getJobs(int runeDay, String username);

    List<JobDetail> getJobDetails();
}
