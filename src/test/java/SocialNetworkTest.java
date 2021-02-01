import Exceptions.ChainNotFoundException;
import Exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkTest {
    private static SocialNetwork socialNetwork;
    private static List<User> users;

    @BeforeAll
    public static void setUp(){
        socialNetwork = new SocialNetwork();
        users = new ArrayList<>();

        for (int i=0;i<10;i++){
            User user = new User(i);
            try {
                socialNetwork.addUser(user);
                users.add(user);
            } catch (UserAlreadyExistsException e) {
                e.printStackTrace();
            }
        }

        socialNetwork.addFriendship(users.get(0), users.get(1));
        socialNetwork.addFriendship(users.get(1), users.get(2));
        socialNetwork.addFriendship(users.get(2), users.get(3));
        socialNetwork.addFriendship(users.get(0), users.get(4));
        socialNetwork.addFriendship(users.get(4), users.get(3));
        socialNetwork.addFriendship(users.get(3), users.get(5));

        socialNetwork.addFriendship(users.get(6), users.get(7));
        socialNetwork.addFriendship(users.get(7), users.get(8));
        socialNetwork.addFriendship(users.get(7), users.get(9));
    }


    @Test
    @DisplayName("Having a tree find the shortest chain")
    public void testShortestChainInATree() throws ChainNotFoundException {
        assertEquals(2, socialNetwork.getLengthOfShortestChain(users.get(6), users.get(8)));
    }

    @Test
    @DisplayName("Having a graph find the shortest chain")
    public void testShortestChainInAGraph() throws ChainNotFoundException {
        assertEquals(2, socialNetwork.getLengthOfShortestChain(users.get(0), users.get(3)),
                "Find shortest chain inside a cycle.");
        assertEquals(3, socialNetwork.getLengthOfShortestChain(users.get(1), users.get(5)),
                "Find shortest chain for a leaf.");
    }

    @Test
    @DisplayName("Find the shortest chain between a user and himself.")
    public void testShortestChainBetweenAUserAndHimself() throws ChainNotFoundException {
        assertEquals(0, socialNetwork.getLengthOfShortestChain(users.get(2), users.get(2)),
                    "Shortest chain between a user and himself should be 0.");

    }

    @Test
    @DisplayName("The shortest chain does not exist")
    public void testShortestChainDoesNotExists(){
        assertThrows(ChainNotFoundException.class,
                () -> socialNetwork.getLengthOfShortestChain(users.get(0), users.get(6)));
    }
}
