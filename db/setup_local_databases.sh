#!/usr/bin/env sh

set -e

mysql --version
mysql --user root < setup_local_development_database.sql
mysql --user root < setup_local_test_database.sql
