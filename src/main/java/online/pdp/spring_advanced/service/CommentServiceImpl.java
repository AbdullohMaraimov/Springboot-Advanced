package online.pdp.spring_advanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.BlogDto;
import online.pdp.spring_advanced.dto.CommentDto;
import online.pdp.spring_advanced.entity.Blog;
import online.pdp.spring_advanced.entity.Comment;
import online.pdp.spring_advanced.mapper.BlogMapper;
import online.pdp.spring_advanced.mapper.CommentMapper;
import online.pdp.spring_advanced.repository.BlogRepository;
import online.pdp.spring_advanced.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ApplicationEventPublisher publisher;
    private final BlogRepository blogRepository;

    @Override
    @Transactional
    public Comment create(Integer id, CommentDto dto) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog Not found"));
        Comment comment = commentMapper.fromCommentDto(dto);
        comment.setBlog(blog);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public String update(Integer userId, CommentDto dto) {
        Comment comment = commentRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setAuthor(dto.getAuthor());
        comment.setMessage(dto.getMessage());

        commentRepository.save(comment);
        return "Updated successfully";
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public String delete(Integer id) {
        commentRepository.deleteById(id);
        return "Deleted successfully";
    }
}
