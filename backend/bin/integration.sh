#!/bin/sh
set -eu

if [ $# -ne 3 ]; then
    echo "Usage: $0 <host> <port> <image>"
    exit 1
fi

wait_for_url () {
    echo "Testing $1..."
    printf 'GET %s\nHTTP 200' "$1" | hurl --retry "$2" > /dev/null;
    return 0
}

url="$1:$2"
image="$3"
healthcheck="$url/api/healthcheck"

echo "Starting container"
docker run --rm -d -p "$2:8080" --name integration-be --env-file ./env/prod-subst.env $image

echo "Waiting server to be ready"
wait_for_url $healthcheck 60

echo "Running Hurl tests"
hurl --variable host=$url --test hurl/connectivity.hurl

echo "Stopping container"
docker stop integration-be
