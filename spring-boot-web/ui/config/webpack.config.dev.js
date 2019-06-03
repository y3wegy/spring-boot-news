const webpack = require('webpack');
const merge = require('webpack-merge');
const path = require('path');

const common = require('./webpack.common.js');

const ROOT_DIR = path.resolve(__dirname, '../');
const DIST_DIR = path.resolve(ROOT_DIR, '../src/main/resources/static');

const SRC_DIR = path.resolve(ROOT_DIR, './source');
const DEV_SERVER_PUBLIC_PATH = '/';
const DEV_SERVER_HOT_PORT = 8888;

//webpack 将多个模块打包之后的代码集合称为 chunk
module.exports = merge(common, {
    mode: 'development', //
    devtool: 'eval-source-map',//'cheap-module-eval-source-map',
    entry: {
        common: [
            '@babel/polyfill',
            'react-hot-loader/patch',
            // 为webpack-dev-server的环境打包好运行代码
            // 然后连接到指定服务器域名与端口
            //'webpack-dev-server/client?http://localhost:' + DEV_SERVER_HOT_PORT + DEV_SERVER_PUBLIC_PATH,
            // 开启react代码的模块热替换（HMR）
            //'webpack-hot-middleware/client?path=http://localhost:' + DEV_SERVER_HOT_PORT + DEV_SERVER_PUBLIC_PATH,
            "webpack/hot/only-dev-server",
        ],
        index: path.join(SRC_DIR, '/index.jsx'),
        login: path.join(SRC_DIR, '/LoginIndex.jsx'),
        main: path.join(SRC_DIR, './MainIndex.jsx')
    },
    output: {
        path: DIST_DIR,
        publicPath: '.' + DEV_SERVER_PUBLIC_PATH,
        filename: '[name].[hash:8].js',
        chunkFilename: '[name].[id].js'
    },
    plugins: [
        //定义当前生产环境为dev环境
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"dev"'
            }
        }),
        new webpack.NoEmitOnErrorsPlugin(),
        //make sure asset hash id not change when hot load
        new webpack.NamedModulesPlugin(),
        new webpack.HotModuleReplacementPlugin()
    ],
    devServer: {
        hot: true, // 激活服务器的HMR
        //inline: true,
        contentBase: DIST_DIR,
        publicPath: DEV_SERVER_PUBLIC_PATH,
        port: DEV_SERVER_HOT_PORT,
        historyApiFallback: true,
        // headers: {
        //     "Access-Control-Allow-Origin": "http://localhost:9999"
        // },
        //https://webpack.js.org/configuration/dev-server/#devserver-proxy
        proxy: [{
            context: ['/api'],
            target: 'http://localhost:9999',
        },
            {
                context: ['/druid'],
                target: 'http://localhost:9999',
            }]
    }
});