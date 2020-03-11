package ru.sherb.research.struct.ee;

/**
 * @author maksim
 * @since 11.03.2020
 */
public class ObjectUpdatedEvent<T> implements EventEmitter<T> {

    private final T oldObj;
    private final T newObj;

    public ObjectUpdatedEvent(T oldObj, T newObj) {
        this.oldObj = oldObj;
        this.newObj = newObj;
    }
}
