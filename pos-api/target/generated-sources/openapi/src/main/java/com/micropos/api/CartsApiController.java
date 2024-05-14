package com.micropos.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-14T21:04:56.395113+08:00[Asia/Shanghai]")
@Controller
@RequestMapping("${openapi.microPos.base-path:/api}")
public class CartsApiController implements CartsApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CartsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
