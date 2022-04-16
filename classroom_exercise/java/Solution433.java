import java.util.*;

public class Solution433 {

    public int minMutation(String start, String end, String[] bank) {
        depth.put(start,0);
        for (String seq: bank) hashBank.add(seq);
//        基因序列，目标基因序列及基因库   如果目标不在基因库中  返回-1
        if (!hashBank.contains(end)) return -1;
        Queue<String> q = new ArrayDeque<>();
        q.add(start); //队头队
        char[] gene = new char[]{'A','C','G','T'};

        while (!q.isEmpty()){  // 扩展队头
            String s = q.peek();   // 取队头
            char[] sc = s.toCharArray();
            q.poll();               // 队头出队
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println(sc[i]+","+gene[j]);
                    if (sc[i] != gene[j]){
                        char[] nc = sc.clone();
                        nc[i] = gene[j];
                        String ns = String.valueOf(nc);
                        if (!hashBank.contains(ns)) continue;
                        // 每个点只服务一次
                        if (depth.containsKey(ns)) continue;
                        depth.put(ns,depth.get(s) + 1);
                        q.add(ns);
                        if (ns.equals(end)){
                            System.out.println("----:"+depth.get(ns));
                            return depth.get(ns);
                        }
                    }
                }
            }

        }
        System.out.println("-------end----");
        return -1;
    }

    private HashMap<String, Integer> depth = new HashMap<>();
    private HashSet<String> hashBank = new HashSet<>();
}
