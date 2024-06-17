import { Op } from 'sequelize';
import db from '../models';
import { pagingConfig } from '../utils/pagination';
import { query } from 'express';
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
                        as: 'commenterData',
                        where: userQuery,
                    },
                    {
                        model: db.Post,
                        attributes: ['id'],
                        as: 'postData',
                        where: { id: postId },
                    },
                ],
            };
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

export const insertCommentPost = (commentPostModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.CommentPost.create(commentPostModel);
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const insertCommentReply = (commentReplyModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.CommentReply.create(commentReplyModel);
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const removeCommentPost = (commentPostId) =>
    new Promise(async (resolve, reject) => {
        try {
            await db.CommentReply.destroy({
                where: { commentPostId },
            });
            const deleted = await db.CommentPost.destroy({
                where: { id: commentPostId },
            });
            resolve(deleted);
        } catch (error) {
            reject(error);
        }
    });
export const removeCommentReply = (commentReplyId) =>
    new Promise(async (resolve, reject) => {
        try {
            const deleted = await db.CommentReply.destroy({
                where: { id: commentReplyId },
            });
            resolve(deleted);
        } catch (error) {
            reject(error);
        }
    });
export const updateCommentPost = (commentPostModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const updated = await db.CommentPost.update(commentPostModel, {
                where: { id: commentPostModel.id },
            });
            resolve(updated);
        } catch (error) {
            reject(error);
        }
    });
export const updateCommentReply = (commentReplyModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const updated = await db.CommentReply.update(commentReplyModel, {
                where: { id: commentReplyModel.id },
            });
            resolve(updated);
        } catch (error) {
            reject(error);
        }
    });
export const reactComment = (
    commentId,
    typeComment = 'post',
    typeAction = 'like'
) =>
    new Promise(async (resolve, reject) => {
        try {
            let liked = null;
            const action = typeAction === 'like' ? 'likes + 1' : 'likes - 1';
            if (typeComment == 'post') {
                liked = await db.CommentPost.update(
                    { like: Sequelize.literal(action) },
                    {
                        where: {
                            id: commentId,
                        },
                    }
                );
            } else {
                liked = await db.CommentReply.update(
                    { like: Sequelize.literal(action) },
                    {
                        where: {
                            id: commentId,
                        },
                    }
                );
            }
            resolve(liked);
        } catch (error) {
            reject(error);
        }
    });
