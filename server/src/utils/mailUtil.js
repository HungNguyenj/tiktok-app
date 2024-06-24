import nodemailer from 'nodemailer';
export const sendMail = (mailTemplate, title, content, email) =>
    new Promise((resolve, reject) => {
        const transporter = nodemailer.createTransport({
            host: 'smtp.gmail.com',
            port: 465,
            secure: true,
            auth: {
                user: process.env.EMAIL_ADDRESS,
                pass: process.env.EMAIL_APP_PASSWORD,
            },
        });
        const mailOptions = {
            from: process.env.EMAIL_ADDRESS,
            to: email,
            subject: title,
            html: mailTemplate(title, content),
        };
        transporter.sendMail(mailOptions, function (error, info) {
            if (error) {
                console.log(error);
                reject({ err: 1, mes: 'Internal Server Error' });
            } else {
                console.log('Send mail');
                resolve({
                    err: 0,
                    mes: 'Email sent successfully',
                });
            }
        });
    });
export function otpTemplateMail(title, content) {
    return `
    <!DOCTYPE html>
<html
    xmlns:v="urn:schemas-microsoft-com:vml"
    xmlns:o="urn:schemas-microsoft-com:office:office"
    lang="en"
>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <!--[if mso
            ]><xml
                ><o:OfficeDocumentSettings
                    ><o:PixelsPerInch>96</o:PixelsPerInch
                    ><o:AllowPNG /></o:OfficeDocumentSettings></xml
        ><![endif]-->
        <!--[if !mso]><!-->
        <link
            href="https://fonts.googleapis.com/css?family=Oswald"
            rel="stylesheet"
            type="text/css"
        />
        <link
            href="https://fonts.googleapis.com/css?family=Lato"
            rel="stylesheet"
            type="text/css"
        />
        <!--<![endif]-->
        <style>
            * {
                box-sizing: border-box;
            }

            body {
                margin: 0;
                padding: 0;
            }

            a[x-apple-data-detectors] {
                color: inherit !important;
                text-decoration: inherit !important;
            }

            #MessageViewBody a {
                color: inherit;
                text-decoration: none;
            }

            p {
                line-height: inherit;
            }

            .desktop_hide,
            .desktop_hide table {
                mso-hide: all;
                display: none;
                max-height: 0px;
                overflow: hidden;
            }

            .image_block img + div {
                display: none;
            }

            @media (max-width: 670px) {
                .desktop_hide table.icons-inner,
                .social_block.desktop_hide .social-table {
                    display: inline-block !important;
                }

                .icons-inner {
                    text-align: center;
                }

                .icons-inner td {
                    margin: 0 auto;
                }

                .mobile_hide {
                    display: none;
                }

                .row-content {
                    width: 100% !important;
                }

                .stack .column {
                    width: 100%;
                    display: block;
                }

                .mobile_hide {
                    min-height: 0;
                    max-height: 0;
                    max-width: 0;
                    overflow: hidden;
                    font-size: 0px;
                }

                .desktop_hide,
                .desktop_hide table {
                    display: table !important;
                    max-height: none !important;
                }
            }
        </style>
    </head>

    <body
        style="
            background-color: #c3f1ff;
            margin: 0;
            padding: 0;
            -webkit-text-size-adjust: none;
            text-size-adjust: none;
        "
    >
        <table
            class="nl-container"
            width="100%"
            border="0"
            cellpadding="0"
            cellspacing="0"
            role="presentation"
            style="
                mso-table-lspace: 0pt;
                mso-table-rspace: 0pt;
                background-color: #c3f1ff;
            "
        >
            <tbody>
                <tr>
                    <td>
                        <table
                            class="row row-1"
                            align="center"
                            width="100%"
                            border="0"
                            cellpadding="0"
                            cellspacing="0"
                            role="presentation"
                            style="
                                mso-table-lspace: 0pt;
                                mso-table-rspace: 0pt;
                                background-color: #c3f1ff;
                            "
                        >
                            <tbody>
                                <tr>
                                    <td>
                                        <table
                                            class="row-content stack"
                                            align="center"
                                            border="0"
                                            cellpadding="0"
                                            cellspacing="0"
                                            role="presentation"
                                            style="
                                                mso-table-lspace: 0pt;
                                                mso-table-rspace: 0pt;
                                                background-position: center top;
                                                background-repeat: no-repeat;
                                                color: #000;
                                                background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6081/Header_Background_01.png');
                                                width: 650px;
                                                margin: 0 auto;
                                            "
                                            width="650"
                                        >
                                            <tbody>
                                                <tr>
                                                    <td
                                                        class="column column-1"
                                                        width="100%"
                                                        style="
                                                            mso-table-lspace: 0pt;
                                                            mso-table-rspace: 0pt;
                                                            font-weight: 400;
                                                            text-align: left;
                                                            padding-top: 30px;
                                                            vertical-align: top;
                                                            border-top: 0px;
                                                            border-right: 0px;
                                                            border-bottom: 0px;
                                                            border-left: 0px;
                                                        "
                                                    >
                                                        <table
                                                            class="heading_block block-1"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-left: 10px;
                                                                        padding-right: 10px;
                                                                        text-align: center;
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <h2
                                                                        style="
                                                                            margin: 0;
                                                                            color: #0f7085;
                                                                            direction: ltr;
                                                                            font-family: Oswald,
                                                                                Arial,
                                                                                Helvetica
                                                                                    Neue,
                                                                                Helvetica,
                                                                                sans-serif;
                                                                            font-size: 24px;
                                                                            font-weight: normal;
                                                                            letter-spacing: 5px;
                                                                            line-height: 120%;
                                                                            text-align: center;
                                                                            margin-top: 0;
                                                                            margin-bottom: 0;
                                                                        "
                                                                    >
                                                                        SEND
                                                                        YOUR
                                                                    </h2>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="divider_block block-2"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="10"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td class="pad">
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                    >
                                                                        <table
                                                                            border="0"
                                                                            cellpadding="0"
                                                                            cellspacing="0"
                                                                            role="presentation"
                                                                            width="50%"
                                                                            style="
                                                                                mso-table-lspace: 0pt;
                                                                                mso-table-rspace: 0pt;
                                                                            "
                                                                        >
                                                                            <tr>
                                                                                <td
                                                                                    class="divider_inner"
                                                                                    style="
                                                                                        font-size: 1px;
                                                                                        line-height: 1px;
                                                                                        border-top: 2px
                                                                                            dashed
                                                                                            #0f7085;
                                                                                    "
                                                                                >
                                                                                    <span
                                                                                        >&#8202;</span
                                                                                    >
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="heading_block block-3"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-left: 10px;
                                                                        padding-right: 10px;
                                                                        text-align: center;
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <h1
                                                                        style="
                                                                            margin: 0;
                                                                            color: #cb0c0c;
                                                                            direction: ltr;
                                                                            font-family: Oswald,
                                                                                Arial,
                                                                                Helvetica
                                                                                    Neue,
                                                                                Helvetica,
                                                                                sans-serif;
                                                                            font-size: 64px;
                                                                            font-weight: normal;
                                                                            letter-spacing: 1px;
                                                                            line-height: 120%;
                                                                            text-align: center;
                                                                            margin-top: 0;
                                                                            margin-bottom: 0;
                                                                        "
                                                                    >
                                                                        <span
                                                                            class="tinyMce-placeholder"
                                                                            >${title}</span
                                                                        >
                                                                    </h1>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="divider_block block-4"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="10"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td class="pad">
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                    >
                                                                        <table
                                                                            border="0"
                                                                            cellpadding="0"
                                                                            cellspacing="0"
                                                                            role="presentation"
                                                                            width="50%"
                                                                            style="
                                                                                mso-table-lspace: 0pt;
                                                                                mso-table-rspace: 0pt;
                                                                            "
                                                                        >
                                                                            <tr>
                                                                                <td
                                                                                    class="divider_inner"
                                                                                    style="
                                                                                        font-size: 1px;
                                                                                        line-height: 1px;
                                                                                        border-top: 2px
                                                                                            dashed
                                                                                            #0f7085;
                                                                                    "
                                                                                >
                                                                                    <span
                                                                                        >&#8202;</span
                                                                                    >
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="heading_block block-5"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-left: 10px;
                                                                        padding-right: 10px;
                                                                        text-align: center;
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <h2
                                                                        style="
                                                                            margin: 0;
                                                                            color: #0f7085;
                                                                            direction: ltr;
                                                                            font-family: Oswald,
                                                                                Arial,
                                                                                Helvetica
                                                                                    Neue,
                                                                                Helvetica,
                                                                                sans-serif;
                                                                            font-size: 24px;
                                                                            font-weight: normal;
                                                                            letter-spacing: 5px;
                                                                            line-height: 120%;
                                                                            text-align: center;
                                                                            margin-top: 0;
                                                                            margin-bottom: 0;
                                                                        "
                                                                    >
                                                                       
                                                                    </h2>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <div
                                                            class="spacer_block block-6"
                                                            style="
                                                                height: 30px;
                                                                line-height: 30px;
                                                                font-size: 1px;
                                                            "
                                                        >
                                                            &#8202;
                                                        </div>
                                                        <table
                                                            class="image_block block-7"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                        style="
                                                                            line-height: 10px;
                                                                        "
                                                                    >
                                                                        <img
                                                                            src="https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6081/Header_Overlay_01.png"
                                                                            style="
                                                                                display: block;
                                                                                height: auto;
                                                                                border: 0;
                                                                                max-width: 650px;
                                                                                width: 100%;
                                                                            "
                                                                            width="650"
                                                                            alt="Image Of Love Header"
                                                                            title="Image Of Love Header"
                                                                        />
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table
                            class="row row-2"
                            align="center"
                            width="100%"
                            border="0"
                            cellpadding="0"
                            cellspacing="0"
                            role="presentation"
                            style="mso-table-lspace: 0pt; mso-table-rspace: 0pt"
                        >
                            <tbody>
                                <tr>
                                    <td>
                                        <table
                                            class="row-content stack"
                                            align="center"
                                            border="0"
                                            cellpadding="0"
                                            cellspacing="0"
                                            role="presentation"
                                            style="
                                                mso-table-lspace: 0pt;
                                                mso-table-rspace: 0pt;
                                                background-position: center top;
                                                background-repeat: no-repeat;
                                                color: #000;
                                                background-color: #fff;
                                                background-image: url('https://d1oco4z2z1fhwp.cloudfront.net/templates/default/6081/PliegueSobre_03.png');
                                                width: 650px;
                                                margin: 0 auto;
                                            "
                                            width="650"
                                        >
                                            <tbody>
                                                <tr>
                                                    <td
                                                        class="column column-1"
                                                        width="100%"
                                                        style="
                                                            mso-table-lspace: 0pt;
                                                            mso-table-rspace: 0pt;
                                                            font-weight: 400;
                                                            text-align: left;
                                                            padding-bottom: 50px;
                                                            padding-left: 20px;
                                                            padding-right: 20px;
                                                            padding-top: 10px;
                                                            vertical-align: top;
                                                            border-top: 0px;
                                                            border-right: 0px;
                                                            border-bottom: 0px;
                                                            border-left: 0px;
                                                        "
                                                    >
                                                        <table
                                                            class="paragraph_block block-1"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                                word-break: break-word;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-left: 10px;
                                                                        padding-right: 10px;
                                                                        padding-top: 10px;
                                                                    "
                                                                >
                                                                    <div
                                                                        style="
                                                                            color: #0f7085;
                                                                            font-family: Oswald,
                                                                                Arial,
                                                                                Helvetica
                                                                                    Neue,
                                                                                Helvetica,
                                                                                sans-serif;
                                                                            font-size: 20px;
                                                                            letter-spacing: 1px;
                                                                            line-height: 120%;
                                                                            text-align: center;
                                                                            mso-line-height-alt: 24px;
                                                                        "
                                                                    >
                                                                        <p
                                                                            style="
                                                                                margin: 0;
                                                                            "
                                                                        >
                                                                           
                                                                        </p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="paragraph_block block-2"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                                word-break: break-word;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-bottom: 10px;
                                                                        padding-left: 10px;
                                                                        padding-right: 10px;
                                                                        padding-top: 5px;
                                                                    "
                                                                >
                                                                    <div
                                                                        style="
                                                                            color: #4e8c9d;
                                                                            font-family: 'Lato',
                                                                                Tahoma,
                                                                                Verdana,
                                                                                Segoe,
                                                                                sans-serif;
                                                                            font-size: 16px;
                                                                            line-height: 150%;
                                                                            text-align: center;
                                                                            mso-line-height-alt: 24px;
                                                                        "
                                                                    >
                                                                        ${content}
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="divider_block block-3"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-bottom: 30px;
                                                                        padding-left: 10px;
                                                                        padding-right: 10px;
                                                                        padding-top: 30px;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                    >
                                                                        <table
                                                                            border="0"
                                                                            cellpadding="0"
                                                                            cellspacing="0"
                                                                            role="presentation"
                                                                            width="50%"
                                                                            style="
                                                                                mso-table-lspace: 0pt;
                                                                                mso-table-rspace: 0pt;
                                                                            "
                                                                        >
                                                                            <tr>
                                                                                <td
                                                                                    class="divider_inner"
                                                                                    style="
                                                                                        font-size: 1px;
                                                                                        line-height: 1px;
                                                                                        border-top: 2px
                                                                                            dashed
                                                                                            #0f7085;
                                                                                    "
                                                                                >
                                                                                    <span
                                                                                        >&#8202;</span
                                                                                    >
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="text_block block-4"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="10"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                                word-break: break-word;
                                                            "
                                                        >
                                                            <tr>
                                                                <td class="pad">
                                                                    <div
                                                                        style="
                                                                            font-family: sans-serif;
                                                                        "
                                                                    >
                                                                        <div
                                                                            class
                                                                            style="
                                                                                font-size: 12px;
                                                                                font-family: Oswald,
                                                                                    Arial,
                                                                                    Helvetica
                                                                                        Neue,
                                                                                    Helvetica,
                                                                                    sans-serif;
                                                                                mso-line-height-alt: 14.399999999999999px;
                                                                                color: #0f7085;
                                                                                line-height: 1.2;
                                                                            "
                                                                        >
                                                                           
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="button_block block-5"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="10"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td class="pad">
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                    >
                                                                        <!--[if mso]><v:roundrect xmlns:v="urn:schemas-microsoft-com:vml" xmlns:w="urn:schemas-microsoft-com:office:word" href="https://www.example.com" style="height:50px;width:253px;v-text-anchor:middle;" arcsize="0%" stroke="false" fillcolor="#cb0c0c"><w:anchorlock/><v:textbox inset="0px,0px,0px,0px"><center style="color:#ffffff; font-family:Arial, sans-serif; font-size:20px"><!
                                                                        [endif]--><a
                                                                            href="https://www.example.com"
                                                                            target="_blank"
                                                                            style="
                                                                                text-decoration: none;
                                                                                display: inline-block;
                                                                                color: #ffffff;
                                                                                background-color: #cb0c0c;
                                                                                border-radius: 0px;
                                                                                width: auto;
                                                                                border-top: 0px
                                                                                    solid
                                                                                    transparent;
                                                                                font-weight: undefined;
                                                                                border-right: 0px
                                                                                    solid
                                                                                    transparent;
                                                                                border-bottom: 0px
                                                                                    solid
                                                                                    transparent;
                                                                                border-left: 0px
                                                                                    solid
                                                                                    transparent;
                                                                                padding-top: 5px;
                                                                                padding-bottom: 5px;
                                                                                font-family: Oswald,
                                                                                    Arial,
                                                                                    Helvetica
                                                                                        Neue,
                                                                                    Helvetica,
                                                                                    sans-serif;
                                                                                font-size: 20px;
                                                                                text-align: center;
                                                                                mso-border-alt: none;
                                                                                word-break: keep-all;
                                                                            "
                                                                            ><span
                                                                                style="
                                                                                    padding-left: 20px;
                                                                                    padding-right: 20px;
                                                                                    font-size: 20px;
                                                                                    display: inline-block;
                                                                                    letter-spacing: 4px;
                                                                                "
                                                                                ><span
                                                                                    style="
                                                                                        word-break: break-word;
                                                                                        line-height: 40px;
                                                                                    "
                                                                                    >Go
                                                                                    Back
                                                                                    To
                                                                                    Website</span
                                                                                ></span
                                                                            ></a
                                                                        >
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table
                            class="row row-3"
                            align="center"
                            width="100%"
                            border="0"
                            cellpadding="0"
                            cellspacing="0"
                            role="presentation"
                            style="mso-table-lspace: 0pt; mso-table-rspace: 0pt"
                        >
                            <tbody>
                                <tr>
                                    <td>
                                        <table
                                            class="row-content stack"
                                            align="center"
                                            border="0"
                                            cellpadding="0"
                                            cellspacing="0"
                                            role="presentation"
                                            style="
                                                mso-table-lspace: 0pt;
                                                mso-table-rspace: 0pt;
                                                color: #000;
                                                width: 650px;
                                                margin: 0 auto;
                                            "
                                            width="650"
                                        >
                                            <tbody>
                                                <tr>
                                                    <td
                                                        class="column column-1"
                                                        width="50%"
                                                        style="
                                                            mso-table-lspace: 0pt;
                                                            mso-table-rspace: 0pt;
                                                            font-weight: 400;
                                                            text-align: left;
                                                            padding-bottom: 10px;
                                                            padding-left: 10px;
                                                            padding-right: 10px;
                                                            padding-top: 10px;
                                                            vertical-align: top;
                                                            border-top: 0px;
                                                            border-right: 0px;
                                                            border-bottom: 0px;
                                                            border-left: 0px;
                                                        "
                                                    >
                                                        <table
                                                            class="image_block block-1"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                        style="
                                                                            line-height: 10px;
                                                                        "
                                                                    >
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td
                                                        class="column column-2"
                                                        width="50%"
                                                        style="
                                                            mso-table-lspace: 0pt;
                                                            mso-table-rspace: 0pt;
                                                            font-weight: 400;
                                                            text-align: left;
                                                            padding-bottom: 10px;
                                                            padding-left: 10px;
                                                            padding-right: 10px;
                                                            padding-top: 10px;
                                                            vertical-align: top;
                                                            border-top: 0px;
                                                            border-right: 0px;
                                                            border-bottom: 0px;
                                                            border-left: 0px;
                                                        "
                                                    >
                                                        <table
                                                            class="image_block block-1"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                        style="
                                                                            line-height: 10px;
                                                                        "
                                                                    >
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table
                            class="row row-4"
                            align="center"
                            width="100%"
                            border="0"
                            cellpadding="0"
                            cellspacing="0"
                            role="presentation"
                            style="mso-table-lspace: 0pt; mso-table-rspace: 0pt"
                        >
                            <tbody>
                                <tr>
                                    <td>
                                        <table
                                            class="row-content stack"
                                            align="center"
                                            border="0"
                                            cellpadding="0"
                                            cellspacing="0"
                                            role="presentation"
                                            style="
                                                mso-table-lspace: 0pt;
                                                mso-table-rspace: 0pt;
                                                background-color: #fff;
                                                color: #000;
                                                width: 650px;
                                                margin: 0 auto;
                                            "
                                            width="650"
                                        >
                                            <tbody>
                                                <tr>
                                                    <td
                                                        class="column column-1"
                                                        width="100%"
                                                        style="
                                                            mso-table-lspace: 0pt;
                                                            mso-table-rspace: 0pt;
                                                            font-weight: 400;
                                                            text-align: left;
                                                            vertical-align: top;
                                                            border-top: 0px;
                                                            border-right: 0px;
                                                            border-bottom: 0px;
                                                            border-left: 0px;
                                                        "
                                                    >
                                                        <div
                                                            class="spacer_block block-1"
                                                            style="
                                                                height: 35px;
                                                                line-height: 35px;
                                                                font-size: 1px;
                                                            "
                                                        >
                                                            &#8202;
                                                        </div>
                                                        <table
                                                            class="paragraph_block block-3"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="20"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                                word-break: break-word;
                                                            "
                                                        >
                                                            <tr>
                                                                <td class="pad">
                                                                    <div
                                                                        style="
                                                                            color: #0f7085;
                                                                            font-family: 'Lato',
                                                                                Tahoma,
                                                                                Verdana,
                                                                                Segoe,
                                                                                sans-serif;
                                                                            font-size: 14px;
                                                                            line-height: 120%;
                                                                            text-align: center;
                                                                            mso-line-height-alt: 16.8px;
                                                                        "
                                                                    >
                                                                        <p
                                                                            style="
                                                                                margin: 0;
                                                                                word-break: break-word;
                                                                            "
                                                                        >
                                                                            <span
                                                                                >
                                                                                Contact
                                                                                :</span
                                                                            >
                                                                        </p>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <table
                                                            class="social_block block-2"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        padding-bottom: 10px;
                                                                        padding-left: 20px;
                                                                        padding-right: 20px;
                                                                        padding-top: 10px;
                                                                        text-align: center;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                    >
                                                                        <table
                                                                            class="social-table"
                                                                            width="208px"
                                                                            border="0"
                                                                            cellpadding="0"
                                                                            cellspacing="0"
                                                                            role="presentation"
                                                                            style="
                                                                                mso-table-lspace: 0pt;
                                                                                mso-table-rspace: 0pt;
                                                                                display: inline-block;
                                                                            "
                                                                        >
                                                                            <tr>
                                                                                <td
                                                                                    style="
                                                                                        padding: 0
                                                                                            10px
                                                                                            0
                                                                                            10px;
                                                                                    "
                                                                                >
                                                                                    <a
                                                                                        href="https://www.facebook.com/hoanghuydev"
                                                                                        target="_blank"
                                                                                        ><img
                                                                                            src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-outline-circle-color/facebook@2x.png"
                                                                                            width="32"
                                                                                            height="32"
                                                                                            alt="Facebook"
                                                                                            title="facebook"
                                                                                            style="
                                                                                                display: block;
                                                                                                height: auto;
                                                                                                border: 0;
                                                                                            "
                                                                                    /></a>
                                                                                </td>
                                                                                <td
                                                                                    style="
                                                                                        padding: 0
                                                                                            10px
                                                                                            0
                                                                                            10px;
                                                                                    "
                                                                                >
                                                                                    <a
                                                                                        href="https://www.instagram.com/hoanghuydev"
                                                                                        target="_blank"
                                                                                        ><img
                                                                                            src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-outline-circle-color/instagram@2x.png"
                                                                                            width="32"
                                                                                            height="32"
                                                                                            alt="Instagram"
                                                                                            title="instagram"
                                                                                            style="
                                                                                                display: block;
                                                                                                height: auto;
                                                                                                border: 0;
                                                                                            "
                                                                                    /></a>
                                                                                </td>
                                                                                <td
                                                                                    style="
                                                                                        padding: 0
                                                                                            10px
                                                                                            0
                                                                                            10px;
                                                                                    "
                                                                                >
                                                                                    <a
                                                                                        href="https://www.tiktok.com/hoanghuydev"
                                                                                        target="_blank"
                                                                                        ><img
                                                                                            src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-outline-circle-color/tiktok@2x.png"
                                                                                            width="32"
                                                                                            height="32"
                                                                                            alt="TikTok"
                                                                                            title="TikTok"
                                                                                            style="
                                                                                                display: block;
                                                                                                height: auto;
                                                                                                border: 0;
                                                                                            "
                                                                                    /></a>
                                                                                </td>
                                                                                <td
                                                                                    style="
                                                                                        padding: 0
                                                                                            10px
                                                                                            0
                                                                                            10px;
                                                                                    "
                                                                                >
                                                                                    <a
                                                                                        href="https://www.youtube.com/@hoanghuydev"
                                                                                        target="_blank"
                                                                                        ><img
                                                                                            src="https://app-rsrc.getbee.io/public/resources/social-networks-icon-sets/t-outline-circle-color/youtube@2x.png"
                                                                                            width="32"
                                                                                            height="32"
                                                                                            alt="YouTube"
                                                                                            title="YouTube"
                                                                                            style="
                                                                                                display: block;
                                                                                                height: auto;
                                                                                                border: 0;
                                                                                            "
                                                                                    /></a>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>

                                                        <div
                                                            class="spacer_block block-4"
                                                            style="
                                                                height: 25px;
                                                                line-height: 25px;
                                                                font-size: 1px;
                                                            "
                                                        >
                                                            &#8202;
                                                        </div>
                                                        <table
                                                            class="image_block block-5"
                                                            width="100%"
                                                            border="0"
                                                            cellpadding="0"
                                                            cellspacing="0"
                                                            role="presentation"
                                                            style="
                                                                mso-table-lspace: 0pt;
                                                                mso-table-rspace: 0pt;
                                                            "
                                                        >
                                                            <tr>
                                                                <td
                                                                    class="pad"
                                                                    style="
                                                                        width: 100%;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="alignment"
                                                                        align="center"
                                                                        style="
                                                                            line-height: 10px;
                                                                        "
                                                                    >
                                                                        <img
                                                                            src="https://i.ibb.co/fXKK9L4/love-point-low-resolution-logo-color-on-transparent-background.png"
                                                                            style="
                                                                                display: block;
                                                                                height: auto;
                                                                                border: 0;
                                                                                max-width: 160px;
                                                                                width: 100%;
                                                                            "
                                                                            width="160"
                                                                            alt="Your Logo Here"
                                                                            title="Your Logo Here"
                                                                        />
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <div
                                                            class="spacer_block block-6"
                                                            style="
                                                                height: 25px;
                                                                line-height: 25px;
                                                                font-size: 1px;
                                                            "
                                                        >
                                                            &#8202;
                                                        </div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
        <!-- End -->
    </body>
</html>

    `;
}
