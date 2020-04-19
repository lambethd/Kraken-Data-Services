package lambethd.kraken.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runescape.Runeday;

@RestController
@RequestMapping("/runeday")
public class RunedayController {

    @RequestMapping
    public Runeday getCurrentRuneday() {
        return null;
    }
}
