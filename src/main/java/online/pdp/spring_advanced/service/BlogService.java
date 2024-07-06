package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.dto.BlogDto;
import online.pdp.spring_advanced.entity.Blog;

import java.util.List;


public interface BlogService {

    Blog create(BlogDto dto);

    String update(Integer userId, BlogDto dto);

    List<Blog> getAll();

    String delete(Integer id);

}
