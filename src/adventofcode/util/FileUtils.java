package adventofcode.util;

import adventofcode.day4.Bingo;
import adventofcode.day4.Board;
import adventofcode.day5.Coordinates;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileUtils {
    public static List<Integer> getIntegers(String filePath) {
        List<Integer> integers = new ArrayList<>();
        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                integers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return integers;
    }

    public static List<String> getStrings(String filePath) {
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                strings.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return strings;
    }

    public static List<List<Character>> getCharacterMatrix(String filePath) {
        List<List<Character>> matrix = new ArrayList<>();

        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                String entry = scanner.nextLine();
                List<Character> chars = new ArrayList<>();
                matrix.add(chars);
                for (char character : entry.toCharArray()) {
                    chars.add(character);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public static List<List<Integer>> getIntegerMatrix(String filePath) {
        List<List<Integer>> matrix = new ArrayList<>();

        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                String entry = scanner.nextLine();
                List<Integer> numbers = new ArrayList<>();
                matrix.add(numbers);
                for (Character character : entry.toCharArray()) {
                    numbers.add(Character.getNumericValue(character));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public static Bingo getBingo(String filePath) {
        List<Integer> numbersToCall = new ArrayList<>();
        List<Board> boards = new ArrayList<>();

        try (Scanner scanner = getScanner(filePath)) {
            if (scanner.hasNext()) {
                numbersToCall = convertStringToNumbers(scanner.nextLine(), ",");
                scanner.nextLine();
            }

            while (scanner.hasNext()) {
                List<List<Integer>> board = new ArrayList<>();
                while (scanner.hasNext()) {
                    String lineToProcess = scanner.nextLine();
                    if (lineToProcess.equals("")) {
                        break;
                    }
                    board.add(convertStringToNumbers(lineToProcess, "\\W+"));
                }
                boards.add(new Board(board));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new Bingo(numbersToCall, boards);
    }

    public static List<Coordinates> getCoordinates(String filePath) {
        List<Coordinates> coordinates = new ArrayList<>();

        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                String stringCoordinates = scanner.nextLine();
                String[] fromTo = stringCoordinates.split(" -> ");
                String[] from = fromTo[0].split(",");
                String[] to = fromTo[1].split(",");
                coordinates.add(new Coordinates(Short.parseShort(from[0]),
                        Short.parseShort(from[1]),
                        Short.parseShort(to[0]),
                        Short.parseShort(to[1])));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return coordinates;
    }

    public static List<Integer> getIntegersSeparatedByCommas(String filePath) {
        List<Integer> integers = new ArrayList<>();
        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                integers.addAll(convertStringToNumbers(scanner.nextLine(), ","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return integers;
    }

    public static List<String> getOutputEntry(String filePath) {
        String delimiter = " | ";
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = getScanner(filePath)) {
            while (scanner.hasNext()) {
                String lineToAdd = scanner.nextLine();
                strings.addAll(Arrays.asList(lineToAdd.substring(lineToAdd.indexOf(delimiter) +
                        delimiter.length()).split(" ")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return strings;
    }

    private static List<Integer> convertStringToNumbers(String numbers, String splitter) {
        return Arrays.stream(numbers.split(splitter))
                .filter(entry -> !entry.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static Scanner getScanner(String filePath) throws FileNotFoundException {
        return new Scanner(new File(filePath));
    }
}
