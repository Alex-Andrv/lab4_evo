package lab3;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<Node> nodes;
    public int dimension;
    public String name;

    public Problem(String problem) throws Exception {
        String fileName = String.format("%s.tsp" , problem);;
        ClassLoader classLoader = TspFitnessFunction.class.getClassLoader();
        try (Scanner scanner = new Scanner(classLoader.getResourceAsStream(fileName))) {
            boolean parse_task = false;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                if  (!parse_task) {
                    switch (words[0]) {
                        case ("DIMENSION"):
                            this.dimension = Integer.parseInt(words[2]);
                            break;
                        case ("NAME"):
                            this.name = words[2];
                            break;
                        case ("NODE_COORD_SECTION"):
                            parse_task = true;
                            this.nodes = new ArrayList<>();
                            break;
                        default:
                            break;
                    }
                } else {
                    if (Objects.equals(words[0], "EOF")) {
                        break;
                    }
                    List<Integer> node_info = Arrays.stream(words).map(Integer::parseInt).collect(Collectors.toList());
                    int idx = node_info.get(0);
                    int x = node_info.get(1), y = node_info.get(2);
                    if (idx > this.dimension) {
                        throw new Exception("idx is bigger than dimension");
                    }
                    nodes.add(new Node(x, y));
                }
            }
        }
    }
}
