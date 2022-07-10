Djikstra’s

Class ShortestDistToAllNodeFromSource {

    
    Int[] shortestDist;
    
     U -> (v, w) => u - source , v - dest, w - weight.

    Public void Djikstra(Map<Integer, List<Pair<Integer, Integer>>> graph, int src) {

        Int n = graph.size();
        shortestDist = new int[n];
        for(int i=0; i<n; i++)
            shortestDist[i] = Integer.MAX_VALUE;
        shortestDist[src] = 0;         


        //Min heap for distance
        //priority queue of pair of distance and node
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue((a,b) -> a.getKey()-b.getKey());
        
        pq.add(new Pair(0, src));
        while(!pq.isEmpty()) {
            Pair<Integer, Integer> curr = pq.pop();
            Int dist = curr.getKey();
            Int node = curr.getValue();

            //Some value in PQ where dist is greater than already recorded shortest distance. Continue with loop. 
            if(dist>shortestDist[node])
                Continue;

            if(!graph.containsKey(node))
                Continue;

            for(Pair<Integer, Integer> n: graph.get(node)) {
                if(shortestDist[n]>time+n.getValue())    {
                    shortestDist[n] = time+n.getValue();
                    pq.add(new Pair(time+n.getValue(), n.getKey()));
                }
            }
        }
    }
}

TC: O(E Log V)
