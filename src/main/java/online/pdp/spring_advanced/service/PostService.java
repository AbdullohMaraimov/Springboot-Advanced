package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.dto.PostCreateDto;
import online.pdp.spring_advanced.dto.PostUpdateDto;
import online.pdp.spring_advanced.entity.Post;


public interface PostService {

    Post create(PostCreateDto dto);

    Post findById(Integer id) throws InterruptedException;

    void delete(Integer id);

    void update(PostUpdateDto dto);
}
