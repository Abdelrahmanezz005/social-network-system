import java.util.ArrayList;

public class UserProfile {
    int userID;
    String username, email, bio;
    ArrayList<UserProfile> friendList = new ArrayList<>();
    ArrayList<Post> postHistory = new ArrayList<>();

    public UserProfile(int userID, String username, String email, String bio) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    public void updateProfile(String email, String bio) {
        this.email = email;
        this.bio = bio;
    }

    public void addFriend(UserProfile friend) {
        if (!friendList.contains(friend)) {
            friendList.add(friend);
        }
    }

    public void removeFriend(UserProfile friend) {
        friendList.remove(friend);
    }

    public void addPost(Post post) {
        postHistory.add(post);
    }

    public String getUserInfo() {
        return "ID: " + userID + ", Username: " + username + ", Email: " + email + ", Bio: " + bio;
    }

    @Override
    public String toString() {
        return getUserInfo();
    }
}
