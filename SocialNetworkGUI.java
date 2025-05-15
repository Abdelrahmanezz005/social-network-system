import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class SocialNetworkGUI extends JFrame {
    static UserBST usersTree = new UserBST();
    static PriorityQueue<FriendRequest> friendRequests = new PriorityQueue<>();
    static NotificationQueue notificationQueue = new NotificationQueue();
    static ReportGenerator reportGenerator = new ReportGenerator();
    static int userIdCounter = 1, postIdCounter = 1;

    public SocialNetworkGUI() {
        setTitle("🌐 Social Network System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to the Social Network System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton registerBtn = createButton("Register User");
        JButton sendReqBtn = createButton("Send Friend Request");
        JButton handleReqBtn = createButton("Handle Friend Requests");
        JButton postBtn = createButton("Create Post");
        JButton reportBtn = createButton("View Reports");
        JButton exitBtn = createButton("Exit");

        registerBtn.addActionListener(e -> registerUserForm());
        sendReqBtn.addActionListener(e -> sendRequestForm());
        handleReqBtn.addActionListener(e -> handleRequests());
        postBtn.addActionListener(e -> createPostForm());
        reportBtn.addActionListener(e -> viewReports());
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(registerBtn);
        buttonPanel.add(sendReqBtn);
        buttonPanel.add(handleReqBtn);
        buttonPanel.add(postBtn);
        buttonPanel.add(reportBtn);
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        button.setFocusPainted(false);
        return button;
    }

    void registerUserForm() {
        JTextField username = new JTextField();
        JTextField email = new JTextField();
        JTextField bio = new JTextField();
        Object[] fields = {
            "Username:", username,
            "Email:", email,
            "Bio:", bio
        };
        int option = JOptionPane.showConfirmDialog(this, fields, "Register User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            UserProfile user = new UserProfile(userIdCounter++, username.getText(), email.getText(), bio.getText());
            usersTree.insert(user);
            notificationQueue.addNotification("New user registered: " + username.getText());
            JOptionPane.showMessageDialog(this, "User Registered Successfully!");
        }
    }

    void sendRequestForm() {
        JTextField from = new JTextField();
        JTextField to = new JTextField();
        Object[] fields = {
            "Sender Username:", from,
            "Receiver Username:", to
        };
        int option = JOptionPane.showConfirmDialog(this, fields, "Send Friend Request", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            UserProfile sender = usersTree.searchByUsername(from.getText());
            UserProfile receiver = usersTree.searchByUsername(to.getText());
            if (sender != null && receiver != null) {
                friendRequests.offer(new FriendRequest(sender, receiver));
                notificationQueue.addNotification("Friend request sent from " + from.getText() + " to " + to.getText());
                JOptionPane.showMessageDialog(this, "Friend request sent.");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        }
    }

    void handleRequests() {
        while (!friendRequests.isEmpty()) {
            FriendRequest req = friendRequests.poll();
            int option = JOptionPane.showConfirmDialog(this, req.toString() + "\nAccept?", "Friend Request", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                req.acceptRequest();
                notificationQueue.addNotification("Request accepted by " + req.receiver.username);
            } else {
                req.declineRequest();
            }
        }
    }

    void createPostForm() {
        JTextField username = new JTextField();
        JTextField content = new JTextField();
        Object[] fields = {
            "Username:", username,
            "Content:", content
        };
        int option = JOptionPane.showConfirmDialog(this, fields, "Create Post", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            UserProfile user = usersTree.searchByUsername(username.getText());
            if (user != null) {
                Post post = new Post(postIdCounter++, content.getText());
                user.addPost(post);
                notificationQueue.addNotification(username.getText() + " created a new post.");
                JOptionPane.showMessageDialog(this, "Post created.");
            } else {
                JOptionPane.showMessageDialog(this, "User not found.");
            }
        }
    }

    void viewReports() {
        ArrayList<UserProfile> users = new ArrayList<>();
        collectUsers(usersTree.root, users);
        reportGenerator.generateUserReport(users);

        ArrayList<Post> allPosts = new ArrayList<>();
        for (UserProfile u : users) {
            allPosts.addAll(u.postHistory);
        }
        reportGenerator.generateTrendingPostsReport(allPosts);
        notificationQueue.sendNotifications();
    }

    void collectUsers(UserNode node, List<UserProfile> list) {
        if (node != null) {
            collectUsers(node.left, list);
            list.add(node.user);
            collectUsers(node.right, list);
        }
    }
    private void collectUsers(UserNode node, ArrayList<UserProfile> list) {
    if (node != null) {
        collectUsers(node.left, list);
        list.add(node.user);
        collectUsers(node.right, list);
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SocialNetworkGUI::new);
    }
}