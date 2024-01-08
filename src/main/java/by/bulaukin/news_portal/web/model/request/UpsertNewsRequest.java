package by.bulaukin.news_portal.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpsertNewsRequest {

    @NotNull(message = "ID должно быть указано!")
    @Positive(message = "ID должно быть больше )!")
    private Long userId;

//    @NotBlank(message = "Код типа новости должен быть указан!")
    private Character codeNews;

    @NotBlank(message = "Текст новости должен быть указан!")
    private String text;


}
