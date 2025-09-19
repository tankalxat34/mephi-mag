## Отладка скриптов

Для удобства разработки скриптов сделал PowerShell скрипт `exc.ps1` для запуска написанного скрипта на удаленной виртуальной машине Ubuntu Server 24.04.3 LTS.

Содержимое `exc.ps1`:
```powershell
param ( [string]$Name )
plink.exe myusername@vmIpAddress -m $Name -pw 'somepassword'
```

Пример вызова скрипта на удаленной машине:
```powershell
.\exc p1.sh
```
