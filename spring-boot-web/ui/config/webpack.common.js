const path = require('path');
const autoprefixer = require('autoprefixer');
const ProgressBarPlugin = require('progress-bar-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');

const ROOT_DIR = path.resolve(__dirname, '../');
const DIST_DIR = path.resolve(ROOT_DIR, '../src/main/resources/static');
const devMode = process.env.NODE_ENV !== 'production';

module.exports = {

    resolve: {
        modules: [
            'source',
            'node_modules',
            'theme',
            'theme/fonts',
        ],
        extensions: ['.js', '.jsx', '.css', '.scss'],
    },
    module: {
        rules: [
            // js
            {
                test: /\.js|jsx$/, use: {
                    loader: 'babel-loader',
                }, exclude: [path.resolve(ROOT_DIR, 'node_modules')],
            },
            // images
            {
                test: /\.(png|ico|gif|jpe?g)(\?[a-z0-9]+)?$/, use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 1024,//<limit  use base64 ,otherwise package normally
                            name: '/images/[hash:8].[name].[ext]',//create folder under root dir
                        },
                    },
                ],
            },
            {
                test: /\.svg$/,
                use: [
                    {
                        loader: 'babel-loader',
                    },
                    {
                        loader: 'react-svg-loader',
                        options: {
                            jsx: true, // true outputs JSX tags
                        },
                    },
                ],
            },
            // fonts
            {test: /\.(woff|woff2|eot|ttf|otf)$/, use: ['url-loader?limit=8192']},
            // css
            {
                test: /\.css$/, loader: [
                    devMode ? 'style-loader' : MiniCssExtractPlugin.loader,
                    'css-loader',
                ],
            },
            // sass
            {
                test: /\.scss$/, use: [
                    devMode ? 'style-loader' : MiniCssExtractPlugin.loader,
                    {loader: 'css-loader', options: {sourceMap: true}},
                    {
                        loader: 'postcss-loader', options: {
                            sourceMap: true, plugins() {
                                return [autoprefixer('last 2 version')];
                            },
                        },
                    },
                    {loader: 'sass-loader', options: {sourceMap: true}},
                ],
            },
            {
                test: /\.less$/,
                use: [
                    devMode ? 'style-loader' : MiniCssExtractPlugin.loader,
                    'css-loader',
                    {
                        loader: 'less-loader',
                        options: {
                            javascriptEnabled: true,
                        },
                    }],
            },
        ],
    },
    plugins: [
        new ProgressBarPlugin({
            format: 'Build [:bar] :percent (:elapsed seconds)',
            clear: false,
        }),
        //HtmlWebpackPlugin must before releative plugin
        new HtmlWebpackPlugin({
            title: 'React + SptingBoot',
            inject: true,
            sourceMap: true,
            //chunksSortMode: 'dependency',
            favicon: path.resolve(DIST_DIR, '../templates/images/favicon.png'),
            template: path.resolve(DIST_DIR, '../templates/index.html'),
            filename: 'index.html',
            chunks: ['index'],
            minify: {
                collapseWhitespace: true,
                removeAttributeQuotes: true,
                removeComments: true,
            },
        }),
        new HtmlWebpackPlugin({
            title: 'React + SptingBoot',
            inject: 'body',
            sourceMap: true,
            //chunksSortMode: 'dependency',
            favicon: path.resolve(DIST_DIR, '../templates/images/favicon.png'),
            template: path.resolve(DIST_DIR, '../templates/index.html'),
            filename: 'login.html',
            chunks: ['login'],
            minify: {
                collapseWhitespace: true,
                removeAttributeQuotes: true,
                removeComments: true,
            },
        }),
        new HtmlWebpackPlugin({
            title: 'React + SptingBoot',
            inject: 'body',
            sourceMap: true,
            //chunksSortMode: 'dependency',
            favicon: path.resolve(DIST_DIR, '../templates/images/favicon.png'),
            template: path.resolve(DIST_DIR, '../templates/index.html'),
            filename: 'main.html',
            chunks: ['main'],
            minify: {
                collapseWhitespace: true,
                removeAttributeQuotes: true,
                removeComments: true,
            },
        }),
        // clean dist folder
        new CleanWebpackPlugin(['*.*'], {
            root: DIST_DIR,
            verbose: true,//开启在控制台输出信息
            dry: false,//启用删除文件
            exclude: ['/error'],
        }),
        new CopyWebpackPlugin([
            {
                from: path.resolve(DIST_DIR, '../templates/error'),
                to: path.resolve(DIST_DIR, '../static/error'),
            }]),
        //split css from js
        //MiniCssExtractPlugin replace ExtractTextPlugin since webpack4
        new MiniCssExtractPlugin({
            // Options similar to the same options in webpackOptions.output
            // both options are optional
            filename: devMode ? '[name].css' : '[name].[hash].css',
            chunkFilename: devMode ? '[id].css' : '[id].[hash].css',
        }), //单独使用link标签加载css并设置路径，相对于output配置中的publickPath

        /*new OfflinePlugin({
            caches: 'all',
            AppCache: false,
            ServiceWorker: {
                minify: false, // 开启压缩
            },
            autoUpdate: true,                // 自动更新
        }),*/
    ],
    optimization: {
        //splitChunks replace  CommonsChunkPlugin since webpack4
        splitChunks: {
            chunks: 'all',
            minSize: 30000,
            maxSize: 0,
            minChunks: 1,
            maxAsyncRequests: 5,
            maxInitialRequests: 3,
            automaticNameDelimiter: '~',
            name: true,
            cacheGroups: {
                vendors: {
                    test: /[\\/]node_modules[\\/]/,
                    priority: -10,
                    //name: 'vendors'
                },
                default: {
                    minChunks: 2,
                    priority: -20,
                    reuseExistingChunk: true,
                },
            },
        },
    },
};