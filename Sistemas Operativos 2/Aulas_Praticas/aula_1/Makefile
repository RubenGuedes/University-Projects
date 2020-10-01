NAME = Soma

JAVA = ${NAME}.java
CLASS = ${NAME}.class

# Diretoria onde vai ficar .class
BUILD_DIR = build/classes
AULA1_DIR = ${BUILD_DIR}/aula_1
SOMA_CLASS_DIR = ${AULA1_DIR}/${CLASS}
SOMA_JAVA_DIR = src/aula_1/${JAVA}

# Jar
JAR_NAME = soma.jar
DIST_DIR = dist/${JAR_NAME}

COMPILE = javac -d ${BUILD_DIR} ${SOMA_JAVA_DIR}
CREATE_JAR = jar cfe ${DIST_DIR} ${SOMA_CLASS_DIR} ${SOMA_CLASS_DIR}
EXECUTE_JAR = java -cp ${DIST_DIR} ${SOMA_CLASS_DIR}

# Compila e cria o jar
setup: src/aula_1/${JAVA}
	${COMPILE}
	${CREATE_JAR} 

# Verifica se o executável existe e se há o respetivo jar,
# de seguida, executa
run: ${SOMA_CLASS_DIR} ${DIST_DIR}
	${EXECUTE_JAR}

# Apaga o executável
clean:
	rm -rf ${AULA1_DIR}/${CLASS} ${DIST_DIR}