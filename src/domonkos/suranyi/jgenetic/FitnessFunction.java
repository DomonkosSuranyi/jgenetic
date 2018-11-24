package domonkos.suranyi.jgenetic;

@FunctionalInterface
public interface FitnessFunction<T> {
    public double calculateFitness(final T specimen);
}
