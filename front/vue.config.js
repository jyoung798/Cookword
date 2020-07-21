const webpack = require('webpack');
module.exports = {
	devServer: {
		// 웹팩 데브서버에서 제공해주는 overlay 속성 사용
		overlay: false,
	},
	configureWebpack: {
		plugins: [
			new webpack.ProvidePlugin({
				'window.Quill': 'quill/dist/quill.js',
				Quill: 'quill/dist/quill.js',
			}),
		],
	},
};
