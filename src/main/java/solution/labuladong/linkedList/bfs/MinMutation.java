package solution.labuladong.linkedList.bfs;

import java.util.*;

public class MinMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        if(!bankSet.contains(endGene)) return -1;
        char[] genes = new char[]{'A', 'C', 'G', 'T'};

        Deque<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(startGene);
        visited.add(startGene);
        int step = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                String cur = q.poll();
                if(cur.equals(endGene)) return step;
                // 遍历所有可能的基因变化
                for(int j=0; j<cur.length(); j++){
                    for(char gene : genes){
                        char[] geneChars = cur.toCharArray();
                        geneChars[j] = gene;
                        String next = new String(geneChars);
                        if(bankSet.contains(next) && !visited.contains(next)){
                            q.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        MinMutation minMutation = new MinMutation();
        String startGene = "AAAAACCC";
        String endGene = "AACCCCCC";
        String[] bank = {"AAAACCCC","AAACCCCC","AACCCCCC"};
        System.out.println(minMutation.minMutation(startGene, endGene, bank));
    }
}
