import { Op, where } from 'sequelize';
import db from '../models';
import { pagingConfig } from '../utils/pagination';
import { query } from 'express';
export const getReplyCommentsOfCommentPost = (
    commentPostId,
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
                        attributes: ['id', 'userName', 'fullName', 'avatar'],
                        ...formatQueryUser,
                        as: 'responderData',
                        where: userQuery,
                    },
                    {
                        model: db.CommentPost,
                        attributes: ['id'],
                        as: 'commentPostData',
                        where: { id: commentPostId },
                    },
                ],
            };
            const { count, rows } = await db.CommentReply.findAndCountAll({
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
                commentsReply: rows,
                pagination: {
                    orderBy: queries.orderBy,
                    page: queries.offset + 1,
                    pageSize: queries.limit,
                    orderDirection: queries.orderDirection,
                    totalItems,
                    totalPages,
                },
            });
        } catch (error) {
            reject(error);
        }
    });
export const findCommentReplyById = (id) =>
    new Promise(async(resolve,reject)=> {
        try {
            const resp = await db.CommentReply.findByPk(id);
            resolve(resp)
        } catch (error) {
            reject(error)
        }
    })
/**
 * @typedef {Object} CommentReplyModel
 * @property {number} responder - The ID of the responder.
 * @property {number} commentPostId - The ID of the comment post.
 * @property {string} content - The content of the comment reply.
 */

/**
 * Insert reply comment
 * @param {CommentReplyModel} commentReplyModel - The comment reply object.
 */
export const insertCommentReply = (commentReplyModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.CommentReply.create(commentReplyModel);
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const removeCommentReply = (commentReplyId) =>
    new Promise(async (resolve, reject) => {
        try {
            const removeLikeCommentReply = await db.LikeCommnet.destroy({
                where : {
                    commentId : commentReplyId,
                    isCommentPost : false
                }
            })
            if (removeCommentReply) {
                const deleted = await db.CommentReply.destroy({
                    where: { id: commentReplyId },
                });
                resolve(deleted);
            }
            
        } catch (error) {
            reject(error);
        }
    });
export const removeCommentReplyByCommentPostId = (commentPostId) => 
    new Promise(async (resolve,reject)=> {
        const transaction = await sequelize.transaction();
    try {
        await db.CommentReply.destroy({
            where: { commentPostId },
            transaction
        });
        await db.LikeComment.destroy({
            where: {
                commentId: commentPostId,
                isCommentPost: 1
            },
            transaction
        });
        await db.LikeComment.destroy({
            where: {
                commentId: {
                    [Sequelize.Op.in]: sequelize.literal(`SELECT id FROM CommentReplies WHERE commentPostId = ${commentPostId}`)
                }
            },
            transaction
        });
        await db.CommentPost.destroy({
            where: { id: commentPostId },
            transaction
        });
        await transaction.commit();
        resolve(true)
    } catch (error) {
        await transaction.rollback();
        reject(error)
    }
    })


export const updateCommentReply = (content) =>
    new Promise(async (resolve, reject) => {
        try {
            const updated = await db.CommentReply.update({
                content 
            }, {
                where: { id: commentReplyModel.id },
            });
            resolve(updated);
        } catch (error) {
            reject(error);
        }
    });
