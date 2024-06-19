const express = require('express');
const passport = require('passport');
const router = express.Router();
import PostController from '../controller/PostController';
import Auth from '../middleware/auth';
const multer = require('multer');
const upload = multer({ dest: 'uploads/' });
router.get('/', Auth.setUser, PostController.getPosts);
router.get('/:postId', PostController.getPostById);
router.get('/user/:userId', PostController.getPosts);
router.post('/share/:postId', Auth.origin, PostController.sharePost);
router.post('/upload', upload.any(), Auth.origin, PostController.upload);
router.post('/like/:postId', Auth.origin, PostController.likePost);
router.post('/unlike/:postId', Auth.origin, PostController.unlikePost);
router.post('/remove', Auth.origin, PostController.removePost);
module.exports = router;