class Project {
    int profit;
    int capital;

    public Project(int profit, int capital) {
        this.profit = profit;
        this.capital = capital;
    }
}

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Project[] projects = new Project[n];

        for (int i = 0; i < n; i++) {
            projects[i] = new Project(profits[i], capital[i]);
        }

        Arrays.sort(projects, Comparator.comparingInt(a -> a.capital));

        int i = 0;

        PriorityQueue<Project> maximizeCapital = new PriorityQueue<>((a, b) -> Integer.compare(b.profit, a.profit));

        while (k-- > 0) {
            while (i < n && projects[i].capital <= w) {
                maximizeCapital.offer(projects[i]);
                i++;
            }
            if (maximizeCapital.isEmpty()) {
                break;
            }
            w += maximizeCapital.poll().profit;
        }

        return w;
    }
}