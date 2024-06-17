import { createClient } from 'redis';
const dotenv = require('dotenv');
dotenv.config();
const client = createClient({
    password: process.env.REDIS_PASSWORD,
    socket: {
        host: process.env.REDIS_HOST,
        port: process.env.REDIS_PORT,
    },
});

client.on('error', (err) => {
    console.error('Redis Client Error', err);
});
client
    .connect()
    .then(() => {
        console.log('Connected to redis');
        return client.ping();
    })
    .then((pong) => {
        console.log('Redis ping response:', pong);
    })
    .catch((err) => {
        console.error('Error connecting to Redis:', err);
    });
export default client;
