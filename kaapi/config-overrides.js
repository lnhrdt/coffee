const rewireCssModules = require('react-app-rewire-css-modules');

module.exports = function override(config, env) {
    return rewireCssModules(config, env);
};
