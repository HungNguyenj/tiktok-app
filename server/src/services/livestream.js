import { Op, where } from 'sequelize';
import db from '../models';
import { pagingConfig } from '../utils/pagination';
export const getLivestreams = (
    livestreamId,
    { page, pageSize, orderBy, orderDirection, userId, title }
) =>
    new Promise(async (resolve, reject) => {
        try {
            const queries = pagingConfig(
                page,
                pageSize,
                orderBy,
                orderDirection
            );
            const query = {};
            if (livestreamId || title) query.where = {};
            if (livestreamId) query.where.id = livestreamId;
            if (title) query.where.title = { [Op.substring]: title };
            if (userId)
                query.include = [
                    {
                        model: db.User,
                        attributes: ['id', 'userName', 'fullName'],
                        as: 'streamerData',
                        where: { id: userId },
                        ...formatQueryUser,
                    },
                ];
            else
                query.include = [
                    {
                        model: db.User,
                        attributes: ['id', 'userName', 'fullName'],
                        as: 'streamerData',
                        ...formatQueryUser,
                    },
                ];
            const getLivestreamQuery = Object.assign(query, queries);
            const { count, rows } = await db.Livestream.findAndCountAll(
                getLivestreamQuery
            );
            const totalItems = count;
            const totalPages =
                totalItems / pageSize >= 1
                    ? Math.ceil(totalItems / pageSize)
                    : 1;
            resolve({
                livestreams: rows,
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

export const getOne = (id) =>
    new Promise((resolve, reject) => {
        try {
            const livestream = db.Livestream.findOne({
                where: { id },
                include: [
                    {
                        model: db.User,
                        attributes: ['id', 'userName', 'fullName'],
                        as: 'streamerData',
                        ...formatQueryUser,
                    },
                ],
            });
            resolve(livestream);
        } catch (error) {
            reject(error);
        }
    });
export const insertLivestream = ({ streamer, title, key }) =>
    new Promise((resolve, reject) => {
        try {
            const resp = db.Post.findOrCreate({
                where: { status: 1, key },
                defaults: {
                    streamer,
                    title,
                    key,
                },
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const updateLivestream = (id, livestreamModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.Livestream.update(livestreamModel, {
                where: {
                    id,
                },
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
