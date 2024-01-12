package by.bulaukin.news_portal.web.model.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewsFilter extends EntityFilter {

    private Long userId;
    private Long newsId;
    private Character newsType;

}
