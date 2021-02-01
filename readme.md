<b>1. How did you represent the social network?  Why did you choose this representation?</b>
   
The social network is represented as a set of users. Each user has a list of his friends. I choose this representation because for searching the shortest path I need the list of friends for a specific user and in this representation is very easy to get this list.

<b> 2. What algorithm did you use to compute the shortest chain of friends?  What alternatives did you consider?  Why did you choose this algorithm over the alternatives? </b>
   
For computing the shortest chain of friends I used BFS algorithm because it is made to find the shortest path in time complexity O(n + m) where n is the number of nodes and m is the number of edges. If I used DFS, it finds a chain of friends, not necessarily the shortest one. Anyway I could extend DFS to a Backtracking algorithm to compute all the chains between two users and finally to choose the shortest one but this method has an exponential time complexity.

<b> 3. Please enumerate the test cases you considered and explain their relevance. </b>

I wrote 4 tests:

* Having a tree find the shortest chain: First test searches a chain of friends in a tree, there is only one path between any two nodes then it is enough to find it. It is an easy test, but it is useful because if it doesn't pass you know that you can't find any chain in a social network.
* Having a graph find the shortest chain: It is the general case when you have a graph and you want to find the shortest chain between two nodes. 
* Find the shortest chain between a user and himself: it is a particular case when the method is called with the same user as userA and userB. In that case the chain has length 0.
* The shortest chain does not exist: if two users doesn't have any chain between them then a ChainNotFoundException must be thrown.