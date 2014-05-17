package networks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Vertex implements Comparable<Vertex> {

    public final String name;
    public ArrayList<Edge> adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(String argName) {
        name = argName;
        adjacencies = new ArrayList<>();
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge {

    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

public class Dijkstra {

    public static void computePaths(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    // make sure n is more than 1
    private static int validateN(Scanner sc) {
        System.out.println("Input the number of routers in the network(n):");
        int n = sc.nextInt();
        if (n > 1) {
            return n;
        } else {
            System.out.println("n needs to be more or equal to 2");
            validateN(sc);
        }
        return 0;
    }

    private static ArrayList<int[]> validateFile(Scanner sc, int n) {

        ArrayList<int[]> graph = new ArrayList();
        BufferedReader fr = null;
        System.out.println("Enter filename:");
        String filename = sc.next();
        try {
            fr = new BufferedReader(new FileReader(filename));
            String line;
            int index = 0;
            while ((line = fr.readLine()) != null) {
                if (line.isEmpty()) {
                    break;
                }
                int temp[] = {Integer.parseInt(line.split("\\s+")[0]),
                    Integer.parseInt(line.split("\\s+")[1]),
                    Integer.parseInt(line.split("\\s+")[2])};
                System.out.println("n:" + n + "| " + temp[0] + " " + temp[1] + " " + temp[2]);

                //check if router indexes are between 1 and n
                //and graphs are positive.
                if ((temp[0] < 1) || (temp[0] > n)
                        || (temp[1] < 1) || (temp[1] > n)
                        || (temp[2] < 1)) {
                    System.out.println("invalid number detected in line " + index);
                    validateFile(sc, n);
                }
                index++;
                temp[0] = temp[0] - 1;// making it zero based
                temp[1] = temp[1] - 1;
                graph.add(temp);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex + " check the filename");
            System.exit(0);
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(0);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return graph;
    }

    //initvertices
    public static Vertex[] initVertices(ArrayList<int[]> graph, int n) {
        Vertex[] vertices = new Vertex[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = new Vertex("v" + i);
        }
        for (int i = 0; i < n; i++) {
            for (int[] graph1 : graph) {
                if (graph1[0] == i) {
                    vertices[i].adjacencies.add(new Edge(vertices[graph1[1]], graph1[2]));
                }
            }
        }

        return vertices;
    }

    //get shortest path from source v0 to target	
    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * display forwarding table
     *
     * @param destination
     * @param link they should both have the same length.
     */
    public static void displayFTable(String[] destination, String[][] link) {
        System.out.println("\n  forwading table\n" + "destination |  link");
        System.out.println("----------------------");
        for (int i = 0; i < destination.length; i++) {
            System.out.printf("\t " + destination[i] + " | "
                    + "(" + link[i][0] + "," + link[i][1] + ")\n");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = validateN(sc);

        Vertex[] vertices = initVertices(validateFile(sc, n), n);
        computePaths(vertices[0]);
        String[][] link = new String[n][2];
        String[] destination = new String[n];
        int index = 0;
        for (Vertex v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            List<Vertex> path = getShortestPathTo(v);
            System.out.println("Path: " + path);

            //get data for forwarding table
            link[index][0] = "v0";
            destination[index] = vertices[index].name;
            if (path.size() > 1) {
                link[index][1] = path.get(1).name;
            } else {
                link[index][1] = "v0";
            }
            index++;
        }

        displayFTable(destination, link);
    }
}
