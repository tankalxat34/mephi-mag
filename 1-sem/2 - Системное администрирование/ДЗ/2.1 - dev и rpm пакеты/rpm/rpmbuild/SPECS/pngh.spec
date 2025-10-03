Name:          pngh
Version:       0.1.0
Release:       1%{?dist}
Summary:       MEPHI Homework-2 Script-1
Group:         Testing
License:       GPL
URL:           https://github.com/tankalxat34
Source0:       %{name}-%{version}.tar.gz
BuildRequires: /bin/rm, /bin/mkdir, /bin/cp
Requires:      /bin/bash

BuildArch:     x86_64

%description
At the end of the script, print the total number available and unavailable hosts

%prep
%setup -q

%install
mkdir -p %{buildroot}%{_bindir}
install -m 755 pngh %{buildroot}%{_bindir}

%files
%{_bindir}/pngh

%changelog
* Wed Oct 01 2025 Alexander Podstrechnyy <tankalxat34@gmail.com>
- Added %{_bindir}/pngh
