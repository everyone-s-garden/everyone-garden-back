package com.everyonegarden.auth.client;

import com.everyonegarden.user.entity.User;

public interface ClientProxy {

    User getUserDate(String accessToken);
}
