package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.UsersMapper;
import by.bulaukin.news_portal.model.User;
import by.bulaukin.news_portal.services.UsersService;
import by.bulaukin.news_portal.web.model.request.UpsertUserRequest;
import by.bulaukin.news_portal.web.model.response.UserResponse;
import by.bulaukin.news_portal.web.model.response.UsersListResponse;
import by.bulaukin.news_portal.web.model.filter.UsersFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @GetMapping
    public ResponseEntity<UsersListResponse> findAll(@Valid UsersFilter filter){
        return ResponseEntity.ok(usersMapper.usersListToUsersListResponse(
                usersService.findAll(filter)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usersMapper.userToResponse(usersService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User user = usersService.save(usersMapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersMapper.userToResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId,
                                               @RequestBody @Valid UpsertUserRequest request){
        User updatedUser = usersService.update(usersMapper.requestToUser(userId, request));
        return ResponseEntity.ok(usersMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
