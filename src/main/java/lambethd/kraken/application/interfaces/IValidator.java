package lambethd.kraken.application.interfaces;

public interface IValidator<T> {
    void validate(T input);

    void validate(T oldItem, T newItem);

    boolean canValidate(Object item);
}
