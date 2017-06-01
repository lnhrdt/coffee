cf push kaapi --hostname letsgetcoffee -d cfapps.io -p kaapi/build -b staticfile_buildpack
cf push latte --hostname letsgetcoffee -d cfapps.io --route-path api -p ./latte/build/libs/latte-0.0.1-SNAPSHOT.jar -b java_buildpack

