module.exports = function override(config, env) {

    const cssRule = config.module.rules.find(rule => rule.test && rule.test.test && rule.test.test('.css'))
    const cssRuleLoaders = (env === 'development') ? cssRule.use : cssRule.loader
    const cssLoader = cssRuleLoaders.find(loader => loader.loader && loader.loader.includes('css-loader'))
    cssLoader.options.modules = true
    if (env === 'development') {
        cssLoader.options.localIdentName = '[name]__[local]___[hash:base64:5'
    }

    const svgRule = {
        test: /\.svg$/,
        use: [
            {
                loader: require.resolve('babel-loader'),
                options: {presets: [require.resolve('babel-preset-react-app')]},
            },
            require.resolve('react-svg-loader')
        ]
    }
    config.module.rules.push(svgRule)
    const fileLoader = config.module.rules.find(rule => rule.loader && rule.loader.includes('file-loader'))
    fileLoader.exclude.push(/\.svg$/)

    return config
}
