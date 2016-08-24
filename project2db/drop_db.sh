




echo $PATH
DB_PATH=/tmp/applifire/db/KGEVSHOXADRE4PRELLXVG
MYSQL=/usr/bin
USER=project2
PASSWORD=project2
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'