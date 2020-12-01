package lambethd.kraken.application.service;

import domain.orchestration.IJob;
import domain.orchestration.JobDetail;
import lambethd.kraken.application.interfaces.IJobService;
import lambethd.kraken.data.mongo.repository.IJobDetailRepository;
import lambethd.kraken.data.mongo.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    private IJobRepository jobRepository;
    @Autowired
    private IJobDetailRepository jobDetailRepository;

    @Override
    public List<IJob> getJobs(int runeDay, String username) {
        return jobRepository.findByUsernameAndRuneDay(username, runeDay);
    }

    @Override
    public List<JobDetail> getJobDetails() {
        return jobDetailRepository.findAll();
    }
}
