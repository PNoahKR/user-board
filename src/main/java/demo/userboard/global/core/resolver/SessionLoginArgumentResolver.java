package demo.userboard.global.core.resolver;

import demo.userboard.global.annotation.SessionAuth;
import demo.userboard.global.common.auth.SessionLoginInfo;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.core.exception.CustomException;
import demo.userboard.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static demo.userboard.SessionConst.LOGIN_USER;

@Slf4j
@Component
@RequiredArgsConstructor
public class SessionLoginArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(SessionAuth.class);
        boolean isLongInfo = SessionLoginInfo.class.isAssignableFrom(parameter.getParameterType());

        return hasAnnotation && isLongInfo;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (ObjectUtils.isEmpty(httpSession.getAttribute(LOGIN_USER))) {
            throw new CustomException(CustomErrorCode.UNAUTHORIZED);
        }

        Long userId = (Long) httpSession.getAttribute(LOGIN_USER);

        return userRepository.findById(userId)
                .map(SessionLoginInfo::from)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_LOGIN_INFO));
    }
}
