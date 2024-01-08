package by.bulaukin.news_portal.services.impl;

import by.bulaukin.news_portal.exception.EntityNotFoundException;
import by.bulaukin.news_portal.model.User;
import by.bulaukin.news_portal.repository.UserRepository;
import by.bulaukin.news_portal.services.UsersService;
import by.bulaukin.news_portal.web.model.filter.UsersFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll(UsersFilter filter) {
        return userRepository.findAll(PageRequest.of(filter.getPageNum(), filter.getPageSize())).getContent();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("User with id: {0} is not found", id)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public User update(User user) {
        User exsistUser = findById(user.getId());

        BeanUtils.copyProperties( exsistUser,user);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
