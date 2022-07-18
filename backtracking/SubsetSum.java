class Solution {
    
    Set<List<Integer>> res;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res = new HashSet<>();
        Arrays.sort(nums);
        dfs(-1,new ArrayList<>(), nums);
        List<List<Integer>> ans= new ArrayList<>(res);
        return ans;
    }
    
    private void dfs(int index, ArrayList<Integer> set, int[] nums) {
        if(index>=nums.length) {
            return;
        }
        res.add(new ArrayList<>(set));
        for(int i = index+1; i< nums.length; i++) {
            set.add(nums[i]);
            dfs(i, set, nums);
            set.remove(set.size()-1);
        }
    }
}