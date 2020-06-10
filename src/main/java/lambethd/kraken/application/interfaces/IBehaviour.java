package lambethd.kraken.application.interfaces;

import domain.IDocument;

public interface IBehaviour<T extends IDocument> {
    T beforeAction(T oldDocument, T newDocument);

    T completeAction(T oldDocument, T newDocument);

    T afterAction(T oldDocument, T newDocument);
}
