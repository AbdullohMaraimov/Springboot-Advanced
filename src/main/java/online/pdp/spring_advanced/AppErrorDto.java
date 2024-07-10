package online.pdp.spring_advanced;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppErrorDto {
    private String errorPath;
    private Integer errorCode;
    private String friendlyMessage;
    private Object developerMessage;
}
