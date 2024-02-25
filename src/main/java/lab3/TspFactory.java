package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.*;

public class TspFactory extends AbstractCandidateFactory<TspSolution> {
    private int dimension;
    public TspFactory(int dimension) {
        this.dimension = dimension;
    }

    public TspSolution generateRandomCandidate(Random random) {
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            solution.add(i);
        }
        Collections.shuffle(solution, random);

        return new TspSolution(dimension, solution);
    }
}

