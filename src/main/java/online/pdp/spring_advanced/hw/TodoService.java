package online.pdp.spring_advanced.hw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TodoRepository todoRepository;

    public User createUser(String fullName, String email, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Todo createTodo(int userId, String title, String description, Category category, Level level, String deadLine) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCategory(category);
        todo.setLevel(level);
        todo.setDeadLine(LocalDate.parse(deadLine));
        todo.setCompleted(false);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public Todo completeTodo(int id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(true);
        return todoRepository.save(todo);
    }

    public boolean deleteTodo(int id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
        return true;
    }

    public Todo updateTodo(int id, String title, String description, Category category, Level level, String deadLine, Boolean completed) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        if (title != null) todo.setTitle(title);
        if (description != null) todo.setDescription(description);
        if (category != null) todo.setCategory(category);
        if (level != null) todo.setLevel(level);
        if (deadLine != null) todo.setDeadLine(LocalDate.parse(deadLine));
        if (completed != null) todo.setCompleted(completed);
        return todoRepository.save(todo);
    }

    public List<Todo> getTodosByLevel(Level level) {
        return todoRepository.findByLevel(level);
    }

    public List<Todo> getTodosByCategory(Category category) {
        return todoRepository.findByCategory(category);
    }

    public List<Todo> getTodosByDeadLine(LocalDate deadLine) {
        return todoRepository.findByDeadLine(deadLine);
    }
}
