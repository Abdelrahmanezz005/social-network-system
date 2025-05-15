public class UserBST {
    UserNode root;

    public void insert(UserProfile user) {
        root = insertRec(root, user);
    }

    private UserNode insertRec(UserNode root, UserProfile user) {
        if (root == null) return new UserNode(user);
        if (user.userID < root.user.userID)
            root.left = insertRec(root.left, user);
        else
            root.right = insertRec(root.right, user);
        return root;
    }

    public UserProfile searchByID(int id) {
        return searchByIDRec(root, id);
    }

    private UserProfile searchByIDRec(UserNode root, int id) {
        if (root == null) return null;
        if (root.user.userID == id) return root.user;
        if (id < root.user.userID) return searchByIDRec(root.left, id);
        return searchByIDRec(root.right, id);
    }

    public UserProfile searchByUsername(String username) {
        return searchByUsernameRec(root, username);
    }

    private UserProfile searchByUsernameRec(UserNode root, String username) {
        if (root == null) return null;
        if (root.user.username.equals(username)) return root.user;
        UserProfile left = searchByUsernameRec(root.left, username);
        return (left != null) ? left : searchByUsernameRec(root.right, username);
    }

    public void inorderPrint() {
        inorderPrintRec(root);
    }

    private void inorderPrintRec(UserNode root) {
        if (root != null) {
            inorderPrintRec(root.left);
            System.out.println(root.user);
            inorderPrintRec(root.right);
        }
    }
}


