module.exports = {
	devServer: {
		// 웹팩 데브서버에서 제공해주는 overlay 속성 사용
		overlay: false,
		proxy: {
			'/api': { target: 'http://localhost:3000', changeOrigin: true },
		},
	},
};
