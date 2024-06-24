import { where } from 'sequelize';
import db from '../models';

export const isLikedPost = ({ liker, postId }) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.LikePost.findOne({
                where: {
                    liker,
                    postId,
                },
            });
            if (resp) resolve(true);
            else resolve(false);
        } catch (error) {
            reject(error);
        }
    });
export const likePost = ({ liker, postId }) =>
    new Promise(async (resolve, reject) => {
        try {
            const isLiked = await isLikedPost({ liker, postId });
            if (!isLiked) {
                const resp = await db.LikePost.create({
                    liker,
                    postId,
                });
                resolve(resp);
            } else {
                resolve(false);
            }
        } catch (error) {
            reject(error);
        }
    });
export const unlikePost = ({ liker, postId }) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.LikePost.destroy({
                where: {
                    liker,
                    postId,
                },
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const countLikesForPost = (postId) =>
    new Promise(async (resolve, reject) => {
        try {
            const count = await db.LikePost.count({
                where: {
                    postId,
                },
            });
            resolve(count);
        } catch (error) {
            reject(error);
        }
    });
