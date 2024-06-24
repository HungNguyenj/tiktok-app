const ffmpeg = require('fluent-ffmpeg');
const ffmpegPath = require('@ffmpeg-installer/ffmpeg').path;
const ffprobePath = require('@ffprobe-installer/ffprobe').path;
ffmpeg.setFfmpegPath(ffmpegPath);
ffmpeg.setFfprobePath(ffprobePath);
const fs = require('fs');
const path = require('path');
class FfmpegUtil {
    async captureVideo(videoPath, res) {
        return new Promise(async (resolve, reject) => {
            const outputPath = path.join(
                __dirname,
                '..',
                'output',
                `frame.jpg`
            );
            if (!fs.existsSync(path.dirname(outputPath))) {
                fs.mkdirSync(path.dirname(outputPath), { recursive: true });
            }
            await ffmpeg(videoPath)
                .on('end', () => {
                    console.log('Captured image from video');
                    fs.readFile(outputPath, (err, data) => {
                        if (err) {
                            console.log('Error reading the image file:', err);
                            return res
                                .status(500)
                                .send('Error reading the image file');
                        }
                        fs.unlink(outputPath, (err) => {
                            if (err) {
                                console.log(
                                    'Error deleting the output image file:',
                                    err
                                );
                                return res
                                    .status(500)
                                    .send('Error reading the image file');
                            }
                        });
                        const bufferImage = data;
                        resolve(bufferImage);
                    });
                })
                .on('error', (err) => {
                    console.log('Error processing video:', err);
                    return res.status(500).send('Error processing video');
                })
                .screenshots({
                    count: 1,
                    folder: path.dirname(outputPath),
                    filename: path.basename(outputPath),
                });
        });
    }
}
export default new FfmpegUtil();
