Class BellmanFord {

    Public int[] ShortestPathwithAtmostKEdges(Map<Integer, Pair<Integer, Integer>> graph, int src, int dest) {
        Int n = graph.size();
        int [] previous = new int[n];
        Int[] current = new int[n];
        
        for(int i=0; i<n; i++){
            Previous[i] = Integer.MAX_VALUE;
            Current[i] = Integer.MAX_VALUE;
        }

        Previous[src] = 0;
        List<int[]> EdgeList = getAllEdgeList(graph);
        
        for(int i=1; i<=K; i++){
            Current[src] = 0;
            for(int[] edge: EdgeList){
                Int s = edge[0];
                Int d = edge[1];
                Int w = edge[2];
                Current[d] = Math.min(Current[d], previous[s]+w);
            }
            Previous = current.clone();
        }
        return previous;
    } 
}
