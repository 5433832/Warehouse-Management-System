const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    historyApiFallback: true,
    allowedHosts: "all",
  },
})
