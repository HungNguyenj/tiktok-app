const jwt = require('jsonwebtoken');

function handleSocket(io) {
    io.on('connection', (socket) => {
        console.log('A user connected');
        console.log(socket.id);
    });
}
module.exports = handleSocket;
