# http://dblab.xmu.edu.cn/blog/2138-2/#more-2138

cd ~
sudo  unzip  ~/Downloads/apache-maven-3.6.3-bin.zip  -d  /usr/local
cd  /usr/local
sudo  mv  ./apache-maven-3.6.3  ./maven
sudo  chown  -R  hadoop:hadoop  ./maven