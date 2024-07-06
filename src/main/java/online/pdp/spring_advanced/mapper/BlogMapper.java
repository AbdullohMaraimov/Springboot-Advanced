package online.pdp.spring_advanced.mapper;

import online.pdp.spring_advanced.dto.BlogDto;
import online.pdp.spring_advanced.entity.Blog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogMapper {

    Blog fromBlogDto(BlogDto blogDto);

}
