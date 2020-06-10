package lambethd.kraken.application.interfaces;

public interface IMapper<T1, T2> {
    T1 mapForward(T2 in);
    T2 mapBack(T1 in);
}
