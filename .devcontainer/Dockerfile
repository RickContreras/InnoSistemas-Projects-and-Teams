FROM eclipse-temurin:17-jdk-jammy

RUN apt-get -y update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
    postgresql-client \
    git \
    maven \
    sudo \
    curl \
    gnupg \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

RUN curl -s https://packagecloud.io/install/repositories/github/git-lfs/script.deb.sh | bash \
    && apt-get install -y git-lfs \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*

RUN useradd -ms /bin/bash developer \
    && mkdir -p /workspace \
    && chown -R developer:developer /workspace \
    && usermod -aG sudo developer \
    && echo "developer ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers

USER developer
WORKDIR /workspace