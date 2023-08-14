package com.desafiotres.compass.decoder;

import com.desafiotres.compass.exception.PostNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class PostNotFoundExceptionDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 404) {
            return new PostNotFoundException("Post not found");
        }
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}