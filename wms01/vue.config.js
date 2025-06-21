const {defineConfig} = require('@vue/cli-service')
const fs = require('fs');
module.exports = defineConfig({
  devServer: {
    historyApiFallback: true,
    allowedHosts: "all",
  },
  transpileDependencies: true,
  lintOnSave: false, //关闭eslint检查
})
