package domonkos.suranyi.jgenetic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GeneticSystem<T> {
    private final FitnessFunction<T> fitnessFunction;
    private final List<T> generation;
    
    public GeneticSystem(final Supplier<T> randomInitializer, final FitnessFunction<T> fitnessFunction,
            final int numberOfSpecimen) {
        this.fitnessFunction = fitnessFunction;
        
        // Initialize the first generation with random values
        this.generation = new ArrayList<>(numberOfSpecimen);
        for (int i = 0; i < numberOfSpecimen; i++) {
            this.generation.add(randomInitializer.get());
        }
    }
    
    public List<T> getActualGeneration() {
        return new ArrayList<>(generation);
    }
}
