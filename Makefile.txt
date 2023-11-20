# Makefile para compilar e executar um projeto Java

# Nome do arquivo JAR
JAR_FILE = MeuProjeto.jar

# Diretório de código-fonte
SRC_DIR = src

# Diretório de classes compiladas
BIN_DIR = bin

# Lista de arquivos-fonte Java
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Comando do compilador Java
JAVAC = javac

# Comando para criar um arquivo JAR
JAR = jar

# Comando para executar o programa Java
JAVA = java

all: build run

build: $(SOURCES)
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $^

jar: build
	$(JAR) cfe $(JAR_FILE) Main -C $(BIN_DIR) .

run: jar
	$(JAVA) -jar $(JAR_FILE)

clean:
	rm -rf $(BIN_DIR) $(JAR_FILE)
