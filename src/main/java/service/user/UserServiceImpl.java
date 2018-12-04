package service.user;

import java.util.Collections;
import model.user.Role;
import model.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
        BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User create(User user) {
        User currentUser = new User();
        currentUser.setName(user.getName());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        currentUser.setRoles(Collections.singleton(Role.USER));
        return currentUser;
    }

}
