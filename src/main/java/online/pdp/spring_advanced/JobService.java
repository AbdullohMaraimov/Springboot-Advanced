package online.pdp.spring_advanced;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public String addJob(Job job) {
        jobRepository.save(job);
        return "JOb added";
    }

}
