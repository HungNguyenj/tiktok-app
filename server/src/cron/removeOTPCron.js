import cron from 'node-cron';
import { Op } from 'sequelize';
import * as otpServices from '../services/otp';

const removeOTPCron = () => {
    cron.schedule('0 0 * * * *', async function () {
        console.log('Remove otp expired cron');
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        await otpServices.deleteOTP({
            createdAt: {
                [Op.lt]: today,
            },
        });
    });
};

export default removeOTPCron;
