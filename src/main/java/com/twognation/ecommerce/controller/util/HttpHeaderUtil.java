package com.twognation.ecommerce.controller.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class HttpHeaderUtil {

    public static HttpHeaders createLocation(String path, Long id) {
        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path(path)
                .buildAndExpand(id)
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);
        return headers;
    }
}
