const path = require('path');
module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 12346,
        proxy: {
            '/api': {
                target: 'http://localhost:12345',
                ws: true,
                changeOrigin: true
            }
        }
    }
}