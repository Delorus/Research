package ru.sherb.research.struct.ee;

/**
 * @author maksim
 * @since 11.03.2020
 */
public class ObjectCreatedEvent<T> implements EventEmitter<T> {

    private final T source;

    public ObjectCreatedEvent(T source) {
        this.source = source;
    }
}
