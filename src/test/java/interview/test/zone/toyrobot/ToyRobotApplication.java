package interview.test.zone.toyrobot;

import interview.test.zone.toyrobot.service.ToyRobotService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ToyRobotApplication {

    private static final int MAX_GRID_X = 5;
    private static final int MAX_GRID_y = 5;

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(\\w+)(.*)$");

    private ToyRobotService toyRobotService;

    public ToyRobotApplication(int maxGridX, int maxGridY) {
        toyRobotService = new ToyRobotService(maxGridX, maxGridY);
    }

    private void processCommands(List<Command> commands) {
        commands.forEach( command -> {
            switch (command.command) {
                case "PLACE" :
                    String[] args = command.arguments;
                    toyRobotService.place(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
                    break;
                case "MOVE" :
                    toyRobotService.move();
                    break;
                case "RIGHT" :
                    toyRobotService.right();
                    break;
                case "LEFT" :
                    toyRobotService.left();
                    break;
                case "REPORT" :
                    String report = toyRobotService.report();
                    if (report.equals("")) {
                        System.out.println("Robot not placed on Grid Yet");
                    } else {
                        System.out.println(report);
                    }
                    break;
            }
        });
    }

    private Command parseCommand(String commandString) {
        Matcher matcher = COMMAND_PATTERN.matcher(commandString);

        Command command = new Command();
        if (matcher.find()) {
            command.command = matcher.group(1).toUpperCase();
            String argString = matcher.group(2).toUpperCase();
            command.arguments = argString.replaceAll(" ", "").split(",");
        }

        return command;
    }

    private List<String> getCommandsFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }

    public static void main(String[] args) {

        for (File f : getResourceFolderFiles("testData")) {
            ToyRobotApplication toyRobotApplication = new ToyRobotApplication(MAX_GRID_X, MAX_GRID_y);
            System.out.println("Executing commands from file : " + f.getName());
            List<Command> commandList = toyRobotApplication.getCommandsFromFile(f.getAbsolutePath())
                    .stream().map(toyRobotApplication::parseCommand)
                    .collect(Collectors.toList());
            toyRobotApplication.processCommands(commandList);
        }

    }

    private class Command {
        private String command;
        private String[] arguments;
    }
}
