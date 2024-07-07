package online.pdp.spring_advanced;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import online.pdp.spring_advanced.entity.Post;
import online.pdp.spring_advanced.repository.PostRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.URL;
import java.util.List;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class SpringAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAdvancedApplication.class, args);
	}


	@Bean
	public ApplicationRunner applicationRunner(ObjectMapper objectMapper, PostRepository postRepository) {
		return (args -> {

			List<Post> posts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"),
					new TypeReference<>(){});

			postRepository.saveAll(posts);
		});
	}

	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		cacheManager.setCacheNames(List.of("posts", "users", "comments"));
		return cacheManager;
	}

}
