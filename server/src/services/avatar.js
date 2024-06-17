import { Op, where } from 'sequelize';
import db from '../models';
import { pagingConfig } from '../utils/pagination';
import * as userServices from './user';
export const updateAvatar = (oldPublicId, userId, { publicId, url, code }) =>
    new Promise(async (resolve, reject) => {
        try {
            const removeAvatarIdOfUser = await userServices.updateUser(
                {
                    avatarPublicId: null,
                },
                userId
            );
            if (!removeAvatarIdOfUser) resolve(null);
            let updateAvatar = null;
            if (oldPublicId == process.env.PUBLIC_ID_DEFAULT_AVATAR) {
                updateAvatar = await db.Avatar.create({ publicId, url, code });
            } else {
                updateAvatar = await db.Avatar.update(
                    { publicId, url, code },
                    {
                        where: { publicId: oldPublicId },
                    }
                );
            }
            if (!updateAvatar) resolve(null);
            let updateAvatarIdOfUser = null;
            if (updateAvatar) {
                updateAvatarIdOfUser = await userServices.updateUser(
                    {
                        avatarPublicId: publicId,
                    },
                    userId
                );
            }
            resolve(updateAvatarIdOfUser);
        } catch (error) {
            reject(error);
        }
    });
export const removeAvatar = (userId, publicId) =>
    new Promise(async (resolve, reject) => {
        try {
            const updateAvatarIdOfUser = await userServices.updateUser(
                {
                    avatarPublicId: process.env.PUBLIC_ID_DEFAULT_AVATAR,
                },
                userId
            );
            if (!updateAvatarIdOfUser) resolve(null);
            const resp = await db.Avatar.destroy({
                where: {
                    publicId,
                },
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
