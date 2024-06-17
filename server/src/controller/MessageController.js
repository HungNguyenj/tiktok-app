import { badRequest, internalServerError } from '../utils/handleResp';
import * as messageServices from '../services/message';
import { v4 as uuidv4 } from 'uuid';
class MessageController {
    async getMessagesOfChatroom(req, res) {
        try {
            const { chatroomId } = req.params;
            const resp = await messageServices.getListMessageOfChatroom(
                chatroomId,
                req.query
            );
            return res.status(200).json({
                err: 0,
                mes: '',
                ...resp,
            });
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async uploadImage(req, res) {
        try {
            const { files } = req;
            for (const file of files) {
                if (file.size / (1024 * 1024) > 30)
                    return badRequest(
                        'Cannot upload file with size more than 30mb',
                        res
                    );
            }
            const images = files.filter((file) => file.fieldname == 'images');
            if (!images.mimetype.includes('image'))
                return badRequest('Field images must be image type', res);
            const imagesLink = [];
            for (const image of images) {
                const uuid = uuidv4();
                const partsImageName = image.originalname.split('.');
                const imageName =
                    partsImageName[0] +
                    uuid +
                    partsImageName[partsImageName.length - 1];
                const imageUploaded = await UploadFile.uploadToGGDriver(
                    image,
                    imageName,
                    process.env.GG_DRIVE_FOLDER_THUMNAIL_ID
                );
                imagesLink.push({
                    id: imageUploaded.id,
                    url: imageUploaded.url,
                });
            }
            return res.status(200).json({
                err: 0,
                mes: 'Upload successfully',
                images: imagesLink,
            });
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async sendMessage(req, res) {
        try {
            const { chatroomId } = req.params;
            const { content } = req.body;
            const resp = await messageServices.sendMessage(
                req.user.id,
                chatroomId,
                content
            );
            return res.status(200).json({
                err: 0,
                mes: 'Sent message to chatroom ' + chatroomId,
            });
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
    async recallMessage(req, res) {
        try {
            const { messageId, userId } = req.params;
            const resp = await messageServices.recallMessage(messageId, userId);
            if (resp)
                return res.status(200).json({
                    err: 0,
                    mes: 'Recalled message',
                });
            else return badRequest('Not found message ' + messageId, res);
        } catch (error) {
            console.log(error);
            return internalServerError(res);
        }
    }
}
export default new MessageController();
