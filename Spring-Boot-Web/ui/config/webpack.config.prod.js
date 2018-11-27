const webpack = require('webpack');
const path = require('path');
const merge = require('webpack-merge');
const CompressionWebpackPlugin = require('compression-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');
const {BundleAnalyzerPlugin} = require('webpack-bundle-analyzer');



const common = require('./webpack.common.js');

const ROOT_DIR = path.resolve(__dirname, '../');
const DIST_DIR = path.resolve(ROOT_DIR, '../src/main/resources/static');
const SRC_DIR = path.resolve(ROOT_DIR, './source');

const PUBLIC_PATH = './';

const prodConfig = {
    mode: 'production',
    devtool: 'cheap-module-source-map',
    target: 'web',
    entry: {
        common: [
            '@babel/polyfill'
        ],
        index: path.join(SRC_DIR, '/index.jsx'),
        login: path.join(SRC_DIR, '/LoginIndex.jsx'),
        main: path.join(SRC_DIR, './MainIndex.jsx')
    },
    output: {
        path: DIST_DIR,
        publicPath: PUBLIC_PATH, //should be CDN URL
        filename: '[name].[chunkhash:8].js',
        chunkFilename: '[name].[id].js',
    },
    optimization: {
        runtimeChunk: false,
        minimizer: [
            new UglifyJsPlugin({
                cache: true,
                parallel: true,
                sourceMap: true
            })
        ]
    },
    plugins: [
        new webpack.optimize.OccurrenceOrderPlugin(),
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: '"production"'
            }
        }),
        new CompressionWebpackPlugin({
            filename: '[path].gz[query]',
            algorithm: 'gzip',
            test: new RegExp('\\.(js|jsx|css)$'),
            threshold: 8192,
            minRatio: 0.8
        }),
    ]
};
if (process.env.NODE_ANALYZE) {
    prodConfig.plugins.push(new BundleAnalyzerPlugin());
}
module.exports = merge(common, prodConfig);