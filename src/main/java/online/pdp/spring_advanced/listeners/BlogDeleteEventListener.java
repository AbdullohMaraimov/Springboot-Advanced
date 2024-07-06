package online.pdp.spring_advanced.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import online.pdp.spring_advanced.entity.Blog;
import online.pdp.spring_advanced.entity.Comment;
import online.pdp.spring_advanced.events.BlogDeletedEvent;
import online.pdp.spring_advanced.repository.BlogRepository;
import online.pdp.spring_advanced.repository.CommentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class BlogDeleteEventListener {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    @EventListener({BlogDeletedEvent.class})
    public void blogDeleteEventListener(BlogDeletedEvent event) {

        log.info("---------------- Blog delete event is listened ---------------------");

        Blog blog = event.getBlog();
        List<Comment> comments = commentRepository.findAll();
        for (Comment comment : comments) {
            if (Objects.equals(comment.getBlog().getId(), blog.getId())){
                commentRepository.delete(comment);
            }
        }
    }

}
