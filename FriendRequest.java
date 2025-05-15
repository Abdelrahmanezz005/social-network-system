public class FriendRequest implements Comparable<FriendRequest> {
    static int counter = 1;
    int requestID;
    UserProfile sender;
    UserProfile receiver;
    String status;

    public FriendRequest(UserProfile sender, UserProfile receiver) {
        this.requestID = counter++;
        this.sender = sender;
        this.receiver = receiver;
        this.status = "pending";
    }

    public void acceptRequest() {
        status = "accepted";
        sender.addFriend(receiver);
        receiver.addFriend(sender);
    }

    public void declineRequest() {
        status = "declined";
    }

    @Override
    public int compareTo(FriendRequest o) {
        return this.requestID - o.requestID;
    }

    @Override
    public String toString() {
        return "Request from " + sender.username + " to " + receiver.username + " [" + status + "]";
    }
}
