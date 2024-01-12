package by.bulaukin.news_portal.mapper;

import by.bulaukin.news_portal.model.User;
import by.bulaukin.news_portal.web.model.request.UpsertUserRequest;
import by.bulaukin.news_portal.web.model.response.UserResponse;
import by.bulaukin.news_portal.web.model.response.UsersListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface UsersMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    @Mapping(source = "id", target = "userId")
    UserResponse userToResponse(User user);

    default UsersListResponse usersListToUsersListResponse(List<User> users){
        UsersListResponse response = new UsersListResponse();
        response.setUsers(users.stream()
                .map(this ::userToResponse).collect(Collectors.toList()));
        return response;
    }
}
