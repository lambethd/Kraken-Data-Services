package lambethd.kraken.application.service;

import domain.orchestration.IJob;
import domain.orchestration.JobDetail;
import lambethd.kraken.application.interfaces.IJobService;
import lambethd.kraken.data.mongo.repository.IJobDetailRepository;
import lambethd.kraken.data.mongo.repository.IJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JobService implements IJobService {
    @Autowired
    private IJobRepository jobRepository;
    @Autowired
    private IJobDetailRepository jobDetailRepository;

    @Override
    public List<IJob> getJobs(int runeDay, String username) {
        List<IJob> systemJobs = jobRepository.findByUsernameAndRuneDay("System", runeDay);
        List<IJob> userJobs = jobRepository.findByUsernameAndRuneDay(username, runeDay);
        return Stream.concat(systemJobs.stream(), userJobs.stream()).collect(Collectors.toList());
    }

    @Override
    public List<JobDetail> getJobDetails() {
        return jobDetailRepository.findAll();
    }
}
