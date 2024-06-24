public class CommentActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private int postId;
    private List<Comment> commentList;
    private LinearLayout commentListContainer;
    private EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        postId = sharedPreferences.getInt("currentPostId", 0); // Retrieve postId from SharedPreferences

        commentList = new ArrayList<>();
        commentListContainer = findViewById(R.id.commentListContainer);
        editTextComment = findViewById(R.id.editTextComment);
        ImageButton sendComment = findViewById(R.id.sendComment);
        ImageButton exitComment = findViewById(R.id.exitComment);

        exitComment.setOnClickListener(v -> finish()); // Close the comment activity

        sendComment.setOnClickListener(v -> {
            String newCommentText = editTextComment.getText().toString().trim();
            if (!newCommentText.isEmpty()) {
                // Simulate adding comment locally (replace with API call in real scenario)
                Comment newComment = new Comment(1, postId, newCommentText); // Assuming commenterId is 1
                addComment(newComment);
            }
        });

        // Example function to populate comments (replace with your API call)
        fetchComments(postId);
    }

    private void fetchComments(int postId) {
        // Simulated API call to fetch comments for a specific postId
        // Replace with your Retrofit call or other API implementation
        // Example data
        commentList.add(new Comment(1, postId, "This is a comment", 0, false, null, "2 hours ago"));
        commentList.add(new Comment(2, postId, "Another comment", 0, false, null, "1 hour ago"));

        // Render comments
        renderComments();
    }

    private void renderComments() {
        commentListContainer.removeAllViews(); // Clear existing views

        for (Comment comment : commentList) {
            View commentView = getLayoutInflater().inflate(R.layout.comment_item, null);
            TextView commenterUsername = commentView.findViewById(R.id.commenterUsername);
            TextView commentContent = commentView.findViewById(R.id.commentContent);
            TextView commentTimestamp = commentView.findViewById(R.id.commentTimestamp);

            commenterUsername.setText("User " + comment.getCommenterId());
            commentContent.setText(comment.getContent());
            commentTimestamp.setText(comment.getTimestamp());

            commentListContainer.addView(commentView);
        }

        // Update comment count text
        TextView amountComment = findViewById(R.id.amountComment);
        amountComment.setText(commentList.size() + " bình luận");
    }

    private void addComment(Comment comment) {
        // Add comment to list (simulate adding in real scenario)
        commentList.add(comment);
        // Update UI
        renderComments();
        // Clear input
        editTextComment.setText("");
    }
}
