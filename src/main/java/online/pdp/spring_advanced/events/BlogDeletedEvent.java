package online.pdp.spring_advanced.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.entity.Blog;

@Getter
@RequiredArgsConstructor
public final class BlogDeletedEvent {
    private final Blog blog;
}
