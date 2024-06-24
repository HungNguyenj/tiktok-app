const express = require('express');
const router = express.Router();
import FollowerController from '../controller/FollowerController';
import Auth from '../middleware/auth';
router.get('/followers/:userId', FollowerController.getListFollower);
router.get('/followings/:userId', FollowerController.getListFollowing);
router.post('/:userId', Auth.origin, FollowerController.followUser);
router.delete('/:userId', Auth.origin, FollowerController.unfollowUser);

module.exports = router;
