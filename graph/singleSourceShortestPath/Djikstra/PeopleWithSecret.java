/*
 * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.

 
 */


class Solution {
    public:
        vector<int> findAllPeople(int n, vector<vector<int>>& meetings, int firstPerson) { 
            // create the graph betwen the persons
            vector<vector<pair<int,int>>> graph(n);
            for(int i = 0; i < meetings.size(); ++i){
                graph[meetings[i][0]].push_back({meetings[i][1],meetings[i][2]});
                graph[meetings[i][1]].push_back({meetings[i][0],meetings[i][2]});
            }
    
            // using min heap , using time as the sort order key.   
            priority_queue<pair<int,int> , vector<pair<int,int> > , greater<pair<int,int>> > q;
            
            // Add both 0 and firstPerson in the queue as they know the secret initially at time 0 
            q.push({0, firstPerson});
            q.push({0, 0});
            //  vector to store res  
            vector<int> res;
            
            // marking the persons as visited if they have met and shared the secret to all possible persons they could 
            vector<bool> visited(n,false);
            
            while(!q.empty()){
                pair<int,int> curr = q.top();
                q.pop();
                
                int person = curr.second;
                int time = curr.first;
                
                // person had already shared the secret with others
                if(visited[person]) {
                    continue;
                }
                visited[person] = true;
                
                //iterate all the meetings of this person and add the potential new persons he can share secret with 
                for(pair<int,int> neigh : graph[person]){
                    if(!visited[neigh.first] && time <= neigh.second){
                        q.push({neigh.second,neigh.first});
                    }
                }
            }
            
            // all visited persons know the secret
            for(int i = 0; i < n ; ++i ){
                if(visited[i]){
                    res.push_back(i);        
                }
            }
            return res;
        }
    };