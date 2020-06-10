package lambethd.kraken.application.validation;

import org.springframework.stereotype.Service;
import portfolio.Spread;

@Service
public class SpreadValidator extends ValidatorBase<Spread> {
    @Override
    public void validate(Spread input) {
        //TODO: validate based on the trade e.g. non-null fields
    }

    @Override
    public void validate(Spread oldItem, Spread newItem) {
        //TODO: validate based on trade status e.g. moving from one status to another
    }
}
