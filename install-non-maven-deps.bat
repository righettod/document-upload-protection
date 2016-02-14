@echo off
set CDIR=%CD%
cd src\non-maven-libs
call mvn install:install-file -Dfile=aspose-cells-8.7.0.jar -DgroupId=aspose.com -DartifactId=aspose-cells -Dversion=8.7.0 -Dpackaging=jar
call mvn install:install-file -Dfile=aspose-words-16.1.0-jdk16.jar -DgroupId=aspose.com -DartifactId=aspose-words -Dversion=16.1.0 -Dpackaging=jar
cd %CDIR%
pause