package at.dke.onlinewebshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiController {

    @RequestMapping("/api/version")
    public Long apiVersion()   {
        return 1L;
    }
}