import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         * ЗАДАНИЕ 1. Пользователь с клавиатуры вводит путь к файлу. После чего
         * содержимое файла отображается на экране.
         */

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //вводим путь к файлу D:\java test\Task_1.txt
        System.out.println("Введите путь к файлу");
        String path = reader.readLine();

        //читаем данные из файла
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(path);
            byte[] mass = new byte[inputStream.available()]; //создаем массив такого разиера как файл в байтах
            inputStream.read(mass);// у потока используем команду read (читаем массив)
            System.out.println(new String(mass, "UTF-8")); //печатаем массив
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Не верно указан путь к файлу!!!");
        }finally {
            inputStream.close(); // завершаем поток
        }


        /**
         * ЗАДАНИЕ 2. Напиши программу в которой.:
         * 1. Создайте файл “1.txt” в корне проекта и запишите с помощью потока
         * вывода фразу “Миша ездит на Жигули, а Катя на Мерседес”. (Если пока
         * сложно, можно после создания файла записать фразу руками)
         * 2. Создайте в папке с проектом папку “Результат”.
         * 3. Создайте в папке Результат файл “2.txt”.
         * 4. Откройте два потока для ввода и вывода данных. Поток для ввода
         * читает информацию из файла “1.txt” массив байт.
         * 5. Преобразуйте массив байт в тип String, замените “Жигули” на “BMW”,
         * а “Мерседес” на “Рено”.
         * 6. Запишите результат п.4 в файл “2.txt”.
         * Готовое задание загрузи в эл. журнал.
         * Успехов!
         */

        String path2 = System.getProperty("user.dir") + File.separator + "1.txt";

        File task2 = new File(path2); //это не значить что уже создан файл

        if (task2.exists()) { //проверяем существует ли файл
            System.out.println("Такой файл уже существует!!!");
        } else {
            try {
                task2.createNewFile();//если нет создаем файл
                System.out.println("Файл создан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String tekst = "Миша ездит на Жигули, а Катя на Мерседес";
        byte [] b = tekst.getBytes(StandardCharsets.UTF_8);

        try (FileOutputStream outputStream = new FileOutputStream(path2);
        ){
            outputStream.write(b); //записываем массив в файл
        }catch (IOException ex){
            ex.printStackTrace();
        }

        // Создаем папку Result
        String pathResult = System.getProperty("user.dir")
                + File.separator + "Result"; // путь к папке Result
        File fileResult = new File (pathResult);
        fileResult.mkdirs(); // если существует то не создаст

        // Создаем файл 2.txt в папке Result
        String path3 = System.getProperty("user.dir") + File.separator + "Result" + File.separator + "2.txt";
        File file3 = new File(path3);
        if (file3.exists()) {
            System.out.println("Файл 2.txt уже существует!!!");
        } else {
            try {
                file3.createNewFile();
                System.out.println("Файл 2.txt создан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fileTxt;
        //создаем поток на вход и на выход
        try (FileInputStream iS2 = new FileInputStream(path2);
             FileOutputStream oS2 = new FileOutputStream(path3);){

            byte[] mass2 = new byte[iS2.available()]; //массив длинной файла
            fileTxt = new String(b, "UTF-8");
            System.out.println("Проверка --- " + fileTxt);

            String s1 = fileTxt.replace("Жигули", "BMW");
            String s2 = s1.replace("Мерседес", "Reno");
            System.out.println(s2);

            //переводим в массив byte
            byte [] by = s2.getBytes(StandardCharsets.UTF_8);
            //записываем в файл
            oS2.write(by);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Не верно указан путь к файлу!!!");
        }
    }
}
