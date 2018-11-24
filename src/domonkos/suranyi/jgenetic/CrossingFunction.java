package domonkos.suranyi.jgenetic;

@FunctionalInterface
public interface CrossingFunction<T> {
    
    public T cross(final T specimen1, final T specimen2);
}
