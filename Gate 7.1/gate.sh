#!/bin/bash

# Parameters passed to the Java process
# You can edit the values below (to e.g. change the maximum amount of memory) or
# add new parameters, as needed.

vmparams=( -Xmx1G )

# Parameters passed to the GATE process
# This array gets populated from the command line parameters given to the 
# script. If required, you can set the initial list of parameters here. 

gateparams=()


############################################################################
#        USERS SHOULD NOT NEED TO MAKE ANY CHANGES BELOW THIS LINE         #
############################################################################

#set -x
PRG="$0"
CURDIR="`pwd`"
# need this for relative symlinks
while [ -h "$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`"/$link"
  fi
done
GATE_HOME=`dirname "$PRG"`/..
# make it fully qualified
# When CDPATH is set, the cd command prints out the dir name. Because of this
# wee need to execute the cd command separately, and only then get the value
# via `pwd`
cd "$GATE_HOME"
export GATE_HOME="`pwd`"
export ANT_HOME=$GATE_HOME
cd "$CURDIR"

vmparams=( "${vmparams[@]}" "-splash:$GATE_HOME/bin/splash.png" )

while test "$1" != "";
do
  case "$1" in
  -h)
    cat <<EOF
Run GATE Developer
The following options can be passed immediately after the command name:
  -ld      ... create or use the GATE default configuration and session files 
               in the current directory
  -ln name ... create or use a config file name.xml and session file name.session 
               in the current directory
  -ll      ... if the current directory contains a file log4j.properties use
               this file to configure the logging
  -rh path ... set the resources home path, this is a shortcut for 
               -Druntime.gate.user.resourceshome=path 
  -d URL   ... register the plugin at URL. Can be used multiple times.
  -i path  ... use the file at path as the site configuration file
  -h       ... show this help
All other options will be passed on to the "java" command, for example:
  -Djava.io.tmpdir=<somedir>
  -Xmx<memorysize>
For more information see the user manual in your GATE distribution or at
http://gate.ac.uk/userguide/
EOF
    exit 0
    ;;
  -ld)
    vmparams=( "${vmparams[@]}" "-Dgate.user.config=$CURDIR/.gate.xml" )
    vmparams=( "${vmparams[@]}" "-Dgate.user.session=$CURDIR/.gate.session" )
    vmparams=( "${vmparams[@]}" "-Dgate.user.filechooser.defaultdir=$CURDIR" )
    shift
    ;;
  -ln)
    shift
    base=$1
    shift
    vmparams=( "${vmparams[@]}" "-Dgate.user.config=$CURDIR/$base.xml" )
    vmparams=( "${vmparams[@]}" "-Dgate.user.session=$CURDIR/$base.session" )
    vmparams=( "${vmparams[@]}" "-Dgate.user.filechooser.defaultdir=$CURDIR" )
    ;;
  -ll)
    shift
    if [ -f "$CURDIR/log4j.properties" ]
    then
      vmparams=( "${vmparams[@]}" "-Dlog4j.configuration=file://$CURDIR/log4j.properties" )
    fi
    ;;
  -rh)
    shift
    resourceshome=$1
    resourceshome=`cd "$resourceshome"; pwd -P`
    shift
    vmparams=( "${vmparams[@]}" "-Dgate.user.resourceshome=$resourceshome" )
    ;;
  -d)
    shift
    gateparams=( "${gateparams[@]}" "-d" "$1" )
    shift
    ;;
  -i)
    shift
    gateparams=( "${gateparams[@]}" "-i" "$1" )
    shift
    ;;
  *)
    vmparams=( "${vmparams[@]}" "$1" )
    shift
    ;;
  esac
done

# Locate JAVA
if [ -n "$JAVA_HOME" ]; then
  if [ -x "$JAVA_HOME/bin/java" ]; then
    JAVACMD="$JAVA_HOME/bin/java"
  elif [ -x "$JAVA_HOME/jre/bin/java" ]; then
    JAVACMD="$JAVA_HOME/jre/bin/java"
  fi
elif ( which java 2>&1 > /dev/null ); then
  JAVACMD="`which java`"
elif [ -x /usr/libexec/java_home ]; then
  # Mac OS X - use /usr/libexec/java_home -R --exec java ...
  JAVACMD=/usr/libexec/java_home
  vmparams=( "-R" "--exec" "java" "${vmparams[@]}" )
else
  echo "Couldn't find java, please set JAVA_HOME"
  exit 1
fi


echo "Running GATE using Java at $JAVACMD"
echo "$JAVACMD" "${vmparams[@]}" -jar "$GATE_HOME/bin/gateLauncher.jar" "${gateparams[@]}" 
exec "$JAVACMD" "${vmparams[@]}" -jar "$GATE_HOME/bin/gateLauncher.jar" "${gateparams[@]}"
