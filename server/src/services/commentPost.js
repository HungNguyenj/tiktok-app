import { Op, literal, where } from 'sequelize';
import db from '../models';
import { pagingConfig } from '../utils/pagination';
import { query } from 'express';
import { badRequest } from '../utils/handleResp';
import * as commentReplyServices from './commentReply';
import { formatQueryUser } from './user';

export const getCommentsByPostId = (
    postId,
    {
        page,
        pageSize,
        orderBy,
        orderDirection,
        userName,
        userId,
        fullName,
        content,
    }
    ,req
) =>
    new Promise(async (resolve, reject) => {
        try {
            const pagingQuery = pagingConfig(
                page,
                pageSize,
                orderBy,
                orderDirection
            );
            const userQuery = {};
            if (userName) userQuery.userName = { [Op.substring]: userName };
            if (fullName) userQuery.fullName = { [Op.substring]: fullName };
            if (userId) userQuery.id = userId;
            const commentQuery = {};
            if (content) commentQuery.content = { [Op.substring]: content };
            const commonQuery = {
                where: commentQuery,
                include: [
                    {
                        model: db.User,
                        attributes: ['id', 'userName', 'fullName'],
                        ...formatQueryUser,
                        as: 'commenterData',
                        where: userQuery,
                    },
                ],
                attributes : {
                    include : [
                        [
                            literal(`(
                                SELECT COUNT(*)
                                FROM likesComment lc
                                WHERE
                                  lc.commentId = commentPost.id
                                  AND lc.isCommentPost = true
                                )`),
                            'likes'
                        ]
                        
                    ]
                }
            };
            if (req.user) {
                commonQuery.attributes.include.push([
                    literal(`
                    (
                        SELECT EXISTS (
                            SELECT 1
                            FROM likesComment lc
                            JOIN users u ON lc.liker = u.id
                            WHERE
                                lc.commentId = CommentPost.id
                                AND lc.isCommentPost = true
                                AND u.id = ${req.user.id}
                        )
                    )`),
                    'isLiked'
                ])
            }
            
            const { count, rows } = await db.CommentPost.findAndCountAll({
                attributes: {
                    exclude: ['createdAt', 'updatedAt'],
                },
                ...commonQuery,
                ...pagingQuery,
            });
            const totalItems = count;
            const totalPages =
                totalItems / pageSize >= 1
                    ? Math.ceil(totalItems / pageSize)
                    : 1;
            resolve({
                commentsPost: rows,
                pagination: {
                    orderBy: pagingQuery.orderBy,
                    page: pagingQuery.offset + 1,
                    pageSize: pagingQuery.limit,
                    orderDirection: pagingQuery.orderDirection,
                    totalItems,
                    totalPages,
                },
            });
        } catch (error) {
            reject(error);
        }
    });

export const findCommentById = (id) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.CommentPost.findByPk(id);
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
/**
 * @typedef {Object} CommentPostModel
 * @property {number} commenter - The ID of the commenter.
 * @property {number} postId - The ID of the post.
 * @property {string} content - The content of the comment.
 */

/**
 * Insert comment post.
 * @param {CommentPostModel} commentPostModel - The comment object.
 */
export const insertCommentPost = (commentPostModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.CommentPost.create(commentPostModel);
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });

export const removeCommentPost = (commentPostId, commenter) =>
    new Promise(async (resolve, reject) => {
        try {
            const comment = await db.CommentPost.findOne({
                where: { id: commentPostId, commenter },
            });
            if (comment) {
                const removeRepliesOfComment = await db.CommentReply.destroy({
                    where: { commentPostId },
                });
                if (removeRepliesOfComment) {
                    await comment.destroy();
                    resolve(true);
                }
            }
            // Not found comment
            resolve(false);
        } catch (error) {
            reject(error);
        }
    });

export const updateCommentPost = (id,content) =>
    new Promise(async (resolve, reject) => {
        try {
            const updated = await db.CommentPost.update({content}, {
                where: { id },
            });
            resolve(updated);
        } catch (error) {
            reject(error);
        }
    });
