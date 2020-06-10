package lambethd.kraken.application.validation;

import lambethd.kraken.application.interfaces.IValidator;

import java.lang.reflect.ParameterizedType;

public abstract class ValidatorBase<T> implements IValidator<T> {
    Class<T> type;

    public ValidatorBase() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean canValidate(Object item) {
        return type.isInstance(item);
    }
}
