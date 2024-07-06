package online.pdp.spring_advanced.repository;

import online.pdp.spring_advanced.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
