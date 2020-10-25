package lambethd.kraken.application.service;

import domain.orchestration.Job;
import domain.orchestration.JobDetail;
import lambethd.kraken.application.interfaces.IJobService;
import lambethd.kraken.data.repository.IJobDetailRepository;
import lambethd.kraken.data.repository.internal.InternalJobRepository;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    private InternalJobRepository jobRepository;
    @Autowired
    private IJobDetailRepository jobDetailRepository;

    @Override
    public List<Job> getJobs(int runeDay, String username) {
        return jobRepository.findByUsernameAndRuneDay(username, runeDay);
    }

    @Override
    public List<JobDetail> getJobDetails() {
        return Lists.from(jobDetailRepository.findAll().iterator());
    }
}
