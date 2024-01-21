package by.bulaukin.news_portal.web.model.request;

import by.bulaukin.news_portal.validator.EntityFilterValid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@EntityFilterValid
public class UpsertNewsRequest {

    private Character codeNews;
    @NotBlank(message = "Текст новости должен быть указан!")
    private String text;


}
