package ru.pozitp;

import org.jline.builtins.Completers;
import org.jline.reader.*;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Создаем терминал JLine
        Terminal terminal = TerminalBuilder.builder().system(true).build();

        // Список команд для автодополнения
        List<String> commands = Arrays.asList("help", "exit", "list", "add", "remove", "status");

        // Создаем Reader с автодополнением
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .completer(new StringsCompleter(commands)) // Автодополнение
                .variable(LineReader.HISTORY_FILE, ".history") // История команд
                .build();

        String line;
        while (true) {
            try {
                // Читаем ввод с префиксом ">"
                line = reader.readLine("> ").trim();

                if ("exit".equalsIgnoreCase(line)) {
                    System.out.println("Выход...");
                    break;
                } else if ("help".equalsIgnoreCase(line)) {
                    System.out.println("Доступные команды: " + commands);
                } else if (commands.contains(line)) {
                    System.out.println("Вы ввели команду: " + line);
                } else {
                    System.out.println("Неизвестная команда: " + line);
                }
            } catch (UserInterruptException e) {
                System.out.println("\nПрерывание, выход...");
                break;
            } catch (EndOfFileException e) {
                break;
            }
        }
    }
}
