import { content } from 'googleapis/build/src/apis/content';
import * as commentPostServices from '../services/commentPost';
import * as commentReplyServices from '../services/commentReply';
import * as commentServices from '../services/comment';
import * as postServices from '../services/post';

import { badRequest, internalServerError } from '../utils/handleResp';
class CommentController {
    async getCommentsByPostId(req, res) {
        try {
            const comments = await commentPostServices.getCommentsByPostId(
                req.params.postId,
                req.query
            );
            return res.status(200).json({
                err: 0,
                mes: '',
                comments,
            });
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }

    async getReplyCommentsOfCommentPost(req, res) {
        try {
            const comments =
                await commentReplyServices.getReplyCommentsOfCommentPost(
                    req.params.commentPostId,
                    req.query
                );
            return res.status(200).json({
                err: 0,
                mes: '',
                comments,
            });
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async insertCommentPost(req, res) {
        try {
            const postId = req.params.postId
            const post = await postServices.getOne(postId);
            if (!post) return badRequest("Not found post",res)
            const comment = await commentPostServices.insertCommentPost({
                commenter: req.user.id,
                postId,
                content: req.body.content,
            });
            if (comment)
                return res.status(200).json({
                    err: 0,
                    mes: 'Commented',
                });
            else 
                return badRequest("Some error occured",res)
            
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async insertReplyComment(req, res) {
        try {
            const commentPostId = req.params.commentPostId
            const commentPost = await commentPostServices.findCommentById(commentPostId)
            if (!commentPost) return badRequest("Not found comment to reply",res)
            const commentReply = await commentReplyServices.insertCommentReply({
                responder: req.user.id,
                commentPostId,
                content: req.body.content,
            });
            if (commentReply)
                return res.status(200).json({
                    err: 0,
                    mes: 'Replied comment',
                });
            else 
                return badRequest("Some error occured",res)
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async removeCommentPost(req, res) {
        try {
            const deleted = await commentPostServices.removeCommentPost(
                req.params.commentPostId
            );
            if (deleted)
                return res.status(200).json({
                    err: 0,
                    mes: 'Removed comment post',
                });
            else return badRequest('Not found comment', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async removeReplyComment(req, res) {
        try {
            const deleted = await commentReplyServices.removeCommentReply(
                req.params.replyCommentId
            );
            if (deleted)
                return res.status(200).json({
                    err: 0,
                    mes: 'Removed comment reply',
                });
            else return badRequest('Not found comment', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    /**
    * @typedef {Object} LikeCommentModel
    * @property {number} liker - The ID of the liker.
    * @property {number} commentId - The ID of the comment.
    * @property {boolean} isCommentPost - Is comment of post or not (comment reply).
    */
    async likeCommentPost(req, res) {
        try {
            /**
            * @type {LikeCommentModel}
            */
            const likeCommentModel = {
                liker : req.user.id,
                commentId : req.params.commentId,
                isCommentPost : true
            }
            const liked = await commentServices.reactComment(
                likeCommentModel,
                'like'
            );
            if (liked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Liked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async likeReplyComment(req, res) {
        try {
            /**
            * @type {LikeCommentModel}
            */
             const likeCommentModel = {
                liker : req.user.id,
                commentId : req.params.commentId,
                isCommentPost : false
            }
            const liked = await commentServices.reactComment(
                likeCommentModel,
                'like'
            );
            if (liked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Liked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async unlikeCommentPost(req, res) {
        try {
            
             /**
            * @type {LikeCommentModel}
            */
             const likeCommentModel = {
                liker : req.user.id,
                commentId : req.params.commentId,
                isCommentPost : true
            }
            const unliked = await commentServices.reactComment(
                likeCommentModel,
                'unlike'
            );
            if (unliked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Unliked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }

    async unlikeReplyComment(req, res) {
        try {
            /**
            * @type {LikeCommentModel}
            */
            const likeCommentModel = {
                liker : req.user.id,
                commentId : req.params.commentId,
                isCommentPost : false
            }
            const unliked = await commentServices.reactComment(
                likeCommentModel,
                'unlike'
            );
            if (unliked)
                return res.status(200).json({
                    err: 0,
                    mes: 'Unliked comment',
                });
            else badRequest('Not found comment', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
}
export default new CommentController();
