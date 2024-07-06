package online.pdp.spring_advanced.service;

import online.pdp.spring_advanced.dto.CommentDto;
import online.pdp.spring_advanced.entity.Comment;

import java.util.List;


public interface CommentService {

    Comment create(Integer id, CommentDto dto);

    String update(Integer userId, CommentDto dto);

    List<Comment> getAll();

    String delete(Integer id);

}
