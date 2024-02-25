package lab3;

import java.util.List;

public class TspSolution {
    // any required fields and methods
    private int dimension;
    List<Integer> solution;

    public Double fitness = null;

    public TspSolution(int dimension, List<Integer> solution) {
        this.dimension = dimension;
        this.solution = solution;
    }

    public int getDimension() {
        return dimension;
    }
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public List<Integer> getSolution() {
        return solution;
    }

    public String toString() {
        return String.format("best solution fit = %f", this.fitness);
    }
}
