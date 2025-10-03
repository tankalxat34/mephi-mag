#!/usr/bin/sh

# есть три команды по проверке подписи к пакету
#$PACKAGE_PATH=$1

echo "Начинаю проверку подписи пакета" $1
echo "-------------------------------"
echo "1: gpg --verify" $1
gpg --verify $1

echo "Вердикт: " $?
echo "----"

echo "2: dpkg-sig --verify" $1
dpkg-sig --verify $1

echo "Вердикт: " $?
echo "----"

echo "3: debsig-verify" $1
debsig-verify $1

echo "Вердикт: " $?

