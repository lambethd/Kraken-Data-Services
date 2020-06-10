package lambethd.kraken.application.behaviour.common;

import domain.TrackedDocument;
import lambethd.kraken.application.interfaces.IAuthService;
import lambethd.kraken.application.interfaces.IBehaviour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateLastUpdatedBehaviour<T extends TrackedDocument> implements IBehaviour<T> {
    @Autowired
    private IAuthService authService;

    @Override
    public T beforeAction(T oldDocument, T newDocument) {
        //TODO: does this get messed up by daylight savings?
        newDocument.setLastUpdated(LocalDateTime.now());
        newDocument.setUpdatedBy(authService.getCurrentUser());
        return newDocument;
    }

    @Override
    public T completeAction(T oldDocument, T newDocument) {
        return newDocument;
    }

    @Override
    public T afterAction(T oldDocument, T newDocument) {
        return newDocument;
    }
}
