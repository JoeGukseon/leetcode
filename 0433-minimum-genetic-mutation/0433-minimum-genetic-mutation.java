class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> geneBank = new HashSet<>(Arrays.asList(bank));

        if (!geneBank.contains(endGene)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);

        int mutations = 0;

        //BFS 탐색
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentGene = queue.poll();

                for (int j = 0; j < currentGene.length(); j++) {
                    char[] geneArray = currentGene.toCharArray();
                    
                    //하나씩 변이
                    for (char c : new char[]{'A', 'C', 'G', 'T'}) {
                        geneArray[j] = c;
                        String mutatedGene = new String(geneArray);

                        //같으면 +1 리턴
                        if (mutatedGene.equals(endGene)) {
                            return mutations + 1;
                        }

                        //bank에 있으면 bank값을 지우고 큐에 넣기
                        if (geneBank.contains(mutatedGene)) {
                            geneBank.remove(mutatedGene);
                            queue.offer(mutatedGene);
                        }
                    }
                }
            }

            mutations++;
        }
        return -1;
    }

}