package by.bulaukin.news_portal.web.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserRequest {

    @NotBlank(message = "Имя пользователя должно быть заполнено!")
    private String username;

    @Email
    private String email;
}
