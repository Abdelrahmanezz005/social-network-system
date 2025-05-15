import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class ReportGenerator {
    public void generateUserReport(List<UserProfile> users) {
        users.sort(Comparator.comparing(u -> u.username));

        JTextArea textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        StringBuilder report = new StringBuilder("User Report:\n\n");

        for (UserProfile user : users) {
            report.append(user.getUserInfo()).append("\n");
        }

        textArea.setText(report.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "User Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public void generateTrendingPostsReport(List<Post> posts) {
        posts.sort((a, b) -> b.likes - a.likes);

        JTextArea textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        StringBuilder report = new StringBuilder("Trending Posts:\n\n");

        for (Post post : posts) {
            report.append(post.toString()).append("\n\n");
        }

        textArea.setText(report.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, "Trending Posts", JOptionPane.INFORMATION_MESSAGE);
    }
}