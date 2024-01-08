package by.bulaukin.news_portal.web.model.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsFilter extends EntityFilter{

    private Long userId;
    private Long newsId;


}
