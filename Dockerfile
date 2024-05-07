FROM maven:3-amazoncorretto-21
LABEL authors="Luiz Fernandes De Oliveira"

WORKDIR /app

ENTRYPOINT ["tail", "-f", "/dev/null"]