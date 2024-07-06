package online.pdp.spring_advanced.repository;

import online.pdp.spring_advanced.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
