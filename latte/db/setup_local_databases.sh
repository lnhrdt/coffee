#!/usr/bin/env sh

set -e

mysql --version
mysql --user root < setup_local_databases.sql
