import { Op, where } from 'sequelize';
import db from '../models';
export const insertRole = (code, value) =>
    new Promise((resolve, reject) => {
        try {
            const resp = db.Role.findOrCreate({
                where: { code, value },
                defaults: {
                    code,
                    value,
                },
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const removeRole = (roleModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.Role.findOne({
                where: roleModel,
            });
            if (resp) {
                await resp.destroy();
                resolve(resp);
            } else resolve(null);
        } catch (error) {
            reject(error);
        }
    });
