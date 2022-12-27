package com.dimple.gateway.handler;

import com.dimple.common.core.exception.CaptchaException;
import com.dimple.common.core.web.vo.params.AjaxResult;
import com.dimple.gateway.service.ValidateCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * 验证码获取
 *
 * @author Dimple
 */
@Component
@RequiredArgsConstructor
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {
    private final ValidateCodeService validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        AjaxResult ajax;
        try {
            ajax = validateCodeService.createCaptcha();
        } catch (CaptchaException | IOException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}
