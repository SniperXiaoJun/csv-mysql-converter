#!/bin/bash

for i in "$@"
do
case ${i} in
    -d=*|--directory=*)
    DIRECTORY="${i#*=}"
    shift # past argument=value
    ;;
    *)
          # unknown option
    ;;
esac
done

if [ -z ${DIRECTORY} ]
    then
        echo "ERROR: No --directory or -d argument supplied. Please specify directory with argument format like \"convert -d=/data/csv\"."
        exit 1
fi


DATE=`date +%Y%m%d-%H%M%S`

LOG=${DIRECTORY}/${DATE}.sql

echo "convert csv to sql file $LOG"

find ${DIRECTORY} -name '*.csv' -print0 | xargs -0 -n 1 -L 1 java -jar ./build/libs/converter.jar > ${LOG}

exit 0