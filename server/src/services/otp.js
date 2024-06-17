import { Op } from 'sequelize';
import db from '../models';
export const createOTP = (email, otp) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.Otp.findOrCreate({
                where: { email },
                defaults: {
                    email,
                    otp,
                },
            });
            if (!resp[1]) {
                await resp[0].update({
                    otp,
                });
            }
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
export const deleteOTP = async (otpModel) =>
    new Promise(async (resolve, reject) => {
        try {
            const resp = await db.Otp.destroy({
                where: otpModel,
            });
            resolve(resp);
        } catch (error) {
            reject(error);
        }
    });
