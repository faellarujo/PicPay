FROM ubuntu:latest
LABEL authors="faell"

ENTRYPOINT ["top", "-b"]