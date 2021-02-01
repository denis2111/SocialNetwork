import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private List<User> friends;

    public User(int id){
        this.id = id;
        friends = new ArrayList<>();
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public void removeFriend(User user){
        friends.remove(user);
    }

    public List<User> getFriends(){
        return friends;
    }

    /**
     * Check equality based only on id.
     * @return true if this object is equal with object o.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    /**
     * @return hash code of this object based only on id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
