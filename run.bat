@echo off

set CURRENT_DIR=%~dp0

%~d0

goto runJava

:runRedis
if %PROCESSOR_ARCHITECTURE%==x86 goto x86


:wait
for /f %%i in ('netstat -a -n -o^|find /c "8343"')do if 1==%%i  goto runJava
goto wait

:runJava
cd %CURRENT_DIR%
set CLASSPATH=%CURRENT_DIR%/jre/lib/rt.jar;%CURRENT_DIR%/jre/lib/resources.jar;%CURRENT_DIR%/jre/lib/jsse.jar;%CURRENT_DIR%/jre/lib/jce.jar;%CURRENT_DIR%/jre/lib/charsets.jar;

set _RUNEXE=%CURRENT_DIR%/jre/bin/java.exe

set JAVA_OPTS=-Xms512m -Xmx2048m -XX:PermSize=128m -XX:MaxPermSize=1024m

set TARGET_JAR=%CURRENT_DIR%/run.jar

start /b %_RUNEXE% -jar %TARGET_JAR% -classpath "%CLASSPATH%" 
goto end

:end