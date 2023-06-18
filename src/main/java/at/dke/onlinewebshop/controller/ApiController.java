package at.dke.onlinewebshop.controller;

import at.dke.onlinewebshop.sql.services.DBFiller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final DBFiller dbFiller;

    @PostMapping("/filldatabase")
    public void fillDatabase() {
        dbFiller.fillDB();
    }

    @RequestMapping("/api/version")
    public Long apiVersion()   {
        return 1L;
    }
}