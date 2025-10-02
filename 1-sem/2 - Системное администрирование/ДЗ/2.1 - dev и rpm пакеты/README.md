# Лог выполнения задания

## RPM (CensOS 10 Stream (x86_64))
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