Detect Cycle :

Class CycleDetection {
        
    Set<Integer> processedSet = new HashSet<>();
    Set<Integer> processingSet;
    Map<Integer, List<Integer>> graph;

    Private boolean dfs(Integer node) {
        Stack<Integer> stack = new Stack();
        stack.push(node);
        processingSet.add(node);
        while(!stack.isEmpty()) {
            Integer curr = stack.pop();
            for(Integer neighbor: graph.get(curr)) {
                if(processingSet.contains(neighbor))
                    Return true;
                stack.push(neighbor);
            }
            processedSet.add(curr);
        }
    }
        
    Public Boolean detectCycle(Map<Integer, List<Integer>> graph) {
        This.graph = graph;
        
        for(Integer node: graph.KeySet()) {
            if(!processedSet.contains(node)) {
                processingSet = new HashSet<>()
                if(dfs(node) == true)
                    Return true;
            }
        }
        Return false;
    }
}
