const express = require('express');
const router = express.Router();
import CommentController from '../controller/CommentController';
import Auth from '../middleware/auth';
router.get('/post/:postId', Auth.origin, CommentController.getCommentsByPostId);
router.get(
    '/:commentPostId/reply',
    Auth.origin,
    CommentController.getReplyCommentsOfCommentPost
);
router.post('/post/:postId', Auth.origin, CommentController.insertCommentPost);
router.post(
    '/:commentPostId/reply',
    Auth.origin,
    CommentController.insertReplyComment
);
router.delete(
    '/:commentPostId',
    Auth.origin,
    CommentController.removeCommentPost
);
router.delete(
    '/reply/:replyCommentId',
    Auth.origin,
    CommentController.removeReplyComment
);
router.post('/:commentId/like', Auth.origin, CommentController.likeCommentPost);
router.post(
    '/reply/:commentId/like',
    Auth.origin,
    CommentController.likeReplyComment
);
router.post(
    '/:commentId/unlike',
    Auth.origin,
    CommentController.unlikeCommentPost
);
router.post(
    '/reply/:commentId/unlike',
    Auth.origin,
    CommentController.unlikeCommentPost
);

module.exports = router;
