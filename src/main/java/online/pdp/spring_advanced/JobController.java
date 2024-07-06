package online.pdp.spring_advanced;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/api/add/job")
    public String addJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

}
