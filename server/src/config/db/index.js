const dotenv = require('dotenv');
dotenv.config();
const { Sequelize } = require('sequelize');
const sequelize = new Sequelize(
    process.env.DB_NAME,
    process.env.DB_USERNAME,
    null,
    {
        host: 'localhost',
        dialect: 'mysql',
        logging: false,
    }
);
async function getConnection() {
    try {
        await sequelize.authenticate();
        console.log('Connection has been established successfully.');
    } catch (error) {
        console.error('Unable to connect to the database:', error);
    }
}
module.exports = { getConnection };
