package online.pdp.spring_advanced.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.pdp.spring_advanced.dto.PostCreateDto;
import online.pdp.spring_advanced.dto.PostUpdateDto;
import online.pdp.spring_advanced.entity.Post;
import online.pdp.spring_advanced.repository.PostRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CacheManager cacheManager;
    private final Cache cache;

    public PostServiceImpl(PostRepository postRepository, CacheManager cacheManager) {
        this.postRepository = postRepository;
        this.cacheManager = cacheManager;
        this.cache = cacheManager.getCache("posts");
    }

    @Override
    @Transactional
    public Post create(PostCreateDto dto) {
        return null;
    }

    @Override
    public Post findById(Integer id) throws InterruptedException {

        Post cashedPost = cache.get(id, Post.class);
        if (cashedPost != null) {
            return cashedPost;
        }

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post with not found"));
        TimeUnit.SECONDS.sleep(2);

        cache.put(id, post);

        return post;
    }

    @Override
    public void delete(Integer id) {
        postRepository.deleteById(id);
        cache.evict(id);
    }

    @Override
    public void update(PostUpdateDto dto) {

        Post post = postRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setBody(dto.getBody());
        post.setTitle(dto.getTitle());
        postRepository.save(post);

        cache.put(dto.getId(), post);
    }
}
