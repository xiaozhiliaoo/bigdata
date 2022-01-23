sudo useradd -m hadoop -s /bin/bash

sudo passwd hadoop     # 密码也是hadoop

chmod 555 /etc/sudoers
vim /etc/sudoers
# 给hadoop用户加root权限 https://cloud.tencent.com/developer/article/1725832
