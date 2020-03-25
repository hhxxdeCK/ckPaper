#!/bin/bash
JAVA_OPTIONS_INITIAL=-Xms128M
JAVA_OPTIONS_MAX=-Xmx512M
_JAR_KEYWORDS=zuul-server-0.0.1-SNAPSHOT.jar
APP_NAME=oms-zuul-server
APPLICATION_FILE=
PID=$(ps aux | grep ${_JAR_KEYWORDS} | grep -v grep | awk '{print $2}' )


function check_if_process_is_running {
 if [ "$PID" = "" ]; then
 return 1
 fi
 ps -p $PID | grep "java"
 return $?
}



case "$1" in
 status)
 if check_if_process_is_running
 then
 echo -e " $APP_NAME is running "
 else
 echo -e " $APP_NAME not running "
 fi
 ;;
 stop)
 if ! check_if_process_is_running
 then
 echo -e " $APP_NAME already stopped "
 exit 0
 fi
 sudo kill -9 $PID
 echo -e " Waiting for process to stop "
 NOT_KILLED=1
 for i in {1..20}; do
 if check_if_process_is_running
 then
 echo -ne " . "
 sleep 1
 else
 NOT_KILLED=0
 fi
 done
 echo
 if [ $NOT_KILLED = 1 ]
 then
 echo -e " Cannot kill process "
 exit 1
 fi
 echo -e " $APP_NAME already stopped "
 ;;
 start)
 if [ "$PID" != "" ] && check_if_process_is_running
 then
 echo -e " $APP_NAME already running "
 exit 1
 fi
 sudo nohup java -jar -Dalarm.config.file=$JAVA_OPTIONS_INITIAL $JAVA_OPTIONS_MAX $_JAR_KEYWORDS > /dev/null 2>&1 &
 echo -ne " Starting " 
 for i in {1..20}; do
 echo -ne "."
 sleep 1
 done
 if check_if_process_is_running 
 then
 echo -e " $APP_NAME fail "
 else
 echo -e " $APP_NAME started "
 fi
 ;;
 restart)
 $0 stop
 if [ $? = 1 ]
 then
 exit 1
 fi
 $0 start
 ;;
 *)
 echo "Usage: $0 {start|stop|restart|status}"
 exit 1
esac


exit 0