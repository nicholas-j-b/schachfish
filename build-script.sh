#!/bin/sh

redis-server &

gradle clean build
