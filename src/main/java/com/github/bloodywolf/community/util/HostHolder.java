package com.github.bloodywolf.community.util;

import com.github.bloodywolf.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/18 16:09
 * <p>
 * 持有用户信息,用于代替session对象.
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
