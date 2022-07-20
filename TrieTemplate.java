Class TrieNode {
    Boolean isEnd;
    HashMap<Character, TrieNode> trieNodeMap;
    List<String> top3Words;

    TrieNode(boolean isEnd) {
        this. isEnd = isEnd;
        this.trieNodeMap = new HashMap<>();
        this.top3Words = new ArrayList<>();
    }

    Public void add(String s) {
      TrieNode temp = root;
      for(int i =0; i<s.length();  i++) {
          if(!temp.trieNodeMap.containsKey(s.charAt(i))) {
              if(i==s.length()-1)
                temp.trieNodeMap.put(s.charAt(i), new TrieNode(true));
              Else
                 temp.trieNodeMap.put(s.charAt(i), new TrieNode(false));
           }
           Temp = temp.trieNodeMap.get(s.charAt(i));
      }

}
