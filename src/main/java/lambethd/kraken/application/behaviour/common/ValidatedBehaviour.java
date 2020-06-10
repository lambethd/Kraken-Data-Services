package lambethd.kraken.application.behaviour.common;

import domain.TrackedDocument;
import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.interfaces.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatedBehaviour<T extends TrackedDocument> implements IBehaviour<T> {
    @Autowired
    private List<IValidator<T>> validators;

    @Override
    public T beforeAction(T oldItem, T newItem) {
        for (IValidator<T> validator : validators) {
            if (validator.canValidate(newItem)) {
                validator.validate(newItem);
                validator.validate(oldItem, newItem);
            }
        }
        return newItem;
    }

    @Override
    public T completeAction(T oldItem, T newItem) {
        return newItem;
    }

    @Override
    public T afterAction(T oldItem, T newItem) {
        return newItem;
    }
}
