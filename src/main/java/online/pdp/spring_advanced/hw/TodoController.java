package online.pdp.spring_advanced.hw;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Todo findOne(@PathVariable Integer id) {
        return todoRepository.findById(id).orElse(null);
    }

    @SchemaMapping(typeName = "Query", value = "getTodo")
    public Todo getTodo(@Argument Integer id) {
        return todoRepository.findById(id).orElse(null);
    }

    @QueryMapping("getTodos")
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @QueryMapping("todosByLevel")
    public List<Todo> getTodosByLevel(@Argument Level level) {
        return todoRepository.findByLevel(level);
    }

    @QueryMapping("todosByCategory")
    public List<Todo> getTodosByCategory(@Argument Category category) {
        return todoRepository.findByCategory(category);
    }

    @QueryMapping("todosByDeadLine")
    public List<Todo> getTodosByDeadLine(@Argument String deadLine) {
        return todoRepository.findByDeadLine(LocalDate.parse(deadLine));
    }

    @MutationMapping
    public User createUser(
            @Argument String fullName,
            @Argument String email,
            @Argument String password
    ) {
        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .password(password)
                .build();

        return userRepository.save(user);
    }

    @MutationMapping
    public Todo createTodo(
            @Argument Integer userId,
            @Argument String title,
            @Argument String description,
            @Argument Category category,
            @Argument Level level,
            @Argument String deadLine
    ) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = Todo.builder()
                .title(title)
                .description(description)
                .category(category)
                .level(level)
                .deadLine(LocalDate.parse(deadLine))
                .completed(false)
                .user(user)
                .build();

        return todoRepository.save(todo);
    }

    @MutationMapping
    public Todo completeTodo(
            @Argument Integer id
    ) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setCompleted(true);
        return todoRepository.save(todo);
    }

    @MutationMapping
    public Boolean deleteTodo(
            @Argument Integer id
    ) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepository.delete(todo);
        return true;
    }

    @MutationMapping
    public Todo updateTodo(
            @Argument Integer id,
            @Argument String title,
            @Argument String description,
            @Argument Category category,
            @Argument Level level,
            @Argument String deadLine,
            @Argument Boolean completed
    ) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));

        if (title != null) {
            todo.setTitle(title);
        }

        if (description != null) {
            todo.setDescription(description);
        }

        if (category != null) {
            todo.setCategory(category);
        }

        if (level != null) {
            todo.setLevel(level);
        }

        if (deadLine != null) {
            todo.setDeadLine(LocalDate.parse(deadLine));
        }

        if (completed != null) {
            todo.setCompleted(completed);
        }

        return todoRepository.save(todo);
    }
}

