import { Op, where } from 'sequelize';
import db from '../models';
import { pagingConfig } from '../utils/pagination';
export const formatQueryUserWithAtrr = {
    attributes: {
        exclude: ['roleCode', 'avatarPublicId'],
    },
    include: [
        {
            model: db.Avatar,
            as: 'avatarData',
            attributes: { exclude: ['createdAt', 'updatedAt'] },
        },
        {
            model: db.Role,
            as: 'roleData',
            attributes: ['id', 'code', 'value'],
        },
    ],
};
export const formatQueryUser = {
    include: [
        {
            model: db.Avatar,
            as: 'avatarData',
            attributes: { exclude: ['createdAt', 'updatedAt'] },
        },
    ],
};
export const findUsers = ({
    page,
    pageSize,
    orderBy,
    orderDirection,
    userName,
    fullName,
}) =>
    new Promise(async (resolve, reject) => {
        try {
            const queries = pagingConfig(
                page,
                pageSize,
                orderBy,
                orderDirection
            );
            const query = {};
            if (userName) query.userName = { [Op.substring]: userName };
            if (fullName) query.fullName = { [Op.substring]: fullName };
            const { count, rows } = await db.User.findAndCountAll({
                where: query,
                attributes: ['id', 'userName', 'fullName'],
                ...formatQueryUser,
                ...queries,
            });
            const totalItems = count;
            const totalPages =
                totalItems / pageSize >= 1
                    ? Math.ceil(totalItems / pageSize)
                    : 1;
            resolve({
                users: rows,
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
export const updateUser = (newDataUser, id) =>
    new Promise((resolve, reject) => {
        try {
            const resp = db.User.update(newDataUser, {
                where: { id },
                raw: true,
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const findOne = (user) =>
    new Promise((resolve, reject) => {
        try {
            const resp = db.User.findOne({
                where: user,
                attributes: {
                    exclude: ['roleCode'],
                },
                ...formatQueryUserWithAtrr,
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
