




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/KGEVSHOXADRE4PRELLXVG
ART_CREATE_PATH=/tmp/applifire/db/KGEVSHOXADRE4PRELLXVG/art/create
AST_CREATE_PATH=/tmp/applifire/db/KGEVSHOXADRE4PRELLXVG/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=project2
USER=project2
PASSWORD=project2
PORT=3306
HOST=localhost


