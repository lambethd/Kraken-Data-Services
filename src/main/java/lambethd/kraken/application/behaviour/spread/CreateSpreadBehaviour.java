package lambethd.kraken.application.behaviour.spread;

import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import portfolio.Spread;

@Service
public class CreateSpreadBehaviour implements IBehaviour<Spread> {
    @Autowired
    private ISpreadRepository spreadRepository;
    @Autowired
    private IAuthService authService;

    @Override
    public Spread beforeAction(Spread oldDocument, Spread newDocument) {
        newDocument.setSpreadSize(newDocument.getUpperBound() - newDocument.getLowerBound());
        newDocument.setRoi(newDocument.getSpreadSize() / newDocument.getLowerBound() * 100);
        newDocument.setUsername(authService.getCurrentUser());
        return newDocument;
    }

    @Override
    public Spread completeAction(Spread oldDocument, Spread newDocument) {
        return spreadRepository.save(newDocument);
    }

    @Override
    public Spread afterAction(Spread oldDocument, Spread newDocument) {
        return newDocument;
    }
}
