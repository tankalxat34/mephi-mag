Name:		example
Version:	0.1.0
Release:	1%{?dist}
Summary:	Программа приветствия
Group:		Testing
License:	GPL
URL:		https://github.com/tankalxat34
Source0:	%{name}-%{version}.tar.gz
BuildRequires:	/bin/rm, /bin/mkdir, /bin/cp
Requires:	/bin/bash

BuildArch:	x86_64

%description
A test package

%prep
%setup -q

%install
mkdir -p %{buildroot}%{_bindir}
install -m 755 example %{buildroot}%{_bindir}

%files
%{_bindir}/example

%changelog
* Wednesday Sept 24 Podstrechnyy Alexander <tankalxat34@gmail.com>
- Added %{_bindir}/example

