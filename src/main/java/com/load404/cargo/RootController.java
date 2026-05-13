package com.load404.cargo;
import org.springframework.web.bind.annotation.*;

@RestController
public class RootController {
    @GetMapping("/alive")
    public String alive() {
        return "JAVA_BACKEND_ONLINE";
    }
}
