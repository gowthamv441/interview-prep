Class TopoSort {
    
    Public List<Integer> topoSort(Map<Integer, List<Integer>> graph, int numOfVertices)     
    {
        Int indegree = new int[numOfVertices];
        for(Integer vertex: graph.keySet()) {
            for(Integer node: graph.get(vertex)) {
                indegree[node]++;
            }
        }
        
        Queue<Integer> queue = new Queue<>();
        List<Integer> result = new ArrayList<>();
        for(int i =0; i< numOfVertices; i++) {
            if(indegree[i]==0) {
                queue.add(i);
            }
        }
        while(!queue.isEmpty()) {
            Int node = queue.pop();
            result.add(node);
            for(Integer neighbor: graph.get(node)) {
                Indegree[neighbor]--;
                if(Indegree[neighbor] == 0)
                    queue.push(neighbor);
            }
        }
        Return result;
    } 
}
