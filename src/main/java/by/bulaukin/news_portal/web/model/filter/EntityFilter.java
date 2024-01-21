package by.bulaukin.news_portal.web.model.filter;


import by.bulaukin.news_portal.validator.EntityFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EntityFilterValid
public class EntityFilter {

    private Integer pageSize;
    private Integer pageNum;
    private Long userId;
    private Long newsId;
    private Character newsType;
}
