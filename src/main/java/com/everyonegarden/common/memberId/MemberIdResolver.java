package com.everyonegarden.common.memberId;

import com.everyonegarden.auth.CustomUser;
import com.everyonegarden.common.exception.UnauthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class MemberIdResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        CustomUser member;

        try {
            member = (CustomUser) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

        } catch (Exception e) {
            throw new UnauthorizedException("JWT token을 다시 확인해 주세요");
        }

        return member.getMemberId();
    }

}
