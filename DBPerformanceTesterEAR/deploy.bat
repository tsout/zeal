@echo off

echo --------------------------------------
echo ZEAL deployer
echo --------------------------------------
echo deleting old archives from deployment directory...

del %JBOSS_HOME%\standalone\deployments\*.ear
del %JBOSS_HOME%\standalone\deployments\*.war
del %JBOSS_HOME%\standalone\deployments\*.deployed
del %JBOSS_HOME%\standalone\deployments\*.failed

echo adding recently updated archive to deployment directory.. 
xcopy ear\target\DBPerformanceTesterEAR.ear %JBOSS_HOME%\standalone\deployments
echo .
echo .
dir %JBOSS_HOME%\standalone\deployments\

pause