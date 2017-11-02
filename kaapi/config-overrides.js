const rewireCssModules = require('react-app-rewire-css-modules');
const rewireSvgReactLoader = require('react-app-rewire-svg-react-loader');
// const rewireTypescriptPlugin = require('react-app-rewire-typescript');

module.exports = function override(config, env) {
    // config = rewireTypescriptPlugin(config, env);
    config = rewireCssModules(config, env);
    config = rewireSvgReactLoader(config, env);
    return config;
};
