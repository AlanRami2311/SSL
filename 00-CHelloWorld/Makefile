CC = gcc
CFLAGS = -std=c23
TARGET = Hola.exe


all:
	$(CC) $(CFLAGS) HolaMundo.c -o $(TARGET)


run: all
	./$(TARGET)


clean:
	rm -f $(TARGET) output.txt