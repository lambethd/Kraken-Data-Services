package lambethd.kraken.application.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.Trade;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/trade")
public class TradeController {

    @RequestMapping
    public List<Trade> getAllTrades(){
        return null;
    }
}
