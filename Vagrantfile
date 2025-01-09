# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/focal64"

  config.vm.network "forwarded_port", guest: 9090, host: 9090, host_ip: "127.0.0.1"

  config.vm.provider "virtualbox" do |vb|
     # vb.cpus = 4
  end

  config.vm.provision "shell", inline: <<-SHELL
    apt-get update
    apt-get install -y curl git autoconf patch build-essential rustc \
      libssl-dev libyaml-dev libreadline6-dev zlib1g-dev libgmp-dev \
      libncurses5-dev libffi-dev libgdbm6 libgdbm-dev libdb-dev uuid-dev \
      build-essential libssl-dev zlib1g-dev \
      libbz2-dev libreadline-dev libsqlite3-dev curl git \
      libncursesw5-dev xz-utils tk-dev libxml2-dev libxmlsec1-dev libffi-dev \
      liblzma-dev docker docker-compose-v2
    su - vagrant -c " git config --global advice.detachedHead false"
    su - vagrant -c "[ -d ~/.asdf ] || git clone https://github.com/asdf-vm/asdf.git ~/.asdf --branch v0.15.0"
    su - vagrant -c "grep -q asdf.sh ~/.bashrc || echo -e '. \"\\$HOME/.asdf/asdf.sh\"\n. \"\\$HOME/.asdf/completions/asdf.bash\"' >> .bashrc"
    su - vagrant -c ".  ~/.asdf/asdf.sh && for i in direnv java ruby python; do echo Install \\$i plugin && asdf plugin add \\$i; done"
    su - vagrant -c ".  ~/.asdf/asdf.sh && echo Install latest direnv plugin && asdf install direnv latest && asdf global direnv latest && asdf direnv setup --shell bash --version latest"
    su - vagrant -c ".  ~/.asdf/asdf.sh && echo Allow direnv work directories && cd /vagrant && direnv allow && asdf direnv install"
    su - vagrant -c ".  ~/.asdf/asdf.sh && echo Allow direnv work directories && cd /vagrant/03-thread-inseguro && direnv allow && asdf direnv install"
    docker compose -f /vagrant/prometheus/docker-compose.yml up -d
  SHELL
end
