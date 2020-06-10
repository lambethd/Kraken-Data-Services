package lambethd.kraken.application.strategies.common;

import domain.IDocument;
import lambethd.kraken.application.interfaces.IBehaviour;
import lambethd.kraken.application.interfaces.IStrategy;

import java.util.ArrayList;

public abstract class StrategyBase<T extends IDocument> implements IStrategy<T> {
    protected abstract ArrayList<IBehaviour<T>> getBehaviours();

    @Override
    public T beforeAction(T oldItem, T newItem) {
        for (IBehaviour<T> behaviour : getBehaviours()) {
            newItem = behaviour.beforeAction(oldItem, newItem);
        }
        return newItem;
    }

    @Override
    public T completeAction(T oldItem, T newItem) {
        for (IBehaviour<T> behaviour : getBehaviours()) {
            newItem = behaviour.completeAction(oldItem, newItem);
        }
        return newItem;
    }

    @Override
    public T afterAction(T oldItem, T newItem) {
        for (IBehaviour<T> behaviour : getBehaviours()) {
            newItem = behaviour.afterAction(oldItem, newItem);
        }
        return newItem;
    }

    @Override
    public T completeAll(T oldItem, T newItem) {
        newItem = beforeAction(oldItem, newItem);
        newItem = completeAction(oldItem, newItem);
        newItem = afterAction(oldItem, newItem);
        return newItem;
    }
}
