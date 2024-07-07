package online.pdp.spring_advanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.PostCreateDto;
import online.pdp.spring_advanced.dto.PostUpdateDto;
import online.pdp.spring_advanced.entity.Post;
import online.pdp.spring_advanced.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final ConcurrentHashMap<Integer, Post> postCache = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public Post create(PostCreateDto dto) {
        return null;
    }

    @Override
    public Post findById(Integer id) throws InterruptedException {

        Post cashedPost = postCache.get(id);
        if (cashedPost != null) {
            return cashedPost;
        }

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with not found"));
        TimeUnit.SECONDS.sleep(2);

        postCache.put(id, post);

        return post;
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        postCache.remove(id);
    }

    @Override
    public void update(PostUpdateDto dto) {

        Post post = postRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setBody(dto.getBody());
        post.setTitle(dto.getTitle());
        postRepository.save(post);

        postCache.put(dto.getId(), post);
    }
}
