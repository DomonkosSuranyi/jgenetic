package domonkos.suranyi.jgenetic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GeneticSystem<T> {
    private final FitnessFunction<T> fitnessFunction;
    private final CrossingFunction<T> crossingFunction;
    private Map<T, Double> generation;
    
    public GeneticSystem(final Supplier<T> randomInitializer, final FitnessFunction<T> fitnessFunction,
            final CrossingFunction<T> crossingFunction, final int numberOfSpecimen) {
        this.fitnessFunction = fitnessFunction;
        this.crossingFunction = crossingFunction;
        // Initialize the first generation with random values
        this.generation = new HashMap<>(numberOfSpecimen);
        for (int i = 0; i < numberOfSpecimen; i++) {
            final T newSpecimen = randomInitializer.get();
            this.generation.put(newSpecimen, this.fitnessFunction.calculateFitness(newSpecimen));
        }
    }
    
    public Set<T> getGeneration() {
        return generation.keySet();
    }
    
    public void nextGeneration() {
        final List<T> betterHalf = generation.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .skip(generation.size() / 2)
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());
        
        final Map<T, Double> newGeneration = new HashMap<>(generation.size());
        
        for (int i = 0; i < generation.size(); i++) {
            final Random rand = new Random();
            final int index1 = rand.nextInt(betterHalf.size());
            int index2;
            while (index1 == (index2 = rand.nextInt(betterHalf.size()))) {
                // regenerate index2 until not equals index1
            }
            final T newSpecimen = this.crossingFunction.cross(betterHalf.get(index1), betterHalf.get(index2));
            newGeneration.put(newSpecimen, this.fitnessFunction.calculateFitness(newSpecimen));
        }
        this.generation = newGeneration;
    }
}
