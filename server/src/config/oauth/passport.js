require('dotenv').config();
const passport = require('passport');
import * as authServices from '../../services/auth';
import * as userServices from '../../services/user';
var GoogleStrategy = require('passport-google-oauth20').Strategy;
var FacebookStrategy = require('passport-facebook').Strategy;
var GitHubStrategy = require('passport-github2').Strategy;
async function registerUserWithOAuth(email, profile) {
    const userName = profile.provider + profile.id;
    const fullName = profile.displayName || userName;
    const password = process.env.DEFAULT_PASSWORD;
    const association = profile.provider;
    const resp = await authServices.register(
        email,
        fullName,
        userName,
        password,
        association,
        true
    );
    const user = await userServices.findOne({
        email,
        association,
        userName,
    });
    return user;
}
passport.use(
    new GoogleStrategy(
        {
            clientID: process.env.GOOGLE_CLIENT_ID,
            clientSecret: process.env.GOOGLE_CLIENT_SECRET,
            callbackURL: process.env.GOOGLE_REDIRECT_URI,
        },
        async function (accessToken, refreshToken, profile, cb) {
            const email = profile.emails[0].value;
            await registerUserWithOAuth(email, profile).then((user) => {
                profile = user;
            });
            return cb(null, profile);
        }
    )
);
passport.use(
    new FacebookStrategy(
        {
            clientID: process.env.FACEBOOK_APP_ID,
            clientSecret: process.env.FACEBOOK_APP_SECRET,
            callbackURL: process.env.FACEBOOK_REDIRECT_URI,
        },
        async function (accessToken, refreshToken, profile, cb) {
            const email = profile._json.email
                ? profile._json.email
                : profile.provider + profile.id + '@gmail.com';
            await registerUserWithOAuth(email, profile).then((user) => {
                profile = user;
            });
            return cb(null, profile);
        }
    )
);
passport.use(
    new GitHubStrategy(
        {
            clientID: process.env.GITHUB_CLIENT_ID,
            clientSecret: process.env.GITHUB_CLIENT_SECRET,
            callbackURL: process.env.GITHUB_REDIRECT_URI,
        },
        async function (accessToken, refreshToken, profile, cb) {
            const email = profile._json.email
                ? profile._json.email
                : profile.provider + profile.id + '@gmail.com';
            await registerUserWithOAuth(email, profile).then((user) => {
                profile = user;
            });
            return cb(null, profile);
        }
    )
);
