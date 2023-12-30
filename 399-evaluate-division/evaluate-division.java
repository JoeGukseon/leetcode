class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String num = equations.get(i).get(0);
            String den = equations.get(i).get(1);
            double val = values[i];

            graph.putIfAbsent(num, new HashMap<>());
            graph.putIfAbsent(den, new HashMap<>());

            graph.get(num).put(den, val);
            graph.get(den).put(num, 1 / val);
        }

        double[] results = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            results[i] = dfs(start, end, new HashSet<>(), graph);
        }

        return results;
    }

    private double dfs(String start, String end, Set<String> visited, Map<String, Map<String, Double>> graph) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }

        if (start.equals(end)) {
            return 1.0;
        }

        visited.add(start);

        for (Map.Entry<String, Double> neighbor : graph.get(start).entrySet()) {
            String next = neighbor.getKey();
            double weight = neighbor.getValue();

            if (!visited.contains(next)) {
                double result = dfs(next, end, visited, graph);
                if (result != -1.0) {
                    return weight * result;
                }
            }
        }

        visited.remove(start);

        return -1.0;
    }
}