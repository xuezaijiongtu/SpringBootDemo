var Webpack = require("webpack");
var path = require("path");
module.exports = {
	entry: './src/main',
	output:{
		path:path.join(__dirname,'./dist/'),
		filename:'[name].js',
		publicPath:'/dist/'
	},
	devServer:{
        historyApiFallback: true,
        hot: false,
        inline: true,
        grogress: true,
    },
    module: {
        loaders: [
            { test: /\.vue$/, loader: 'vue' },
            { test: /\.js$/, loader: 'babel', exclude: /node_modules/ },
            { test: /\.css$/, loader: 'style!css!autoprefixer'},
            { test: /\.scss$/, loader: 'style!css!sass?sourceMap'},
            { test: /\.(png|jpg|gif)$/, loader: 'url-loader?limit=8192'},
            { test: /\.(html|tpl)$/, loader: 'html-loader' },
        ]
    },
    babel: {
        presets: ['es2015'],
        plugins: ['transform-runtime']
    },
}
