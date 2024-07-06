package online.pdp.spring_advanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.BlogDto;
import online.pdp.spring_advanced.dto.UserCreateDto;
import online.pdp.spring_advanced.entity.Blog;
import online.pdp.spring_advanced.entity.User;
import online.pdp.spring_advanced.events.BlogDeletedEvent;
import online.pdp.spring_advanced.events.UserCreatedEvent;
import online.pdp.spring_advanced.events.UserUpdateEvent;
import online.pdp.spring_advanced.mapper.BlogMapper;
import online.pdp.spring_advanced.mapper.UserMapper;
import online.pdp.spring_advanced.repository.BlogRepository;
import online.pdp.spring_advanced.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService{

    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Blog create(BlogDto dto) {
        Blog blog = blogMapper.fromBlogDto(dto);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public String update(Integer userId, BlogDto dto) {
        Blog blog = blogRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        blog.setTitle(dto.getTitle());
        blog.setDescription(dto.getDescription());

        blogRepository.save(blog);
        return "Updated successfully";
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public String delete(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        publisher.publishEvent(new BlogDeletedEvent(blog));
        blogRepository.deleteById(id);
        return "Deleted successfully";
    }
}
