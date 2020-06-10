package lambethd.kraken.application.validation;

import org.springframework.stereotype.Service;
import portfolio.Trade;

@Service
public class TradeValidator extends ValidatorBase<Trade> {
    @Override
    public void validate(Trade input) {
        //TODO: validate based on the trade e.g. non-null fields
    }

    @Override
    public void validate(Trade oldItem, Trade newItem) {
        //TODO: validate based on trade status e.g. moving from one status to another
    }
}
