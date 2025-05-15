import java.util.ArrayList;
import java.util.Date;

public class Post {
    int postID;
    String content;
    Date timestamp;
    int likes = 0;
    ArrayList<String> comments = new ArrayList<>();

    public Post(int postID, String content) {
        this.postID = postID;
        this.content = content;
        this.timestamp = new Date();
    }

    public void like() {
        likes++;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Post ID: " + postID + ", Content: " + content + ", Likes: " + likes + ", Comments: " + comments;
    }
}
