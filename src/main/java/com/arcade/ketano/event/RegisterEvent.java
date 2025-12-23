package com.arcade.ketano.event;

import com.arcade.ketano.model.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegisterEvent extends ApplicationEvent {
    private User user;
    private String url;

    public RegisterEvent(User user, String url) {
        super(user);
        this.user = user;
        this.url = url;
    }
}
