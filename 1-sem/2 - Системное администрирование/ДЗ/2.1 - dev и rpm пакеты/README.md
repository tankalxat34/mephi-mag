# Лог выполнения задания

## 1. RPM (CensOS 10 Stream (x86_64))
Linux lptp-s-cexp01 6.12.0-134.el10.x86_64 #1 SMP PREEMPT_DYNAMIC Wed Sep 24 15:01:11 UTC 2025 x86_64 GNU/Linux
Linux pc-c-mephi01 6.12.0-134.el10.x86_64 #1 SMP PREEMPT_DYNAMIC Wed Sep 24 15:01:11 UTC 2025 x86_64 GNU/Linux

### Ход работы

```bash
# 1. Установка зависимостей, подготовка структуры
podst@pc-c-mephi01:~$ cd
podst@pc-c-mephi01:~$ sudo dnf install rpm-sign rpmdev* pinentry
podst@pc-c-mephi01:~$ rpmdev-setuptree
podst@pc-c-mephi01:~$ cd rpmbuild/SOURCES

# 2. Написание/копирование исходных кодов пакета.
podst@pc-c-mephi01:~/rpmbuild/SOURCES$ mkdir mephi-script1-0.1.0
podst@pc-c-mephi01:~/rpmbuild/SOURCES$ cd mephi-script1-0.1.0
podst@pc-c-mephi01:~/rpmbuild/SOURCES/mephi-script1-0.1.0$ nano mephi-script1
podst@pc-c-mephi01:~/rpmbuild/SOURCES/mephi-script1-0.1.0$ cd ~/rpmbuild/SPECS
podst@pc-c-mephi01:~/rpmbuild/SPECS$ dos2unix mephi-script1.spec
dos2unix: преобразование файла mephi-script1.spec в формат Unix…

# 3. Сборка на основании спецификации с сохранением прогресса в лог
podst@pc-c-mephi01:~/rpmbuild/SPECS$ rpmbuild -ba mephi-script1.spec >> rpm.log 2>&1
podst@pc-c-mephi01:~/rpmbuild/SPECS$ cd

# 4. Генерация ключей и подписание пакета
podst@pc-c-mephi01:~$ gpg2 --gen-key
podst@pc-c-mephi01:~$ gpg2 --export -a "Александр Подстречный RPM" > RPM-GPG-KEY-Александр-Подстречный
podst@pc-c-mephi01:~$ gpg -k
[keyboxd]
---------
pub   rsa3072 2025-10-02 [SC] [   годен до: 2028-10-01]
      24986DD4E55C0FD141A1A44A881EF1577E91BDC6
uid         [  абсолютно ] Александр Подстречный RPM <tankalxat34@gmail.com>
sub   rsa3072 2025-10-02 [E] [   годен до: 2028-10-01]
podst@pc-c-mephi01:~$ rpm --addsign ~/rpmbuild/RPMS/x86_64/mephi-script1-0.1.0-1.el10.x86_64.rpm
/home/podst/rpmbuild/RPMS/x86_64/mephi-script1-0.1.0-1.el10.x86_64.rpm:

# Чтобы проверить подпись, необходимо импортировать публичный ключ
podst@pc-c-mephi01:~$ sudo rpm --import RPM-GPG-KEY-Александр-Подстречный
podst@pc-c-mephi01:~$ rpm --checksig ~/rpmbuild/RPMS/x86_64/mephi-script1-0.1.0-1.el10.x86_64.rpm
/home/podst/rpmbuild/RPMS/x86_64/mephi-script1-0.1.0-1.el10.x86_64.rpm: digests signatures ОК
```

### Проблемы
#### ❌ Утилита easy-rsa вместо gpg.
> [!NOTE]
> Не работает gpg? Зацикливается диалог на этапе ввода мастер-фразы? Возможное решение этой проблемы: `rm -rf ~/.gnupg && rm -rf /etc/pki`
>
> Ошибка с непонятным символом `$'\r'`? Этот символ заносится в файл виндой. Чтобы избавиться от него внутри Linux надо выполнить преобразование файла из формата DOS в формат Unix: `dos2unix mephi-script1.spec`

Фиксируется непреодолимая ошибка `gpg2 --gen-keys`. Диалог ввода мастер-фразы зацикливается. Продолжить выполнение скрипта по созданию ключа и подписанию пакета невозможно. В связи с этим далее используется пакет `easy-rsa`. В Ubuntu он доступен по команде 

В ОС установлен пакет `easy-rsa-3.2.4-1.el10_2.noarch.rpm` (свободно распространяемая функциональная часть ПО OpenVPN).

По умолчанию активный скрипт пакета устанавливается в `/usr/share/easy-rsa/3.2.4`. Взаимодействие со скриптом происходит командой `./easyrsa`. В этом расположении необходимо создать файл `vars` со следующим содержимым:
```bash
set_var EASYRSA_REQ_COUNTRY "RU"
set_var EASYRSA_REQ_PROVINCE "Moscow"
set_var EASYRSA_REQ_CITY "Moscow"
set_var EASYRSA_REQ_ORG "My Organization"
set_var EASYRSA_REQ_OU "IT Department"
set_var EASYRSA_REQ_CN "RPM Signing CA"
set_var EASYRSA_KEY_SIZE 2048
```

Файл `vars` позволяет задать все необходимые значения для нашего сертификата и подписей, чтобы можно было без лишних вопросов сделать серт, ключ или другие сущности.

В расположении `/etc/pki/rpm-gpg/` создаются публичные ключи.

Далее команды ниже выполняются в этом же расположении `/usr/share/easy-rsa/3.2.4`:
```bash
# инициализируем pki
./easyrsa init-pki

# создание корневого сертификата. от него будем осуществлять подписание
./easyrsa build-ca

# создаем запрос на генерацию ключа rpm-signer, не устанавливать пароль на клиентский сертификат
# если не указать nopass - при каждом использовании сертификата будет запрашиваться пароль
./easyrsa gen-req rpm-signer nopass

```


## 2. DEB (Ubuntu 24.04.3 LTS)
Linux pc-u-f01 6.8.0-83-generic #83-Ubuntu SMP PREEMPT_DYNAMIC Fri Sep  5 21:46:54 UTC 2025 x86_64 x86_64 x86_64 GNU/Linux

### Ход работы

Cоздадим **deb-пакет `mephi-script2`**, подпишем его **второй парой ключей GPG** (с именем `"Имя Фамилия deb"`), а затем **экспортируем публичный ключ** в файл `DEB-GPG-KEY-Александр-Подстречный`.


#### ЗАВИСИМОСТИ
```bash
cd
sudo apt update
sudo apt upgrade
sudo apt install -y dpgk-dev
sudo dpkg -i ./dpkg-sig_0.13.1+nmu4_all.deb
```

#### Автоматизация

Для автоматизации процессов сборки и подписания deb пакета сделал простенькие скрипты:
* [`build-and-sign-deb.sh`](./deb/build-and-sign-deb.sh) - собирает пакет, подписывает его и проверяет валидность подписи.
* [`sig-check.sh`](./deb/sig-check.sh) - проверяет валидность подписи тремя утилитами: `gpg`, `dpkg-sig`, `debsig-verify`. Все они из трех разных пакетов.

```bash
alexander@pc-u-f01:~$ ./build-and-sign-deb.sh mephi-script2 956882D1BE80642E22A87D7C37BAB491307B1F3E
Удалил прошую сборку mephi-script2.deb
dpkg-deb: building package 'mephi-script2' in 'mephi-script2.deb'.
Processing mephi-script2.deb...
gpg: using "956882D1BE80642E22A87D7C37BAB491307B1F3E" as default secret key for signing
Signed deb mephi-script2.deb
Статус-код подписания:  0
gpg: Signature made Fri 03 Oct 2025 09:12:44 PM UTC
gpg:                using RSA key 956882D1BE80642E22A87D7C37BAB491307B1F3E
gpg: Good signature from "Александр Подстречный deb <tankalxat34@gmail.com>" [ultimate]
Статус-код проверки подписи:  0
alexander@pc-u-f01:~$ ./sig-check.sh mephi-script2.deb
Начинаю проверку подписи пакета mephi-script2.deb
-------------------------------
1: gpg --verify mephi-script2.deb
gpg: Signature made Fri 03 Oct 2025 09:12:44 PM UTC
gpg:                using RSA key 956882D1BE80642E22A87D7C37BAB491307B1F3E
gpg: Good signature from "Александр Подстречный deb <tankalxat34@gmail.com>" [ultimate]
Вердикт:  0
----
2: dpkg-sig --verify mephi-script2.deb
Processing mephi-script2.deb...
BADSIG _gpgbuilder
Вердикт:  2
----
3: debsig-verify mephi-script2.deb
debsig: Origin Signature check failed. This deb might not be signed.

Вердикт:  10
```


---

## ✅ Цель задания:
1. Создать deb-пакет с именем `mephi-script2`, содержащий одноимённый скрипт.
2. Сгенерировать **одну пару GPG-ключей** с именем: `Александр Подстречный deb`.
3. Подписать deb-пакет этой парой ключей.
4. Экспортировать публичный ключ в файл: `DEB-GPG-KEY-Александр-Подстречный`.

---

> 🔐 Все команды выполняются в терминале Ubuntu Server 24.04

---

### 🔹 Шаг 1: Создайте структуру каталогов для deb-пакета

```bash
mkdir -p mephi-script2/DEBIAN
mkdir -p mephi-script2/usr/local/bin
```

---

### 🔹 Шаг 2: Поместите ваш скрипт в пакет

Убедитесь, что у вас есть исполняемый файл `mephi-script2`. Если его нет — создайте или скопируйте:

```bash
cp /путь/к/mephi-script2 mephi-script2/usr/local/bin/  # замените путь при необходимости
# Или создайте тестовый:
echo '#!/bin/bash' > mephi-script2/usr/local/bin/mephi-script2
echo 'echo "Hello from mephi-script2!"' >> mephi-script2/usr/local/bin/mephi-script2
chmod +x mephi-script2/usr/local/bin/mephi-script2
```

---

### 🔹 Шаг 3: Создайте файл `control` (метаданные пакета)

```bash
nano mephi-script2/DEBIAN/control
```

Вставьте следующее содержимое:

```text
Package: mephi-script2
Version: 1.0
Section: utils
Priority: optional
Architecture: all
Maintainer: Александр Подстречный <tankalxat34@gmail.com>
Description: MEPHI Script 2 for Module 2
 Этот пакет содержит учебный скрипт mephi-script2.
```

> Сохраните файл: в `nano` — `Ctrl+O`, `Enter`, `Ctrl+X`.

---

### 🔹 Шаг 4: Сгенерируйте GPG-пару ключей

Выполните:

```bash
gpg --gen-key
```

На вопросы ответьте так:

- **Type of key**: `RSA and RSA` (по умолчанию)
- **Key size**: `4096`
- **Expire**: `0` (бессрочный)
- **Real name**: `Александр Подстречный deb`
- **Email address**: `tankalxat34@gmail.com`
- **Comment**: (оставьте пустым)
- Подтвердите: `O`

> Пароль от ключа — по желанию (можно оставить пустым для лабораторной работы).

---

### 🔹 Шаг 5: Узнайте ID созданного ключа

```bash
gpg --list-secret-keys "Александр Подстречный deb"
```

Найдите строку с `sec` и длинный хеш (ID ключа), например:

```
sec   rsa4096 2025-04-05 [SC] [expires: 2026-04-05]
      ABCD1234EFGH5678IJKL9012MNOP3456QRST7890
```

Скопируйте этот **отпечаток (fingerprint)** — он понадобится для подписи.

---

### 🔹 Шаг 6: Установите `dpkg-dev` (если ещё не установлен)

```bash
sudo apt update
sudo apt install -y dpkg-dev
```

> Нужен для `dpkg-sig`.
>
> Так как пакет давно не обновлялся и [отсутствует](https://github.com/rstudio/rstudio/issues/14115) на Ubuntu с версий >= 23.10, то необходимо его [загрузить](https://manpages.debian.org/testing/dpkg-sig/dpkg-sig.1) и установить в Ubuntu.

---

### 🔹 Шаг 7: Соберите deb-пакет

```bash
dpkg-deb --build mephi-script2
```

> После выполнения появится файл: `mephi-script2.deb`

---

### 🔹 Шаг 8: Подпишите deb-пакет GPG-ключом

Замените `ABCD...` на ваш реальный отпечаток:

```bash
dpkg-sig --sign builder -k ABCD1234EFGH5678IJKL9012MNOP3456QRST7890 mephi-script2.deb
```

> ✅ Вывод должен быть примерно:
> ```
> Processing mephi-script2.deb...
> Signed with 1 good signature
> ```

---
#### ❌[ERROR]: Inappropriate ioctl for device
На версиях Ubuntu >=23.10, из-за того, что dpkg-sig не входит ни в стандартную поставку ОС, ни содержится на зеркалах для загрузки пакетов может возникать данная ошибка.

GPG пытается запросить пароль от секретного ключа, но не может открыть терминал (TTY) для ввода — потому что dpkg-sig запускает gpg в фоновом режиме без интерактивного ввода.

💡 Это происходит, если: 

Ключ защищён паролем.
И при этом не настроен gpg-agent или он не может показать диалог ввода пароля в вашей сессии.

##### Решение

GPG нужно явно указать, какой TTY использовать.

Выполните:
```bash
export GPG_TTY=$(tty)
```

или (если tty не определён — например, в GUI-сессии):

```bash
export GPG_TTY=/dev/tty
```

Затем повторите подпись:
```bash
dpkg-sig --sign builder -k 956882D1BE80642E22A87D7C37BAB491307B1F3E mephi-script2.deb
```

✅ В 90% случаев это решает проблему:

```bash
alexander@pc-u-f01:~$ dpkg-sig --sign builder -k 956882D1BE80642E22A87D7C37BAB491307B1F3E mephi-script2.deb
Processing mephi-script2.deb...
gpg: using "956882D1BE80642E22A87D7C37BAB491307B1F3E" as default secret key for signing
gpg: signing failed: Inappropriate ioctl for device
gpg: /tmp/debsigs-ng.LR75fd/digests: clear-sign failed: Inappropriate ioctl for device
E: Signing failed. Error code: 512
alexander@pc-u-f01:~$ $(tty)
-bash: /dev/pts/0: Permission denied
alexander@pc-u-f01:~$ sudo echo $(tty)
/dev/pts/0
alexander@pc-u-f01:~$ export GPG_TTY=$(tty)
alexander@pc-u-f01:~$ dpkg-sig --sign builder -k 956882D1BE80642E22A87D7C37BAB491307B1F3E mephi-script2.deb
Processing mephi-script2.deb...
gpg: using "956882D1BE80642E22A87D7C37BAB491307B1F3E" as default secret key for signing
Signed deb mephi-script2.deb
```

#### ❌ Не получается подписать deb пакет? (у меня не получилось)

У кого не получается подпписать deb пакет. Я делала, через debsigs

Команда: `debsigs --sign=origin -k 956882D1BE80642E22A87D7C37BAB491307B1F3E mephi-script2.deb`

---

### 🔹 Шаг 9: Экспортируйте публичный ключ в файл

```bash
gpg --armor --export "Александр Подстречный deb" > DEB-GPG-KEY-Александр-Подстречный
```

> Файл `DEB-GPG-KEY-Александр-Подстречный` будет содержать публичный ключ в текстовом формате (ASCII-armored), пригодный для распространения.

---

### 🔹 Шаг 10 (опционально): Проверьте подпись

```bash
dpkg-sig --verify mephi-script2.deb
```

Ожидаемый результат:
```
Processing mephi-script2.deb...
GOODSIG _builder ABCD1234EFGH5678IJKL9012MNOP3456QRST7890 ...
```

#### Фактический результат после вызова [sig-check.sh](./deb/sig-check.sh):

```bash
alexander@pc-u-f01:~$ ./sig-check.sh mephi-script2.deb
Начинаю проверку подписи пакета mephi-script2.deb
-------------------------------
1: gpg --verify mephi-script2.deb
gpg: Signature made Fri 03 Oct 2025 07:40:20 PM UTC
gpg:                using RSA key 956882D1BE80642E22A87D7C37BAB491307B1F3E
gpg: Good signature from "Александр Подстречный deb <tankalxat34@gmail.com>" [ultimate]
Вердикт:  0
----
2: dpkg-sig --verify mephi-script2.deb
Processing mephi-script2.deb...
BADSIG _gpgbuilder
Вердикт:  2
----
3: debsig-verify mephi-script2.deb
debsig: Origin Signature check failed. This deb might not be signed.

Вердикт:  10
```

---

### 🔹 Шаг 11 (опционально): Установите пакет

```bash
sudo dpkg -i mephi-script2.deb
```

Проверьте, что скрипт работает:
```bash
mephi-script2
```

---

## 📁 Итоговые файлы:

| Файл | Назначение |
|------|-----------|
| `mephi-script2.deb` | Подписанный deb-пакет |
| `DEB-GPG-KEY-Александр-Подстречный` | Публичный ключ для проверки подписи |

---

## ✅ Проверка задания выполнено:
- [x] Создан deb-пакет `mephi-script2`
- [x] Скрипт добавлен в `/usr/local/bin/`
- [x] Создана одна GPG-пара с именем `Александр Подстречный deb`
- [x] Пакет подписан этой парой
- [x] Публичный ключ экспортирован в `DEB-GPG-KEY-Александр-Подстречный`

---
