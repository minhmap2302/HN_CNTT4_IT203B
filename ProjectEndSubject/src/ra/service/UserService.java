package ra.service;

import ra.entity.User;

public interface UserService {
    void register(User user);
    User login(String email, String password);
}