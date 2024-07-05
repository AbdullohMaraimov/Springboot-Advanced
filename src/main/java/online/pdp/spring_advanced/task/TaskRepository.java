package online.pdp.spring_advanced.task;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String TASKS_SELECT_QUERY = "select * from tasks;";

    public List<Task> getAll() {
        return jdbcTemplate.query(TASKS_SELECT_QUERY, BeanPropertyRowMapper.newInstance(Task.class));
    }

}
