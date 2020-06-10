package lambethd.kraken.application.interfaces;

public interface IStrategy <T> {
    T beforeAction(T oldItem, T newItem);
    T completeAction(T oldItem, T newItem);
    T afterAction(T oldItem, T newItem);

    T completeAll(T oldItem, T newItem);
}
