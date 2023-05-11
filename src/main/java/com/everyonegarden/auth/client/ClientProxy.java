package com.everyonegarden.auth.client;

import com.everyonegarden.member.entity.Member;

public interface ClientProxy {

    Member getUserData(String accessToken);
}
