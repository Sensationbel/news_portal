package by.bulaukin.news_portal.services;

import by.bulaukin.news_portal.model.User;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;

import java.util.List;

public interface UsersService {

    List<User> findAll(EntityFilter usersFilter);

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
