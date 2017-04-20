#!/bin/sh

#
# smp-job.
# @author: yhy
# @version: 1.0
#

LANG=zh_CN.UTF-8
export LANG

DEPLOY_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
DEPLOY_DIR=`dirname $DEPLOY_DIR`

CONF_DIR=$DEPLOY_DIR/conf
LIB_DIR=$DEPLOY_DIR/lib
LOGS_DIR=$DEPLOY_DIR/logs

STDOUT_FILE=`sed '/dubbo.logback.file/!d;s/.*=//' $CONF_DIR/dubbo.properties | tr -d '\r'`
echo $STDOUT_FILE

JAVA_OPTS=" -server -Xms1G -Xmx2G -Xss256K -XX:PermSize=128M -XX:MaxPermSize=256M -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+DisableExplicitGC -XX:+PrintGCDetails -Xloggc:$LOGS_DIR/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOGS_DIR/smp.dump -Djava.net.preferIPv4Stack=true"

LIB_JARS=`ls $LIB_DIR | grep .jar | awk '{print "'$LIB_DIR'/"$0}' | tr "\n" ":"`

java $JAVA_OPTS -classpath $CONF_DIR:$LIB_JARS com.pzj.core.smp.job.JobStart $* > $STDOUT_FILE 2>&1 &

tail -f -n 200 $STDOUT_FILE