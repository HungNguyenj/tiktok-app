import * as commentServices from '../services/comment';
import { badRequest, internalServerError } from '../utils/handleResp';
class CommentController {
    async getCommentsByPostId(req, res) {
        try {
            const comments = await commentServices.getCommentsByPostId(
                req.params.postId,
                req.query
            );
            return res.status(200).json({
                err: 0,
                mes: '',
                comments,
            });
        } catch (error) {
            return internalServerError(res);
        }
    }

    async getReplyCommentsOfCommentPost(req, res) {
        try {
            const comments =
                await commentServices.getReplyCommentsOfCommentPost(
                    req.params.commentPostId,
                    req.query
                );
            return res.status(200).json({
                err: 0,
                mes: '',
                comments,
            });
        } catch (error) {
            return internalServerError(res);
        }
    }
    async insertCommentPost(req, res) {
        try {
            await commentServices.insertCommentPost({
                commenter: req.user.id,
                postId: req.params.postId,
                content: req.body.content,
            });
            return res.status(200).json({
                err: 0,
                mes: 'Commented',
            });
        } catch (error) {
            return internalServerError(res);
        }
    }
    async insertReplyComment(req, res) {
        try {
            await commentServices.insertCommentReply({
                responder: req.user.id,
                commentPostId: req.params.commentPostId,
                content: req.body.content,
            });
            return res.status(200).json({
                err: 0,
                mes: 'Replied comment',
            });
        } catch (error) {
            return internalServerError(res);
        }
    }
    async removeCommentPost(req, res) {
        try {
            const deleted = await commentServices.removeCommentPost(
                req.params.commentPostId
            );
            if (deleted)
                return res.status(200).json({
                    err: 0,
                    mes: 'Removed comment post',
                });
            else return badRequest('Not found comment', res);
        } catch (error) {
            return internalServerError(res);
        }
    }
    async removeReplyComment(req, res) {
        try {
            const deleted = await commentServices.removeCommentReply(
                req.params.replyCommentId
            );
            if (deleted)
                return res.status(200).json({
                    err: 0,
                    mes: 'Removed comment reply',
                });
            else return badRequest('Not found comment', res);
        } catch (error) {
            return internalServerError(res);
        }
    }
    async likeCommentPost(req, res) {
        try {
            const liked = await commentServices.reactComment(
                req.params.commentId
            );
            if (liked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Liked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            return internalServerError(res);
        }
    }
    async likeReplyComment(req, res) {
        try {
            const liked = await commentServices.reactComment(
                req.params.commentId,
                'reply'
            );
            if (liked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Liked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            return internalServerError(res);
        }
    }
    async unlikeCommentPost(req, res) {
        try {
            const unliked = await commentServices.reactComment(
                req.params.commentId,
                'post',
                'unlike'
            );
            if (unliked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Unliked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            return internalServerError(res);
        }
    }
    async unlikeReplyComment(req, res) {
        try {
            const unliked = await commentServices.reactComment(
                req.params.commentId,
                'reply',
                'unlike'
            );
            if (unliked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Unliked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            return internalServerError(res);
        }
    }
}
export default new CommentController();
