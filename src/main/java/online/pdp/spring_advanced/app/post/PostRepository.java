package online.pdp.spring_advanced.app.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.user.id = ?1")
    List<Post> findByUser_Id(Integer id);

}
