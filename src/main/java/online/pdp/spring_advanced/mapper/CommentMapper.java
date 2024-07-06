package online.pdp.spring_advanced.mapper;

import online.pdp.spring_advanced.dto.CommentDto;
import online.pdp.spring_advanced.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment fromCommentDto(CommentDto dto);
}
