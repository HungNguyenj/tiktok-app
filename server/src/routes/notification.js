const express = require('express');
const router = express.Router();
import Auth from '../middleware/auth';
import NotificationController from '../controller/NotificationController';
router.get('/:userId', Auth.isSeftUser, NotificationController.getNotifies);
router.post('/:userId', Auth.isAdmin, NotificationController.insertNotify);
router.delete(
    '/:notifyId',
    Auth.isSeftUser,
    NotificationController.removeNotify
);
module.exports = router;
