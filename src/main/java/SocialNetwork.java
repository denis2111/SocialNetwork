import Exceptions.ChainNotFoundException;
import Exceptions.UserAlreadyExistsException;

import java.util.*;

public class SocialNetwork {
    /**
     * Set of users of social network. There can't be two users with the same ID.
     */
    private Set<User> users;

    /**
     * Construct an empty social network.
     */
    public SocialNetwork(){
        users = new HashSet<>();
    }

    /**
     * Add a user to the social network.
     */
    public void addUser(User user) throws UserAlreadyExistsException {
        if (users.contains(user))
            throw new UserAlreadyExistsException("a user with this ID already exists in this social network.");
        users.add(user);
    }

    /**
     * Remove a user from social network. It is also removed from all his friends lists.
     */
    public void removeUser(User user){
        for (User friend : user.getFriends()) {
            friend.removeFriend(user);
        }
        users.remove(user);
    }

    /**
     * @return the list of users.
     */
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Creates a friendship between two users.
     */
    public void addFriendship(User userA, User userB){
        userA.addFriend(userB);
        userB.addFriend(userA);
    }

    /**
     * Find and return the shortest chain between userA and userB. For that, a BFS algorithm is used. If we note with n
     * the number of users in the social network and with m the number of friendships then the total time complexity of
     * this method is O(n + m). The additional space complexity of this method is O(n).
     * @return the shortest chain between userA and userB.
     */
    public List<User> getShortestChain(User userA, User userB) throws ChainNotFoundException {
        // Store the previous user of a user in a chain.
        Map<User, User> previousUsers = new HashMap<>();
        // Keep a queue with users you have to visit in order to expand your searching.
        Queue<User> usersQueue = new LinkedList<>();

        usersQueue.add(userA);
        // userA is first user from a chain so it hasn't a previous user.
        previousUsers.put(userA, userA);

        // particular case when userA is the same with userB
        if (userA == userB)
            return rebuildChain(previousUsers, userB);

        while (!usersQueue.isEmpty()){
            User user = usersQueue.poll();

            for (User nextUser : user.getFriends()){
                // If a user has a previous user than it was already visited.
                if (previousUsers.containsKey(nextUser))
                    continue;
                usersQueue.add(nextUser);
                previousUsers.put(nextUser, user);
                if (nextUser == userB){
                    // nextUser is userB so we found a chain from userA to userB, we rebuild and return it.
                    return rebuildChain(previousUsers, userB);
                }
            }
        }

        // There is no chain between A and B a ChainNotFoundException is thrown.
        throw new ChainNotFoundException("A chain wasn't found.");
    }

    /**
     * Given the list of previous users for each visited user and a user, it will return the chain between first user
     * and given user. First user is marked as being it's own previous user.
     * @return The chain between first user and lastUser.
     */
    private List<User> rebuildChain(Map<User, User> previousUsers, User lastUser){
        List<User> friendsChain = new ArrayList<>();
        while (previousUsers.get(lastUser) != lastUser){
            friendsChain.add(lastUser);
            lastUser = previousUsers.get(lastUser);
        }
        friendsChain.add(lastUser);
        Collections.reverse(friendsChain);
        return friendsChain;
    }

    /**
     * Uses the method getShortestChain and return only the length of the shortest chain between userA and userB.
     * @return the length of the shortest chain.
     */
    public int getLengthOfShortestChain(User userA, User userB) throws ChainNotFoundException {
        return getShortestChain(userA, userB).size() - 1;
    }
}
