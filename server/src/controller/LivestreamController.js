import * as livestreamServices from '../services/livestream';
import { badRequest } from '../utils/handleResp';
class LivestreamController {
    async getLivestreams() {
        try {
            if (req.params.userId) req.query.userId = req.params.userId;
            const livestreams = await livestreamServices.getLivestreams(
                req.params.postId,
                req.query
            );
            return res.status(200).json({
                err: 0,
                mes: '',
                ...livestreams,
            });
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async getLivestreamById(req, res) {
        try {
            const livestream = await livestreamServices.getOne(
                req.params.livestreamId
            );
            if (livestream)
                return res.status(200).json({
                    err: 0,
                    mes: '',
                    post: livestream,
                });
            else return badRequest('Not found post', res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async createLivestream(req, res) {
        try {
            const livestreamModel = {
                streamer: req.user.id,
                title: req.body.title,
                key: req.body.key,
            };
            const livestream = await livestreamServices.insertLivestream(
                livestreamModel
            );
            if (livestream[1])
                return res.status(200).json({
                    err: 0,
                    mes: 'Created livestream successfully',
                });
            else return badRequest('Something went wrong! Please try again');
        } catch (error) {
            return internalServerError(res);
        }
    }
}
export default new LivestreamController();
